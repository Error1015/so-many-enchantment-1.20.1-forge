package org.error1015.somanyenchantments.enchantments.curse

import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object RottenCurseEnchantment: BasicEnchantment(ApplicableSlots.EVERYTHING, ApplicableSlots.ALL, EnchantmentInit.rottenCurse) {
    override fun isCurse() = true
}