package org.error1015.somanyenchantments

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import org.error1015.somanyenchantments.config.EnchantmentsConfig
import org.error1015.somanyenchantments.enchantments.ModEnchantments.Enchantments
import org.error1015.somanyenchantments.enchantments.RegisterEnchantments
import org.error1015.somanyenchantments.event.EventHandler
import org.error1015.somanyenchantments.init.EnchantmentInit
import thedarkcolour.kotlinforforge.forge.MOD_BUS

const val MODID = "somanyenchantments"

@Mod(MODID)
object SoManyEnchantments {
    init {
        Enchantments.register(MOD_BUS)
        RegisterEnchantments.REGISTRY.register(MOD_BUS)
        MinecraftForge.EVENT_BUS.register(EventHandler())
        EnchantmentInit.allEnchantments.forEach { enchantmentName -> EnchantmentsConfig.loadConfig(MODID, enchantmentName) }
    }
}