package org.error1015.somanyenchantments.utils

import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.damagesource.DamageTypes

/**
 * 判断属于近战攻击的伤害源
 */
fun DamageSource.isLivingEntityAttack(): Boolean =
     type() == DamageTypes.MOB_ATTACK || type() == DamageTypes.PLAYER_ATTACK