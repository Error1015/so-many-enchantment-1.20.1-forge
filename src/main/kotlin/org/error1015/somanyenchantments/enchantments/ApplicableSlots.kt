package org.error1015.somanyenchantments.enchantments

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Vanishable
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.level.block.Block
import org.error1015.somanyenchantments.MODID
import kotlin.collections.toTypedArray

object ApplicableSlots {
    val ARMOR = arrayOf(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
    val WEAPON = arrayOf(EquipmentSlot.MAINHAND)
    val ALL = EquipmentSlot.entries.toTypedArray()

    val EVERYTHING: EnchantmentCategory
        get() = EnchantmentCategory.create("$MODID:everything") {
            it is Vanishable || Block.byItem(it) is Vanishable || EnchantmentCategory.BREAKABLE.canEnchant(it) || EnchantmentCategory.WEARABLE.canEnchant(it)
        }
}