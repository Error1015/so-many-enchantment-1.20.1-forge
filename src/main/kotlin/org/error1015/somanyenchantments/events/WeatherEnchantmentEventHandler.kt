package org.error1015.somanyenchantments.events

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.LivingEntity
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.enchantments.weather.ClearSkyLoveSongEnchantment
import org.error1015.somanyenchantments.enchantments.weather.RainBlessingEnchantment
import org.error1015.somanyenchantments.utils.enchantmentLevel

@Mod.EventBusSubscriber
object WeatherEnchantmentEventHandler {
    /**
     * 晴空恋歌
     */
    @SubscribeEvent
    fun doClearLoveEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        // 如果天气不是下雨天也不是雷暴天气 则为晴天 Wiki: https://zh.minecraft.wiki/w/%E5%A4%A9%E6%B0%94
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val level = attacker.mainHandItem.enchantmentLevel(ClearSkyLoveSongEnchantment)
            if (level == 0) return
            if (!attacker.level().canSeeSky(attacker.blockPosition())) return // 如果攻击者的位置无法看到天空则无法触发效果
            if (!event.entity.level().isRaining && !event.entity.level().isThundering) {
                event.amount += 0.5f + 0.75f * level
            } else {
                event.amount -= 0.6f * level
                if (Math.random() < level * 0.03f) {
                    // 设置天气为晴天
                    (attacker.level() as ServerLevel).setWeatherParameters(1600, 0, false, false)
                }
            }
        }
    }

    /**
     * 露雨恩赐
     */
    @SubscribeEvent
    fun doRainBlessingEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val level = attacker.mainHandItem.enchantmentLevel(RainBlessingEnchantment)
            if (level == 0) return
            if (!attacker.level().canSeeSky(attacker.blockPosition())) return
            if (attacker.level().isRaining) {
                event.amount += 0.8f * level + 0.2f
            } else {
                event.amount -= 0.3f * level + 0.2f
                if (Math.random() < level * 0.03f) {
                    // 设置天气为下雨天 持续半分钟
                    (attacker.level() as ServerLevel).setWeatherParameters(0, 600, true, false)
                }
            }
        }
    }
}