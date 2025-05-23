package org.error1015.somanyenchantments.enchantments.runeword

import net.minecraft.world.item.DiggerItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object RevivalEnchantment : BasicEnchantment(EnchantmentCategory.DIGGER, ApplicableSlots.WEAPON, EnchantmentInit.revival) {
    override fun canEnchant(pStack: ItemStack): Boolean {
        return super.canEnchant(pStack) && pStack.item is DiggerItem
    }
}