package org.error1015.somanyenchantments.enchantments.weapon

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object BlessSwordEnchantment : BasicEnchantment(EnchantmentCategory.WEAPON,ApplicableSlots.WEAPON, EnchantmentInit.blessSword) {
    override fun canEnchant(pStack: ItemStack): Boolean {
        return super.canEnchant(pStack) && pStack.item is SwordItem
    }
}