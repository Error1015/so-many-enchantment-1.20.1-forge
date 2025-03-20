package org.error1015.somanyenchantments.enchantments.fish;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.error1015.somanyenchantments.enchantments.BasicEnchantment;
import org.error1015.somanyenchantments.init.EnchantmentInit;

public class BetterLure extends BasicEnchantment {
    public BetterLure(EquipmentSlot... slots) {
        super(EnchantmentCategory.FISHING_ROD, slots, EnchantmentInit.betterLure);
    }
}
