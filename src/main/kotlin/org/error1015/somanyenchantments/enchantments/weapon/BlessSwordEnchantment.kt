package org.error1015.somanyenchantments.enchantments.weapon

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots

object BlessSwordEnchantment : Enchantment(Rarity.UNCOMMON, EnchantmentCategory.WEAPON,ApplicableSlots.WEAPON) {
    override fun getMaxLevel() = 5

    override fun checkCompatibility(pOther: Enchantment): Boolean {
        return super.checkCompatibility(pOther) && pOther != LifeStealEnchantment
    }
}