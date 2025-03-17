package org.error1015.somanyenchantments.enchantments.curse

import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots

object FrailtyCurseEnchantment : Enchantment(Rarity.UNCOMMON, EnchantmentCategory.ARMOR, ApplicableSlots.ARMOR) {
    override fun getMaxLevel() = 4

    override fun isCurse() = true
}