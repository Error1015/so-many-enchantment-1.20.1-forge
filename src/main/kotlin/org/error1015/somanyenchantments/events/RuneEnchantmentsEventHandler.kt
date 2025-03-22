package org.error1015.somanyenchantments.events

import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent
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
    fun doRevival(event: LivingEntityUseItemEvent) {
        if (event.entity.level().isClientSide) return
        if (event.entity is Player && event.item.isItemEnchanted(RevivalEnchantment)) {
            val player = event.entity as Player
            val level = event.item.enchantmentLevel(RevivalEnchantment)
            if (level == 0) return

            // 如果耐久值小于30,则有一定概率触发
            if (event.item.maxDamage - event.item.damageValue < 30) {
                val itemCopy = event.item.copy()
                val r = Math.random()
                itemCopy.damageValue = event.item.maxDamage / 2
                when {
                    r <= (1f + level) * 0.15f -> player.addItem(itemCopy)
                    event.item.maxDamage < 1250 && r <= (1f + level) * 0.2f -> player.addItem(itemCopy)
                }
            }
        }
    }
}