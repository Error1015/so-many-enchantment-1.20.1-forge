package org.error1015.somanyenchantments.enchantments.weapon

import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots

object LifeStealEnchantment: Enchantment(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, ApplicableSlots.WEAPON) {
    override fun getMaxLevel() = 4

    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && pOther != BlessSwordEnchantment
    }
}