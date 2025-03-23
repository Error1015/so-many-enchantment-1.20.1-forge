package org.error1015.somanyenchantments.enchantments.weather

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object ClearSkyLoveSongEnchantment: BasicEnchantment(EnchantmentCategory.WEAPON, ApplicableSlots.WEAPON, EnchantmentInit.clearSkyLoveSong) {
    override fun canEnchant(pStack: ItemStack): Boolean {
        return super.canEnchant(pStack) && pStack.item is SwordItem
    }
}