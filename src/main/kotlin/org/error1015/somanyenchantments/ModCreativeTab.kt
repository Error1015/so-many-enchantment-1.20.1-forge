package org.error1015.somanyenchantments

import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.EnchantmentInstance
import net.minecraftforge.registries.DeferredRegister
import org.error1015.somanyenchantments.enchantments.ModEnchantments
import org.error1015.somanyenchantments.enchantments.RegisterEnchantments
import thedarkcolour.kotlinforforge.forge.registerObject

object ModCreativeTab {
    val Tab: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID)

    val enchantmentsTab: CreativeModeTab by Tab.registerObject("somanyenchantments_tab") {
        CreativeModeTab
            .builder()
            .icon { Items.ENCHANTED_BOOK.defaultInstance }
            .title(Component.translatable("itemGroup.somanyenchantments.tab"))
            .displayItems { _, output ->
                enchantmentItemStacks().forEach { output.accept(it) }
            }
            .build()
    }

    internal fun enchantmentItemStacks(): List<ItemStack> {
        val enchantmentsList = mutableListOf<ItemStack>()
        // kotlin代码中的附魔注册表
        ModEnchantments.Enchantments.entries.forEach {
            val enchantments = EnchantedBookItem.createForEnchantment(EnchantmentInstance(it.get(), it.get().maxLevel))
            enchantmentsList += enchantments
        }
        // Java代码中的注册表
        RegisterEnchantments.REGISTRY.entries.forEach {
            val enchantments = EnchantedBookItem.createForEnchantment(EnchantmentInstance(it.get(), it.get().maxLevel))
            enchantmentsList += enchantments
        }
        return enchantmentsList
    }
}