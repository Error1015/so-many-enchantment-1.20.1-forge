package org.error1015.somanyenchantments.enchantments;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;
import org.error1015.somanyenchantments.config.EnchantmentsConfig;
import org.error1015.somanyenchantments.init.EnchantmentInit;
import org.jetbrains.annotations.NotNull;

public class BasicEnchantment extends Enchantment {
    private final String enchantName;
    protected BasicEnchantment(EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots, String pName) {
        super(EnchantmentsConfig.getRarityByConfig(EnchantmentInit.purificationBlade), pCategory, pApplicableSlots);
        this.enchantName = pName;
    }

    @Override
    public boolean isTreasureOnly() {
        return EnchantmentsConfig.isTreasure(enchantName);
    }
    @Override
    public boolean isDiscoverable() {
        return EnchantmentsConfig.couldFound(enchantName);
    }

    @Override
    public boolean isTradeable() {
        return EnchantmentsConfig.couldTrade(enchantName);
    }
    @Override
    public int getMaxLevel() {
        return EnchantmentsConfig.getMaxLevel(enchantName);
    }
    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return EnchantmentsConfig.couldEnchantTable(enchantName) && super.canApplyAtEnchantingTable(stack);
    }
    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return EnchantmentsConfig.couldAnvil(enchantName);
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment pOther) {
        boolean result = true;
        for (String location : EnchantmentsConfig.getUnableCompatibility(enchantName)) {
            if (pOther == ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(location))) {
                result = false;
                break;
            }
        }
        return super.checkCompatibility(pOther) && result;
    }
}