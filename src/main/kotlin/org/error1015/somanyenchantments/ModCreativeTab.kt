package org.error1015.somanyenchantments

import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentInstance
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import org.error1015.somanyenchantments.enchantments.ModEnchantments
import org.error1015.somanyenchantments.enchantments.RegisterEnchantments
import thedarkcolour.kotlinforforge.forge.registerObject

object ModCreativeTab {
    val ItemGroup: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID)
    val EnchantedBooks: Collection<ItemStack>
        get() = ModEnchantments.Enchantments.entries.mapToEnchantedBook() + RegisterEnchantments.REGISTRY.entries.mapToEnchantedBook()

    val EnchantmentsTab: CreativeModeTab by ItemGroup.registerObject("somanyenchantments_tab") {
        CreativeModeTab
            .builder()
            .icon { Items.ENCHANTED_BOOK.defaultInstance }
            .title(Component.translatable("itemGroup.somanyenchantments.tab"))
            .displayItems { _, output ->
                output.acceptAll(EnchantedBooks)
            }
            .build()
    }

    private fun Collection<RegistryObject<Enchantment>>.mapToEnchantedBook() = map { registryObj ->
        EnchantedBookItem.createForEnchantment(
            EnchantmentInstance(
                registryObj.get(), registryObj.get().maxLevel
            )
        )
    }
}