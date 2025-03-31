package org.error1015.somanyenchantments.mixin;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.server.commands.EnchantCommand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.error1015.somanyenchantments.utils.EnchantmentsUtilsKt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

// @Mixin(EnchantCommand.class)
// public abstract class EnchantCommandMixin {
//     @Inject(at = @At("HEAD"), method = "enchant", cancellable = true)
//     private static void enchant(CommandSourceStack pSource, Collection<? extends Entity> pTargets, Holder<Enchantment> pEnchantment, int pLevel, CallbackInfoReturnable<Integer> cir) {
//         var enchantment = pEnchantment.value();
//
//         int i = 0;
//         for (Entity entity : pTargets) {
//             if (entity instanceof LivingEntity livingEntity) {
//                 ItemStack itemstack = livingEntity.getMainHandItem();
//                 if (!itemstack.isEmpty()) {
//                     // 如果已经附魔了这个附魔
//                     if (EnchantmentsUtilsKt.isItemEnchanted(itemstack, enchantment)) {
//                         int level = itemstack.getEnchantmentLevel(enchantment);
//                         int BiggerLevel = Math.max(level, pLevel);
//                         itemstack.getAllEnchantments().replace(enchantment, BiggerLevel);
//                     }
//                     itemstack.enchant(enchantment, pLevel);
//                     ++i;
//                 }
//             }
//         }
//         cir.setReturnValue(i);
//     }
// }