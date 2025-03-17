package org.error1015.somanyenchantments.enchantments.tool;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AutoSmelt extends Enchantment {
    public AutoSmelt(EquipmentSlot... slots) {
        super(Enchantment.Rarity.COMMON, EnchantmentCategory.DIGGER, slots);
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
        return false;
    }
}
