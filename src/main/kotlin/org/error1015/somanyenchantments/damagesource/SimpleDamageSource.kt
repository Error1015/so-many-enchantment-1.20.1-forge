package org.error1015.somanyenchantments.damagesource

import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.entity.LivingEntity

class SimpleDamageSource(holder: Holder<DamageType>): DamageSource(holder) {
    override fun getLocalizedDeathMessage(pLivingEntity: LivingEntity): Component {
        val s = "death.attack.$msgId"
        return Component.translatable(s, pLivingEntity.displayName.string)
    }
}