package org.error1015.somanyenchantments.events

import net.minecraftforge.event.level.BlockEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.enchantments.runeword.RevivalEnchantment
import org.error1015.somanyenchantments.utils.enchantmentLevel
import org.error1015.somanyenchantments.utils.isItemEnchanted

@Mod.EventBusSubscriber
object RuneEnchantmentsEventHandler {
    /**
     * 符文: 重铸
     */
    @SubscribeEvent
    fun doRevival(event: BlockEvent.BreakEvent) {
        if (event.player.level().isClientSide) return
        val player = event.player ?: return
        val item = event.player.mainHandItem ?: return

        if (item.isItemEnchanted(RevivalEnchantment)) {
            val level = item.enchantmentLevel(RevivalEnchantment)
            if (level == 0) return

            // 如果耐久值最大耐久的25%,则有一定概率触发
            if (item.maxDamage - item.damageValue < item.maxDamage / 4) {
                val itemCopy = item.copy() ?: return
                val r = Math.random()
                itemCopy.damageValue = item.maxDamage / 2
                when {
                    r <= (1f + level) * 0.15f -> player.addItem(itemCopy)
                    item.maxDamage < 1250 && r <= (1f + level) * 0.2f -> player.addItem(itemCopy)
                }
            }
        }
    }
}