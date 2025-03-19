package org.error1015.somanyenchantments.enchantments.curse

import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object FrailtyCurseEnchantment : BasicEnchantment(Rarity.UNCOMMON, EnchantmentCategory.ARMOR, ApplicableSlots.ARMOR, EnchantmentInit.frailtyCurse) {
    override fun isCurse() = true
}