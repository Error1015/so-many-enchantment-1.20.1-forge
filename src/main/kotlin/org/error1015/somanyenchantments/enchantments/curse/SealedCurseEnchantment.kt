package org.error1015.somanyenchantments.enchantments.curse

import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object SealedCurseEnchantment : BasicEnchantment(EnchantmentCategory.WEAPON, ApplicableSlots.WEAPON, EnchantmentInit.sealedCurse) {
    override fun getMaxLevel() = 1

    override fun isCurse() = true
}