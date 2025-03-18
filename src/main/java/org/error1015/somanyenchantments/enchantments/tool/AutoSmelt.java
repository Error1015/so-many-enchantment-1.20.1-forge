package org.error1015.somanyenchantments.enchantments.tool;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;
import org.error1015.somanyenchantments.config.EnchantmentsConfig;
import org.error1015.somanyenchantments.init.EnchantmentInit;
import org.jetbrains.annotations.NotNull;

public class AutoSmelt extends Enchantment {
    public AutoSmelt(EquipmentSlot... slots) {
        super(EnchantmentsConfig.getRarityByConfig(EnchantmentInit.autoSmelt), EnchantmentCategory.DIGGER, slots);
    }

    @Override
    public boolean isTreasureOnly() {
        return EnchantmentsConfig.isTreasure(EnchantmentInit.autoSmelt);
    }

    @Override
    public boolean isDiscoverable() {
        return EnchantmentsConfig.couldFound(EnchantmentInit.autoSmelt);
    }

    @Override
    public boolean isTradeable() {
        return EnchantmentsConfig.couldTrade(EnchantmentInit.autoSmelt);
    }

    @Override
    public int getMaxLevel() {
        return EnchantmentsConfig.getMaxLevel(EnchantmentInit.autoSmelt);
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return super.canApplyAtEnchantingTable(stack) && EnchantmentsConfig.couldEnchantTable(EnchantmentInit.autoSmelt);
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return pStack.getItem() instanceof DiggerItem && EnchantmentsConfig.couldAnvil(EnchantmentInit.autoSmelt);
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment pOther) {
        boolean result = true;
        for (String location : EnchantmentsConfig.getUnableCompatibility(EnchantmentInit.autoSmelt)) {
            if (pOther == ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(location))) {
                result = false;
                break;
            }
        }
        return super.checkCompatibility(pOther) && result;
    }
}