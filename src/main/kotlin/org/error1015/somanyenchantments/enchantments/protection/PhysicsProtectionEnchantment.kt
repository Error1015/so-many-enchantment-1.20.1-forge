package org.error1015.somanyenchantments.enchantments.protection

import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentCategory
import org.error1015.somanyenchantments.enchantments.ApplicableSlots
import org.error1015.somanyenchantments.enchantments.BasicEnchantment
import org.error1015.somanyenchantments.init.EnchantmentInit

object PhysicsProtectionEnchantment: BasicEnchantment(EnchantmentCategory.ARMOR, ApplicableSlots.ARMOR, EnchantmentInit.physicsProtection) {
    override fun canEnchant(pStack: ItemStack): Boolean {
        return super.canEnchant(pStack) && pStack.item is ArmorItem
    }
}