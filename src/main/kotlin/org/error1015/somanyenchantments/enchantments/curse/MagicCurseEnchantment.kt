package org.error1015.somanyenchantments.enchantments.curse

import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object MagicCurseEnchantment : BasicEnchantment(
    EnchantmentCategory.ARMOR, ApplicableSlots.ARMOR, EnchantmentInit.magicCurse
) {
    override fun isCurse() = true

    override fun canEnchant(pStack: ItemStack): Boolean {
        return super.canEnchant(pStack) && pStack.item is ArmorItem
    }
}