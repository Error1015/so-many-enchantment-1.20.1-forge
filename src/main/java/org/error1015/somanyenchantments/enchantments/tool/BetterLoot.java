package org.error1015.somanyenchantments.enchantments.tool;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BetterLoot extends Enchantment {
    public BetterLoot(EquipmentSlot... slots) {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, slots);
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
