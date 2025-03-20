package org.error1015.somanyenchantments.mixin;

import net.minecraft.data.loot.packs.VanillaFishingLoot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.error1015.somanyenchantments.enchantments.RegisterEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel;

@Mixin(EnchantmentHelper.class)
public class ReFishLuck {
    /**
     * @author MCMostWolf
     * @reason 覆盖原版钓鱼海洋眷恋计算
     */
    @Overwrite
    public static int getFishingLuckBonus(ItemStack pStack) {
        int origin = getItemEnchantmentLevel(Enchantments.FISHING_LUCK, pStack);
        int betterFishLuckLevel = 3 * getItemEnchantmentLevel(RegisterEnchantments.BETTER_FISH_LUCK.get(), pStack);
        return Math.max(origin, betterFishLuckLevel);
    }
}
