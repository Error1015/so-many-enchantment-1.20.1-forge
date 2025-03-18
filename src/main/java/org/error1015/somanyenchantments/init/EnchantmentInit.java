package org.error1015.somanyenchantments.init;

import java.util.List;

public class EnchantmentInit {
    public static final String autoSmelt = "auto_smelt";
    public static final String betterLoot = "better_loot";
    public static List<String> allEnchantments = List.of(autoSmelt, betterLoot);
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
        }
        else if (enchantmentName.equals(betterLoot)) {
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
        }
        return defaultConfig;
    }
}
