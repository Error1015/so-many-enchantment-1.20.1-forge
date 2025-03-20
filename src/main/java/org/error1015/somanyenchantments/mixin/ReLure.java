package org.error1015.somanyenchantments.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.error1015.somanyenchantments.enchantments.RegisterEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel;

@Mixin(EnchantmentHelper.class)
public class ReLure {
    /**
     * @author MCMostWolf
     * @reason 覆盖原版钓鱼速度
     */
    @Overwrite
    public static int getFishingSpeedBonus(ItemStack stack) {
        int original = getItemEnchantmentLevel(Enchantments.FISHING_SPEED, stack);
        int betterLureLevel = 2 * getItemEnchantmentLevel(RegisterEnchantments.BETTER_LURE.get(), stack);
        return Math.min(Math.max(original, betterLureLevel), 5); // 返回叠加后的结果
    }
}
