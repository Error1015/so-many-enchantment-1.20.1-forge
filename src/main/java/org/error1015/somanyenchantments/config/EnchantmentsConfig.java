package org.error1015.somanyenchantments.config;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.loading.FMLPaths;
import org.error1015.somanyenchantments.init.EnchantmentInit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantmentsConfig {
    private static final Map<String, Boolean> isTreasureMap = new HashMap<>();
    private static final Map<String, Boolean> couldFoundMap = new HashMap<>();
    private static final Map<String, Integer> maxLevelMap = new HashMap<>();
    private static final Map<String, Integer> qualityMap = new HashMap<>();
    private static final Map<String, Boolean> couldEnchantTableMap = new HashMap<>();
    private static final Map<String, Boolean> couldAnvilMap = new HashMap<>();
    private static final Map<String, Boolean> couldTradeMap = new HashMap<>();
    private static final Map<String, List<String>> unableCompatibilityMap = new HashMap<>();
    public static void loadConfig(String modId, String enchantmentName) {
        Gson gson = new Gson();
        Path configDir = FMLPaths.CONFIGDIR.get().resolve(modId).resolve(enchantmentName);
        Path configPath = configDir.resolve("enchantmentConfig.json");
        try {
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }
            if (!Files.exists(configPath)) {
                System.out.println("Config file does not exist, creating a default one.");
                createDefaultConfig(configPath, enchantmentName);
            }
            String content = Files.readString(configPath);

            List<EnchantmentConfig> configs = gson.fromJson(content, new TypeToken<List<EnchantmentConfig>>(){}.getType());

            for (EnchantmentConfig config : configs) {
                String key = config.enchantmentName;
                isTreasureMap.put(key, config.isTreasure);
                couldFoundMap.put(key, config.couldFound);
                maxLevelMap.put(key, config.maxLevel);
                qualityMap.put(key, config.quality);
                couldEnchantTableMap.put(key, config.couldEnchantTable);
                couldAnvilMap.put(key, config.couldAnvil);
                couldTradeMap.put(key, config.couldTrade);
                List<String> compatibility = config.unableCompatibility != null
                        ? config.unableCompatibility
                        : List.of();
                unableCompatibilityMap.put(key, compatibility);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDefaultConfig(Path configPath, String enchantmentName) throws IOException {
        String defaultContent = EnchantmentInit.getDefaultConfig(enchantmentName);
        Files.writeString(configPath, defaultContent);
    }
    public static boolean isTreasure(String enchantmentName) {
        return isTreasureMap.getOrDefault(enchantmentName, false);
    }
    public static boolean couldFound(String enchantmentName) {
        return couldFoundMap.getOrDefault(enchantmentName, false);
    }
    public static int getMaxLevel(String enchantmentName) {
        return maxLevelMap.getOrDefault(enchantmentName, 1);
    }

    public static boolean couldEnchantTable(String enchantmentName) {
        return couldEnchantTableMap.getOrDefault(enchantmentName, false);
    }

    public static boolean couldAnvil(String enchantmentName) {
        return couldAnvilMap.getOrDefault(enchantmentName, false);
    }
    public static boolean couldTrade(String enchantmentName) {
        return couldTradeMap.getOrDefault(enchantmentName, false);
    }
    public static List<String> getUnableCompatibility(String enchantmentName) {
        return unableCompatibilityMap.getOrDefault(enchantmentName, List.of());
    }

    public static Enchantment.Rarity getRarityByConfig(String enchantmentName) {
        return switch (qualityMap.getOrDefault(enchantmentName, 0)) {
            case 1 -> Enchantment.Rarity.UNCOMMON;
            case 2 -> Enchantment.Rarity.RARE;
            case 3 -> Enchantment.Rarity.VERY_RARE;
            default -> Enchantment.Rarity.COMMON;
        };
    }
}
