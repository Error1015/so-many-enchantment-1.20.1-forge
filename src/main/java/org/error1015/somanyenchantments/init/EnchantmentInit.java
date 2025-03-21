package org.error1015.somanyenchantments.init;

import java.util.List;

public class EnchantmentInit {
    public static final String autoSmelt = "auto_smelt";
    public static final String betterLoot = "better_loot";
    public static final String purificationBlade = "purification_blade";
    public static final String frailtyCurse = "frailty_curse";
    public static final String unpredictable = "unpredictable";
    public static final String revival = "revival";
    public static final String blessSword = "bless_word";
    public static final String lifeSteal = "life_steal";
    public static final String betterLure = "better_lure";
    public static final String betterFishLuck = "better_fish_luck";
    public static final String breakMagic = "break_magic";
    public static final String sealedCurse = "sealed_curse";
    public static final String magicProtection = "magic_protection";
    public static final String clearSkyLoveSong = "clear_sky_love_song";
    public static final String rainBlessing = "rain_blessing";
    public static final String physicsProtection = "physic_protection";
    public static final String rottenCurse = "rotten_curse";
    public static final String speedIsUnbreakable = "speed_is_unbreakable";
    public static final String magicBless = "magic_bless";

    public static List<String> allEnchantments = List.of(autoSmelt, betterLoot, purificationBlade, frailtyCurse, unpredictable, revival, blessSword, lifeSteal, betterLure, betterFishLuck, breakMagic, sealedCurse, magicProtection, rainBlessing, clearSkyLoveSong, physicsProtection, rottenCurse, speedIsUnbreakable, magicBless);

    private static String defaultConfig;

    public static String getDefaultConfig(String enchantmentName) {
        switch (enchantmentName) {
            case autoSmelt -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":true,
                            "maxLevel":1,
                            "quality":3,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["minecraft:silk_touch"]
                        }
                    ]""", enchantmentName);
            case betterLoot -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":false,
                            "couldFound":true,
                            "maxLevel":3,
                            "quality":2,
                            "couldEnchantTable":true,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]""", enchantmentName);
            case purificationBlade -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":5,
                            "quality":3,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]""", enchantmentName);
            case frailtyCurse -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":4,
                            "quality":3,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);
            case unpredictable -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":2,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);

            case revival -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":4,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);
            case blessSword -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":5,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["somanyenchantments:life_steal"]
                        }
                    ]
                    """, enchantmentName);
            case lifeSteal -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":5,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["somanyenchantments:bless_sword"]
                        }
                    ]
                    """, enchantmentName);
            case betterLure -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":3,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["minecraft:lure"]
                        }
                    ]
                    """, enchantmentName);
            case betterFishLuck -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":3,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);

            case breakMagic -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":5,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["somanyenchantments:bless_word"]
                        }
                    ]
                    """, enchantmentName);

            case sealedCurse -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":1,
                            "quality":3,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);

            case magicProtection -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":false,
                            "couldFound":true,
                            "maxLevel":5,
                            "quality":2,
                            "couldEnchantTable":true,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);

            case clearSkyLoveSong -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":false,
                            "couldFound":false,
                            "maxLevel":6,
                            "quality":2,
                            "couldEnchantTable":true,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["somanyenchantments:rain_blessing"]
                        }
                    ]
                    """, enchantmentName);

            case rainBlessing -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":false,
                            "couldFound":false,
                            "maxLevel":6,
                            "quality":2,
                            "couldEnchantTable":true,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["somanyenchantments:clear_sky_love_song"]
                        }
                    ]
                    """, enchantmentName);

            case physicsProtection -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":false,
                            "couldFound":true,
                            "maxLevel":4,
                            "quality":2,
                            "couldEnchantTable":true,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);

            case rottenCurse -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":true,
                            "maxLevel":1,
                            "quality":3,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);

            case speedIsUnbreakable -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":true,
                            "maxLevel":5,
                            "quality":3,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":["minecraft:knockback","somanyenchantments:bless_word"]
                        }
                    ]
                    """, enchantmentName);

            case magicBless -> defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":false,
                            "couldFound":true,
                            "maxLevel":4,
                            "quality":2,
                            "couldEnchantTable":false,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);
        }
        return defaultConfig;
    }
}