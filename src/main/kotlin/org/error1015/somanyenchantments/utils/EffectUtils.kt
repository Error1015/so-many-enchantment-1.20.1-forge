package org.error1015.somanyenchantments.utils

import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import kotlin.random.Random

/**
 * 从实体身上获取随机一个Debuff
 */
val LivingEntity.randomDebuff: MobEffectInstance
    get() = debuffs[Random.nextInt(0, debuffs.size)]

/**
 * 从实体身上获取Debuff
 */
val LivingEntity.debuffs: List<MobEffectInstance>
    get() = activeEffects.asSequence().filter { it.effect.category == MobEffectCategory.HARMFUL }.toList()