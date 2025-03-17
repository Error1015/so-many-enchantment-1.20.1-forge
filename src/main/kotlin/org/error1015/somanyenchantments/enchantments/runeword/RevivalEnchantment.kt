package org.error1015.somanyenchantments.enchantments.runeword

import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots

object RevivalEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentCategory.DIGGER, ApplicableSlots.WEAPON) {
    override fun getMaxLevel() = 2
}