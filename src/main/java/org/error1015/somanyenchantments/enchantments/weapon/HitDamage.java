package org.error1015.somanyenchantments.enchantments.weapon;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.error1015.somanyenchantments.enchantments.BasicEnchantment;
import org.error1015.somanyenchantments.init.EnchantmentInit;
import org.jetbrains.annotations.NotNull;

public class HitDamage extends BasicEnchantment {
    public HitDamage(EquipmentSlot... slots) {
        super(EnchantmentCategory.WEAPON, slots, EnchantmentInit.hitDamage);
    }
    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return super.canEnchant(pStack) && pStack.getItem() instanceof SwordItem;
    }
}
