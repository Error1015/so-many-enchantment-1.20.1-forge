package org.error1015.somanyenchantments.events

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraftforge.event.entity.EntityJoinLevelEvent
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries
import org.error1015.somanyenchantments.enchantments.curse.*
import org.error1015.somanyenchantments.utils.*
import kotlin.random.Random

@Mod.EventBusSubscriber
object CurseEnchantmentHandler {
    val curses = ForgeRegistries.ENCHANTMENTS.asSequence().filter { it.isCurse }.toList()

    @SubscribeEvent
    fun doUnpredictableEnchantmentEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is Player) {
            val player = event.source.entity as Player
            if (player.mainHandItem.isItemEnchanted(UnpredictableEnchantment)) {
                val level = player.mainHandItem.enchantmentLevel(UnpredictableEnchantment)
                if (level == 0) return
                val value = Random.nextFloat() + 1.25f * level * event.amount
                // 32%的概率给目标回复血量 68%的概率给目标造成伤害
                if (Math.random() <= 0.32) {
                    event.entity.heal(value)
                    event.isCanceled = true
                } else {
                    event.amount = value + 2
                }
            }
        }
    }

    @SubscribeEvent
    fun doFrailtyCurseEnchantmentEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.entity is Player) {
            val player = event.entity as Player
            val level = player.getAllArmorsEnchantmentsTotalLevel(FrailtyCurseEnchantment)
            if (level == 0) return
            event.amount += level * 0.4f
        }
    }

    /**
     * 符文：密封诅咒
     */
    @SubscribeEvent
    fun doSealedCurseEnchantmentEvent(event: LivingAttackEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val target = event.entity ?: return
            // 如果攻击者的主手物品有密封诅咒，则给目标装备一个随机诅咒
            if (attacker.mainHandItem.isItemEnchanted(SealedCurseEnchantment)) {
                if (Random.nextDouble() < 0.2) {
                    val armors = mutableListOf<ItemStack>()
                    for (stack in target.armorSlots) {
                        if (stack != null) armors.add(stack)
                    }
                    // 检查是否有可用的诅咒和护甲
                    if (curses.isEmpty() || armors.isEmpty()) return
                    // 随机选择诅咒和护甲
                    val armorsIndex = Random.nextInt(0, armors.size)
                    val armor = armors[armorsIndex]
                    var curseIndex = Random.nextInt(0, curses.size)
                    var enchantment = curses[curseIndex] ?: return
                    var level = Random.nextInt(1, enchantment.maxLevel + 1)
                    val armorEnchantments = EnchantmentHelper.getEnchantments(armor).toMutableMap()
                    val isEnchantmentCompatible = EnchantmentHelper.isEnchantmentCompatible(armorEnchantments.keys, enchantment)
                    // 如果不兼容或者已经存在冲突附魔 则退出(太麻烦了重新生成)
                    if (!enchantment.canEnchant(armor) || !isEnchantmentCompatible) {
                        return
                    }
                    // 成功后设置附魔
                    armorEnchantments[enchantment] = level
                    EnchantmentHelper.setEnchantments(armorEnchantments, armor)
                    // 伤害攻击者
                    attacker.hurt(attacker.damageSources().magic(), level * 4f)
                }
            }
        }
    }

    /**
     * 腐朽诅咒
     */
    @SubscribeEvent
    fun doRottenCurseEnchantmentEvent(event: EntityJoinLevelEvent) {
        if (event.entity.level().isClientSide) return
        if (event.entity is ItemEntity) {
            val itemEntity = event.entity as ItemEntity
            if (itemEntity.item.isItemEnchanted(RottenCurseEnchantment)) {
                itemEntity.lifespan = 200 // 设置成10秒
            }
        }
    }

    /**
     * 魔法诅咒
     */
    @SubscribeEvent
    fun doMagicCurseEvent(event: LivingAttackEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val sourceEntity = event.source.entity as LivingEntity
            val target = event.entity ?: return
            // 如果攻击者任意护甲上有魔法诅咒
            if (sourceEntity armorHasEnchantment MagicCurseEnchantment) {
                val effect = target.randomDebuff ?: return
                // 概率触发
                if (Math.random() < 0.2) {
                    target.removeEffect(effect.effect) // 移除目标的随机负面效果
                    sourceEntity.addEffect(effect) // 把debuff转移到攻击者
                }
            }
        }
    }
}