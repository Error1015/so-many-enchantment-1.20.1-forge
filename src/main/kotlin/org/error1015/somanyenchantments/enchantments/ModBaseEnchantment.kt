package org.error1015.somanyenchantments.enchantments

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

abstract class ModBaseEnchantment(pRarity: Rarity, pCategory: EnchantmentCategory, pApplicableSlots: Array<EquipmentSlot>): Enchantment(pRarity, pCategory,pApplicableSlots) {

}