package org.error1015.somanyenchantments.init;

import java.util.List;

public class EnchantmentInit {
    public static final String autoSmelt = "auto_smelt";
    public static final String betterLoot = "better_loot";
    public static final String purificationBlade = "purification_blade";
    public static final String frailtyCurse = "frailty_curse";
    public static final String unpredictable = "unpredictable";
    public static final String piercing = "piercing";
    public static final String revival = "revival";
    public static final String blessSword = "bless_word";
    public static final String lifesteal = "lifesteal";

    public static List<String> allEnchantments = List.of(
            autoSmelt,
            betterLoot,
            purificationBlade,
            frailtyCurse,
            unpredictable, piercing,
            revival,
            blessSword,
            lifesteal
    );

    private static String defaultConfig;

    public static String getDefaultConfig(String enchantmentName) {
        if (enchantmentName.equals(autoSmelt)) {
            defaultConfig = String.format("""
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
        } else if (enchantmentName.equals(betterLoot)) {
            defaultConfig = String.format("""
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
        } else if (enchantmentName.equals(purificationBlade)) {
            defaultConfig = String.format("""
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
        } else if (enchantmentName.equals(frailtyCurse)) {
            defaultConfig = String.format("""
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
        } else if (enchantmentName.equals(unpredictable)) {
            defaultConfig = String.format("""
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
        } else if (enchantmentName.equals(piercing)) {
            defaultConfig = String.format("""
                    [
                        {
                            "enchantmentName": "%s",
                            "isTreasure":true,
                            "couldFound":false,
                            "maxLevel":4,
                            "quality":3,
                            "couldEnchantTable":true,
                            "couldAnvil":true,
                            "couldTrade":true,
                            "unableCompatibility":[]
                        }
                    ]
                    """, enchantmentName);
        } else if (enchantmentName.equals(revival)) {
            defaultConfig = String.format("""
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
        } else if (enchantmentName.equals(blessSword)) {
            defaultConfig = String.format("""
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
        } else if (enchantmentName.equals(lifesteal)) {
            defaultConfig = String.format("""
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
        }
        return defaultConfig;
    }
}