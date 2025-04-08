package org.error1015.somanyenchantments

import net.minecraftforge.common.ForgeConfigSpec

object Config {
    val builder = ForgeConfigSpec.Builder()

    val lifeSteal: ForgeConfigSpec.ConfigValue<Float> = builder.comment("生命窃取附魔每级吸血值").define("life_steal", 0.1f)
    val lifeStealHurtTarget: ForgeConfigSpec.ConfigValue<Int> = builder.comment("生命窃取附魔是否对目标造成伤害倍数,为0则无伤害").define("life_steal_hurt_target", 2)

    val spec: ForgeConfigSpec = builder.build()
}