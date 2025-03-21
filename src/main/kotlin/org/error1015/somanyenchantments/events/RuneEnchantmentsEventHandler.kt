package org.error1015.somanyenchantments.events

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.enchantments.runeword.PiercingEnchantment
import org.error1015.somanyenchantments.enchantments.runeword.RevivalEnchantment
import org.error1015.somanyenchantments.utils.enchantmentLevel
import org.error1015.somanyenchantments.utils.getItemEnchantmentLevel
import org.error1015.somanyenchantments.utils.isItemEnchanted

@Mod.EventBusSubscriber
object RuneEnchantmentsEventHandler {
    /**
     * 符文:穿刺
     */
    @SubscribeEvent
    fun doPiercingAttack(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is Player) {
            val player = event.source.entity as Player
            val piercingLevel = player.getItemEnchantmentLevel(PiercingEnchantment, EquipmentSlot.MAINHAND)
            if (piercingLevel == 0) return
            TODO()
        }
    }

    /**
     * 符文: 重铸
     */
    @SubscribeEvent
    fun doRevival(event: LivingEntityUseItemEvent) {
        if (event.entity.level().isClientSide) return
        if (event.entity is Player && event.item.isItemEnchanted(RevivalEnchantment)) {
            val player = event.entity as Player
            val level = event.item.enchantmentLevel(RevivalEnchantment)
            if (level == 0) return

            // 如果耐久值小于30,则有一定概率触发
            if (event.item.maxDamage - event.item.damageValue < 30) {
                val itemCopy = event.item.copy()
                itemCopy.damageValue = event.item.maxDamage / 2
                // 有（(1+附魔等级) * 15%的概率
                if (Math.random() <= (1f + level) * 0.15f) {
                    player.addItem(itemCopy)
                } else if (event.item.maxDamage < 1250 && Math.random() <= (1f + level) * 0.2f) {
                    player.addItem(itemCopy)
                }
            }
        }
    }
}