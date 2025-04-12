package org.error1015.somanyenchantments.enchantments.tool;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.error1015.somanyenchantments.enchantments.BasicEnchantment;
import org.error1015.somanyenchantments.init.EnchantmentInit;
import org.jetbrains.annotations.NotNull;

public class DigCollect extends BasicEnchantment {
    public DigCollect(EquipmentSlot... slots) {
        super(EnchantmentCategory.DIGGER, slots, EnchantmentInit.digCollect);
    }
    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return super.canEnchant(pStack) && pStack.getItem() instanceof DiggerItem;
    }
}