package org.error1015.somanyenchantments.enchantments.runeword

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots

object PiercingEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, ApplicableSlots.WEAPON) {
    override fun getMaxLevel() = 4
}