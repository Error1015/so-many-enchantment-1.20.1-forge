package org.error1015.somanyenchantments.damagesource;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import org.error1015.somanyenchantments.SoManyEnchantmentsKt;
import org.jetbrains.annotations.NotNull;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> HIT_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(SoManyEnchantmentsKt.MODID, "hit_damage"));

    public static @NotNull DamageSource getSourceFromResourceKey(@NotNull Level level, @NotNull ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }
}