package org.error1015.somanyenchantments.utils

import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.damagesource.DamageTypes

/**
 * 判断属于近战攻击的伤害源
 */
fun DamageSource.isLivingEntityAttack(): Boolean =
    !this.isIndirect && this.`is`(DamageTypes.MOB_ATTACK) || this.`is`(DamageTypes.PLAYER_ATTACK)