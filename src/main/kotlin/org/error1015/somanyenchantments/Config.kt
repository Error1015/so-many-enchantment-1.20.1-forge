package org.error1015.somanyenchantments

import net.minecraftforge.common.ForgeConfigSpec

object Config {
    val builder = ForgeConfigSpec.Builder()

    val lifeSteal: ForgeConfigSpec.ConfigValue<Float> = builder.comment("生命窃取附魔每级吸血值", "默认值: 0.1").define("life_steal", 0.1f)
    val lifeStealHurtTarget: ForgeConfigSpec.ConfigValue<Int> = builder
        .comment("生命窃取附魔是否对目标造成伤害倍数,为0则无伤害", "默认值: 2")
        .define("life_steal_hurt_target", 2)
    val rottenCurse: ForgeConfigSpec.ConfigValue<Int> = builder
        .comment("腐朽诅咒物品掉落在地面上持续时间 单位: 秒", "默认值: 10")
        .define("rotten_curse_time", 10)
    val magicCurseChance: ForgeConfigSpec.ConfigValue<Float> = builder.comment("魔法诅咒触发概率", "默认值: 0.8").define("magicCurseChance", 0.8f)


    val spec: ForgeConfigSpec = builder.build()
}