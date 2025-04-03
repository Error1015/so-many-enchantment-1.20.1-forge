package org.error1015.somanyenchantments.events

import net.minecraft.world.damagesource.DamageTypes
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.enchantments.protection.MagicProtectionEnchantment
import org.error1015.somanyenchantments.enchantments.protection.PhysicsProtectionEnchantment
import org.error1015.somanyenchantments.utils.getAllArmorsEnchantmentsTotalLevel
import org.error1015.somanyenchantments.utils.isLivingEntityAttack

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

    /**
     * 物理保护
     * 降低带有此附魔的玩家收到的近战攻击伤害
     */
    @SubscribeEvent
    fun doPhysicsProtectionEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        // 判断伤害来源是否是近战攻击
        if (event.source.isLivingEntityAttack()) {
            val target = event.entity ?: return
            val level = target.getAllArmorsEnchantmentsTotalLevel(PhysicsProtectionEnchantment)
            if (level <= 0) return
            event.amount -= level * 0.5f
        }
    }
}