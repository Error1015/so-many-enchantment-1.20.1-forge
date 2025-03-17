package org.error1015.somanyenchantments.events

import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.enchantments.curse.FrailtyCurseEnchantment
import org.error1015.somanyenchantments.enchantments.curse.UnpredictableEnchantment
import org.error1015.somanyenchantments.utils.enchantmentLevel
import org.error1015.somanyenchantments.utils.getArmorEnchantmentsSum
import org.error1015.somanyenchantments.utils.isItemEnchanted
import kotlin.random.Random

@Mod.EventBusSubscriber
object CurseEnchantmentHandler {
    @SubscribeEvent
    fun doUnpredictableEnchantmentEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return

        if (event.source.entity is Player) {
            val player = event.source.entity as Player
            if (player.mainHandItem.isItemEnchanted(UnpredictableEnchantment)) {
                val level = player.mainHandItem.enchantmentLevel(UnpredictableEnchantment)
                if (level == 0) return
                val value = Random.nextFloat() + 1.25f * level * event.amount
                // 46%的概率给目标回复血量 54%的概率给目标造成伤害
                if (Math.random() <= 0.46) {
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
            val level = player.getArmorEnchantmentsSum(FrailtyCurseEnchantment)
            if (level == 0) return
            event.amount += level * 0.4f
        }
    }
}