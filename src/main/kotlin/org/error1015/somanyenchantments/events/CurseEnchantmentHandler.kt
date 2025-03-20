package org.error1015.somanyenchantments.events

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries
import org.error1015.somanyenchantments.enchantments.curse.FrailtyCurseEnchantment
import org.error1015.somanyenchantments.enchantments.curse.SealedCurseEnchantment
import org.error1015.somanyenchantments.enchantments.curse.UnpredictableEnchantment
import org.error1015.somanyenchantments.utils.*
import org.error1015.somanyenchantments.utils.enchantmentLevel
import org.error1015.somanyenchantments.utils.getArmorEnchantmentsSum
import org.error1015.somanyenchantments.utils.isItemEnchanted
import kotlin.random.Random

@Mod.EventBusSubscriber
object CurseEnchantmentHandler {
    @SubscribeEvent
    fun doUnpredictableEnchantmentEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is Player) {
            val player = event.source.entity as Player
            if (player.mainHandItem.isItemEnchanted(UnpredictableEnchantment)) {
                val level = player.mainHandItem.enchantmentLevel(UnpredictableEnchantment)
                if (level == 0) return
                val value = Random.nextFloat() + 1.25f * level * event.amount
                // 46%的概率给目标回复血量 54%的概率给目标造成伤害
                if (Math.random() <= 0.46) {
                    event.entity.heal(value)
                    event.isCanceled = true
                } else {
                    event.amount = value + 2
                }
            }
        }
    }

    @SubscribeEvent
    fun doFrailtyCurseEnchantmentEvent(event: LivingDamageEvent) {
        if (event.entity.level().isClientSide) return
        if (event.entity is Player) {
            val player = event.entity as Player
            val level = player.getArmorEnchantmentsSum(FrailtyCurseEnchantment)
            if (level == 0) return
            event.amount += level * 0.4f
        }
    }

    /**
     * 符文：密封诅咒
     */
    @SubscribeEvent
    fun doSealedCurseEnchantmentEvent(event: LivingAttackEvent) {
        if (event.entity.level().isClientSide) return
        if (event.source.entity is LivingEntity) {
            val attacker = event.source.entity as LivingEntity
            val target = event.entity
            // 如果攻击者的主手物品有密封诅咒，则给目标装备一个随机诅咒
            if (attacker.mainHandItem.isItemEnchanted(SealedCurseEnchantment)) {
                if (Math.random() < 0.2) {
                    val armors = mutableListOf<ItemStack>() // 目标的护甲List
                    target.armorSlots.forEach { armors.add(it) }
                    val curses = ForgeRegistries.ENCHANTMENTS.asSequence().filter { it.isCurse }.toList() // 已经注册的诅咒附魔
                    val curseIndex = Random.nextInt(0, curses.size - 1) // 随机诅咒索引
                    val armorsIndex = Random.nextInt(0, armors.size - 1) // 随机护甲索引
                    val armor = armors[armorsIndex] // 随机到的护甲
                    val enchantment = curses[curseIndex] // 随机到的诅咒附魔
                    val level = Random.nextInt(1, enchantment.maxLevel) // 随机到的附魔等级
                    val armorEnchantments = EnchantmentHelper.getEnchantments(armor) // 目标护甲的附魔Map
                    armorEnchantments.plus(enchantment to level) // 添加附魔
                    EnchantmentHelper.setEnchantments(armorEnchantments, armor) // 修改附魔
                }
            }
        }
    }
}