package org.error1015.somanyenchantments.events

import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.monster.Ravager
import net.minecraft.world.entity.monster.Witch
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.SwordItem
import net.minecraftforge.event.ItemAttributeModifierEvent
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries
import org.error1015.somanyenchantments.enchantments.weapon.*
import org.error1015.somanyenchantments.utils.asUUID
import org.error1015.somanyenchantments.utils.enchantmentLevel
import org.error1015.somanyenchantments.utils.isItemEnchanted
import org.error1015.somanyenchantments.utils.randomDebuff
import org.error1015.somanyenchantments.Config
import kotlin.random.Random

@Mod.EventBusSubscriber
object WeaponEnchantmentsHandler {
    val debuffs = ForgeRegistries.MOB_EFFECTS.asSequence().filter { it.category == MobEffectCategory.HARMFUL }.toList()

    @SubscribeEvent
    fun doLifeStealEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is Player) {
            val player = event.source.entity as Player
            val mainHandItem = player.getItemBySlot(EquipmentSlot.MAINHAND) ?: return
            if (mainHandItem.isItemEnchanted(LifeStealEnchantment)) {
                val level = mainHandItem.enchantmentLevel(LifeStealEnchantment)
                if (level == 0) return
                val value = when (level) {
                    1 -> event.amount * 0.05f
                    else -> (event.amount * 0.05f) + (level * Config.lifeSteal.get())
                }
                player.heal(value)
                // 同时造成恢复伤害的2倍
                val hurtValue = Config.lifeStealHurtTarget.get() ?: return
                if (hurtValue != 0) {
                    event.amount *= hurtValue * value
                }
            }
        }
    }   

    @SubscribeEvent
    fun doBlessSwordEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is Player) {
            val player = event.source.entity as Player
            val mainHandItem = player.mainHandItem
            val level = mainHandItem.enchantmentLevel(BlessSwordEnchantment)
            if (level == 0) return
            // 当目标是否为亡灵生物都恢复玩家造成伤害 * level的3%
            player.heal(event.amount * level * 0.03f)
            // 当目标为亡灵时,伤害增加0.6×等级+1, 伤害提升到原本的(4%×魔咒等级)+100%
            if (event.entity.mobType == MobType.UNDEAD && mainHandItem.item is SwordItem) {
                event.amount += 0.6f * level + 1f + (event.amount * 0.04f * level + 1)
            }
        }
    }

    @SubscribeEvent
    fun doBreakMagicEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val target = event.entity
            val enchantmentLevel = attacker.mainHandItem.enchantmentLevel(BreakMagicEnchantment)
            if (enchantmentLevel == 0) return
            if (target.activeEffects.isEmpty()) return
            val count = target.activeEffects.size
            // 如果目标为女巫或者袭击者 伤害加成1.5 * 等级 其他目标伤害加成0.625 * 等级 * effectCount
            event.amount += when (target) {
                is Witch -> 1.5f * enchantmentLevel
                is Ravager -> 1.5f * enchantmentLevel
                else -> 0.625f * count * enchantmentLevel
            }
        }
    }

    /**
     * 唯快不破
     */
    @SubscribeEvent
    fun doSpeedIsUnbreakableEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val target = event.entity ?: return
            val level = attacker.mainHandItem.enchantmentLevel(SpeedIsUnbreakableEnchantment)
            if (level == 0) return
            if (Math.random() < 0.01f * level + 0.25f) {
                // 重置无敌时间
                target.invulnerableTime = 23 - level * 3
            }
        }
    }

    /**
     * 唯快不破为物品添加攻击速度
     */
    @SubscribeEvent
    fun doAddAttackSpeedEvent(event: ItemAttributeModifierEvent) {
        if (event.itemStack.isItemEnchanted(SpeedIsUnbreakableEnchantment)) {
            val level = event.itemStack.enchantmentLevel(SpeedIsUnbreakableEnchantment)
            if (level == 0) return
            if (event.slotType == EquipmentSlot.MAINHAND) {
                event.addModifier(
                    Attributes.ATTACK_SPEED, AttributeModifier(
                        "e6109481-134f-4c54-a535-29c3ae5c7a21".asUUID(),
                        "attackSpeed",
                        0.15 * level,
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                    )
                )
            }
        }
    }

    /**
     * 魔法祝福
     */
    @SubscribeEvent
    fun doMagicBlessEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val target = event.entity ?: return
            val level = attacker.mainHandItem.enchantmentLevel(MagicBlessEnchantment)
            if (level == 0) return
            if (Math.random() < 0.4) {
                // 生成随机Debuff 持续时间和等级
                val debuffIndex = Random.nextInt(0, debuffs.size)
                val debuff = debuffs[debuffIndex] ?: return
                val debuffDuration = Random.nextInt(20, 20 * level + 1)
                val debuffLevel = Random.nextInt(0, level) // level这个东西是从0开始数的

                // 如果目标是亡灵生物且随机到的Debuff为瞬间伤害
                if (target.mobType == MobType.UNDEAD && debuff == MobEffects.HARM) {
                    // 瞬间治疗给的持续时间不能太长
                    target.addEffect(MobEffectInstance(MobEffects.HEAL, 5, debuffLevel))
                } else when (debuff) {
                    // 如果是瞬间伤害则时间给足够短
                    MobEffects.HARM -> target.addEffect(MobEffectInstance(debuff, 5, debuffLevel))
                    else -> target.addEffect(MobEffectInstance(debuff, debuffDuration, debuffLevel))
                }
            }
        }
    }

    /**
     * 百毒不侵
     */
    @SubscribeEvent
    fun doAntidoteEvent(event: LivingAttackEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val debuff = attacker.randomDebuff ?: return
            if (attacker.mainHandItem isItemEnchanted AntidoteEnchantment) {
                // 一定概率清除随机debuff
                if (Math.random() < 0.2) {
                    attacker.removeEffect(debuff.effect)
                }
            }
        }
    }
}