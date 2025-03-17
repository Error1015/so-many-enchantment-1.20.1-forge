package org.error1015.somanyenchantments.enchantments.curse

import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots

object UnpredictableEnchantment: Enchantment(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, ApplicableSlots.WEAPON) {
    override fun getMaxLevel() = 2

    override fun isCurse() = true
}