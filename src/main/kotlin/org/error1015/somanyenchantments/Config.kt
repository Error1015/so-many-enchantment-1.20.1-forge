package org.error1015.somanyenchantments

import net.minecraftforge.common.ForgeConfigSpec

object Config {
    private val builder = ForgeConfigSpec.Builder()

    val piercingEnchantment: ForgeConfigSpec.BooleanValue = builder.comment("符文: 穿刺").define("piercing", true)
    val lifeStealEnchantment: ForgeConfigSpec.BooleanValue = builder.comment("生命窃取").define("life steal", true)
    val blessSwordEnchantment: ForgeConfigSpec.BooleanValue = builder.comment("祝福之刃").define("bless sword", true)
    val spec: ForgeConfigSpec = builder.build()
}