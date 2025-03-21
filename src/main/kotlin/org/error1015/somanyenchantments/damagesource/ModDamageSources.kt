package org.error1015.somanyenchantments.damagesource

import net.minecraft.core.RegistryAccess
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.damagesource.DamageType

object ModDamageSources {
    // val registryAccess = Minecraft.getInstance().level?.registryAccess() ?: throw IllegalStateException("Minecraft.getInstance().level is null")
    // val piercingDamageSource = createSimpleDamageSource(ModDamageTypes.PIERCING)


    fun createSimpleDamageSource(registryAccess: RegistryAccess,key: ResourceKey<DamageType>): DamageSource {
        return SimpleDamageSource(registryAccess.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key))
    }
}