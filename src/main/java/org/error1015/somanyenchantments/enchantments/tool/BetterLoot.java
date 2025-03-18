package org.error1015.somanyenchantments.enchantments.tool;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.error1015.somanyenchantments.config.EnchantmentsConfig;
import org.error1015.somanyenchantments.init.EnchantmentInit;
import org.jetbrains.annotations.NotNull;

public class BetterLoot extends Enchantment {
    public BetterLoot(EquipmentSlot... slots) {
        super(EnchantmentsConfig.getRarityByConfig(EnchantmentInit.betterLoot), EnchantmentCategory.WEAPON, slots);
    }

    @Override
    public boolean isTreasureOnly() {
        return EnchantmentsConfig.isTreasure(EnchantmentInit.betterLoot);
    }
    @Override
    public boolean isDiscoverable() {
        return EnchantmentsConfig.couldFound(EnchantmentInit.betterLoot);
    }

    @Override
    public boolean isTradeable() {
        return EnchantmentsConfig.couldTrade(EnchantmentInit.betterLoot);
    }
    @Override
    public int getMaxLevel() {
        return EnchantmentsConfig.getMaxLevel(EnchantmentInit.betterLoot);
    }
    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EnchantmentsConfig.couldEnchantTable(EnchantmentInit.betterLoot) && super.canApplyAtEnchantingTable(stack);
    }
    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return pStack.getItem() instanceof SwordItem && EnchantmentsConfig.couldAnvil(EnchantmentInit.betterLoot);
    }
}
