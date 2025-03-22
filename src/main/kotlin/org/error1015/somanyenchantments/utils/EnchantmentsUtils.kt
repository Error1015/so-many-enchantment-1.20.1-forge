package org.error1015.somanyenchantments.utils

import net.minecraft.world.entity.EquipmentSlot.*
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper

var LivingEntity.helmet: ItemStack
    get() = getItemBySlot(HEAD)
    set(value) = setItemSlot(HEAD, value)

var LivingEntity.chestplate: ItemStack
    get() = getItemBySlot(CHEST)
    set(value) {
        setItemSlot(CHEST, value)
    }

var LivingEntity.leggings: ItemStack
    get() = getItemBySlot(LEGS)
    set(value) {
        setItemSlot(LEGS, value)
    }

var LivingEntity.boots: ItemStack
    get() = getItemBySlot(FEET)
    set(value) {
        setItemSlot(FEET, value)
    }

/**
 * 获取ItemStack的指定附魔等级
 */
fun ItemStack.enchantmentLevel(enchantment: Enchantment) = allEnchantments[enchantment] ?: 0

/**
 * 如果ItemStack的所有附魔中包含指定附魔则返回true
 */
fun ItemStack.isItemEnchanted(enchantment: Enchantment) = enchantment in allEnchantments

/**
 * 给ItemStack添加附魔
 * @param value 添加附魔的键值对
 */
fun ItemStack.addEnchantments(value: Pair<Enchantment, Int>) {
    val enchantments = EnchantmentHelper.getEnchantments(this)
    enchantments += value
    EnchantmentHelper.setEnchantments(enchantments, this)
}

/**
 * 获取玩家背包所有带有某个附魔的物品
 */
fun Player.getItemFromEnchantment(enchantment: Enchantment) = inventory.items.asSequence().filter { it.isItemEnchanted(enchantment) }

/**
 * 获取玩家背包中所有附魔物品
 */
fun Player.getAllEnchantmentItems() = inventory.items.asSequence().filter { it.isEnchanted }

/**
 * 获取玩家护甲栏的所有装备的某一附魔等级总和
 */
fun LivingEntity.getAllArmorsEnchantmentsTotalLevel(enchantment: Enchantment): Int {
    var level = 0
    armorSlots.asSequence().filter { it.isItemEnchanted(enchantment) }.forEach { level += it.enchantmentLevel(enchantment) }
    return level
}