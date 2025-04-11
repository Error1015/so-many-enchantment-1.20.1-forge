package org.error1015.somanyenchantments.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.error1015.somanyenchantments.enchantments.RegisterEnchantments;
import org.error1015.somanyenchantments.utils.EnchantmentsUtilsKt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    /**
     * 高级饵钓
     * @author MCMostWolf
     * @reason 覆盖原版钓鱼海洋眷恋计算
     */
    @Inject(method = "getFishingLuckBonus", at = @At("HEAD"), cancellable = true)
    private static void getFishingLuckBonus(ItemStack pStack, CallbackInfoReturnable<Integer> cir) {
        if (EnchantmentsUtilsKt.isItemEnchanted(pStack, RegisterEnchantments.BETTER_FISH_LUCK.get())) {
            int enchantmentLevel = EnchantmentsUtilsKt.enchantmentLevel(pStack, RegisterEnchantments.BETTER_FISH_LUCK.get());
            cir.setReturnValue(enchantmentLevel * 3);
        }
    }

    /**
     * 高级海之眷顾
     * @author MCMostWolf
     * @reason 覆盖原版钓鱼速度
     */
    @Inject(method = "getFishingSpeedBonus", at = @At("HEAD"), cancellable = true)
    private static void getFishingSpeedBonus(ItemStack pStack, CallbackInfoReturnable<Integer> cir) {
        if (EnchantmentsUtilsKt.isItemEnchanted(pStack, RegisterEnchantments.BETTER_LURE.get())) {
            int betterLureLevel = EnchantmentsUtilsKt.enchantmentLevel(pStack, RegisterEnchantments.BETTER_LURE.get());
            cir.setReturnValue(Math.min(betterLureLevel, 5));
        }
    }
}