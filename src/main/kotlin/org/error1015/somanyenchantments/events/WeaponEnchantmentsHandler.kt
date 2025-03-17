package org.error1015.somanyenchantments.events

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.SwordItem
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.Config
import org.error1015.somanyenchantments.enchantments.weapon.BlessSwordEnchantment
import org.error1015.somanyenchantments.enchantments.weapon.LifeStealEnchantment
import org.error1015.somanyenchantments.utils.enchantmentLevel
import org.error1015.somanyenchantments.utils.isItemEnchanted

@Mod.EventBusSubscriber
object WeaponEnchantmentsHandler {
    @SubscribeEvent
    fun doLifeStealEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (!Config.lifeStealEnchantment.get()) return
        if (event.source.entity is Player) {
            val player = event.source.entity as Player
            val mainHandItem = player.getItemBySlot(EquipmentSlot.MAINHAND)
            if (mainHandItem.isItemEnchanted(LifeStealEnchantment)) {
                val level = mainHandItem.enchantmentLevel(LifeStealEnchantment)
                if (level == 0) return
                // 当附魔等级为1级,恢复玩家造成伤害的5% 当附魔等级>1级则恢复玩家造成伤害的5%+等级*0.25
                var healValue: Float = when (level) {
                    1 -> event.amount * 0.05f
                    4 -> event.amount * 0.1f // 若凌的额外要求: 四级最高10%
                    else -> event.amount * (0.05f + level * 0.25f)
                }
                player.heal(healValue)
                // 同时造成恢复伤害的2倍
                event.amount *= 2f * healValue
            }
        }
    }

    @SubscribeEvent
    fun doBlessSwordEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (!Config.blessSwordEnchantment.get()) return
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
}