package org.error1015.somanyenchantments.events

import net.minecraft.world.damagesource.DamageTypes
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.enchantments.protection.MagicProtectionEnchantment
import org.error1015.somanyenchantments.utils.getAllArmorsEnchantmentsTotalLevel

@Mod.EventBusSubscriber
object ProtectionEnchantmentEventHandler {
    /**
     * 魔法保护
     * 降低带有此附魔的玩家的受到的魔法伤害
     * 百科没有提到效果数值
     * 暂定为每级降低0.8点伤害
     * 16级魔法保护4则可以减少12.8点伤害
     */
    @SubscribeEvent
    fun doMagicProtectionEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source == null) return
        if (event.source.`is`(DamageTypes.MAGIC)) {
            val target = event.entity ?: return
            val level = target.getAllArmorsEnchantmentsTotalLevel(MagicProtectionEnchantment)
            if (level == 0) return
            event.amount -= level * 0.8f
        }
    }
}