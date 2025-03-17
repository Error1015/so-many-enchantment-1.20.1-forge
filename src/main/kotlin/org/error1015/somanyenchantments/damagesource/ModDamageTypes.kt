package org.error1015.somanyenchantments.damagesource

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.damagesource.DamageType
import org.error1015.somanyenchantments.MODID

object ModDamageTypes {
    val PIERCING = createResourceKey("piercing")

    fun createResourceKey(path: String): ResourceKey<DamageType> {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation(MODID, path))
    }
}