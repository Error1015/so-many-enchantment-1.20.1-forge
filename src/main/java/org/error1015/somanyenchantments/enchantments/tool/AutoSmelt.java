package org.error1015.somanyenchantments.enchantments.tool;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.error1015.somanyenchantments.enchantments.JavaModEnchantments;
import org.jetbrains.annotations.NotNull;

public class AutoSmelt extends Enchantment {
    public AutoSmelt(EquipmentSlot... slots) {
        super(Rarity.VERY_RARE, EnchantmentCategory.DIGGER, slots);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != Enchantments.SILK_TOUCH;
    }
}