package org.error1015.somanyenchantments

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import org.error1015.somanyenchantments.enchantments.ModEnchantments.Enchantments
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.registerConfig

const val MODID = "somanyenchantments"

@Mod(MODID)
object SoManyEnchantments {
    init {
        Enchantments.register(MOD_BUS)
        registerConfig(ModConfig.Type.COMMON, Config.spec)
    }
}