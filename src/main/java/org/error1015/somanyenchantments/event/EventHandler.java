package org.error1015.somanyenchantments.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.error1015.somanyenchantments.enchantments.RegisterEnchantments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class EventHandler {
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;

        // 检查是否持有 AutoSmelt 附魔的工具
        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getEnchantmentLevel(RegisterEnchantments.AUTO_SMELT.get()) == 0) return;

        BlockState state = event.getState();
        Level world = event.getPlayer().level();
        BlockPos pos = event.getPos();
        List<ItemStack> originalDrops = Block.getDrops(state, (ServerLevel) world, pos, null, player, player.getMainHandItem());

        // 替换可熔炼的物品
        List<ItemStack> newDrops = new ArrayList<>();

        for (ItemStack drop : originalDrops) {
            Optional<ItemStack> oSmelted = getSmeltingResult(drop, world);
            if (oSmelted.isPresent()) {
                ItemStack smelted = oSmelted.get();
                if (!smelted.isEmpty()) {
                    int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE, player);
                        double a = fortune + 2;
                        double rn = Math.random();
                        if (rn >= 1/a) {
                            int b = (int) (rn * (fortune+1));
                            for (int i = 0; i < b; i++) {
                                newDrops.add(smelted.copy());
                            }
                        }
                    newDrops.add(smelted.copy());
                } else {
                    newDrops.add(drop);
                }
            }
            else {
                newDrops.add(drop);
            }
        }

        // 清除原掉落物并手动添加新掉落物
        world.destroyBlock(pos, false); // 防止原掉落物生成
        for (ItemStack stack : newDrops) {
            if (!stack.isEmpty()) {
                // 创建物品实体并添加到世界
                ItemEntity itemEntity = new ItemEntity(
                        world,
                        pos.getX() + 0.5D,
                        pos.getY() + 0.5D,
                        pos.getZ() + 0.5D,
                        stack
                );
                world.addFreshEntity(itemEntity);
            }
        }

        // 确保方块被破坏
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11); // 11 是破坏方块的 Flag
    }
    @SubscribeEvent
    public void attackEntity(LootingLevelEvent event) {
        if (event.getDamageSource() != null) {
            if (event.getDamageSource().getEntity() instanceof LivingEntity livingEntity) {
                int betterLoot = livingEntity.getMainHandItem().getEnchantmentLevel(RegisterEnchantments.BETTER_LOOT.get());
                if (betterLoot > 0) {
                    event.setLootingLevel(event.getLootingLevel() + 3 + betterLoot * 2);
                }
            }
            else if (event.getDamageSource().getDirectEntity() instanceof LivingEntity livingEntity) {
                int betterLoot = livingEntity.getMainHandItem().getEnchantmentLevel(RegisterEnchantments.BETTER_LOOT.get());
                if (betterLoot > 0) {
                    event.setLootingLevel(event.getLootingLevel() + 3 + betterLoot * 2);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingHurtEvent event) {
        LivingEntity targetedEntity = event.getEntity();
        Level level = targetedEntity.level();
        DamageSource damageSource = event.getSource();

        LivingEntity attacker = getAttacker(damageSource);
        if (attacker == null) {
            return;
        }
        applyPurificationBladeEffect(targetedEntity, level, damageSource, attacker, event);
    }

    private LivingEntity getAttacker(DamageSource damageSource) {
        if (damageSource.getEntity() instanceof LivingEntity attacker) {
            return attacker;
        } else if (damageSource.getDirectEntity() instanceof LivingEntity attacker) {
            return attacker;
        }
        return null;
    }

    private void applyPurificationBladeEffect(LivingEntity targetedEntity, Level level, DamageSource damageSource, LivingEntity attacker, LivingHurtEvent event) {
        Random RANDOM = new Random(); // 单例随机数生成器
        ItemStack handItem = attacker.getMainHandItem();
        int purificationBladeLevel = handItem.getEnchantmentLevel(RegisterEnchantments.PURIFICATION_BLADE.get());

        if (purificationBladeLevel > 0
                && !damageSource.is(DamageTypes.INDIRECT_MAGIC)
                && !damageSource.is(DamageTypes.MAGIC)) {

            double probability = Math.max(0.0, Math.min(1.0, 0.9 - purificationBladeLevel * 0.06));
            if (RANDOM.nextDouble() > probability) {
                return; // 不满足触发条件
            }

            float damageAmount = event.getAmount();
            event.setAmount(damageAmount * 1.2f); // 增加基础伤害

            // 额外伤害
            targetedEntity.invulnerableTime = 0; // 确保无敌帧被重置
            targetedEntity.hurt(level.damageSources().indirectMagic(attacker, attacker), purificationBladeLevel * 0.75f + 1.25f);

            //下一次攻击无视无敌帧
            targetedEntity.invulnerableTime = 0;

            // 随机清理 Buff
            List<MobEffectInstance> activeEffects = new ArrayList<>(targetedEntity.getActiveEffects());
            if (!activeEffects.isEmpty()) {
                MobEffectInstance effectToRemove = activeEffects.get(RANDOM.nextInt(activeEffects.size()));
                targetedEntity.removeEffect(effectToRemove.getEffect());
            }
        }
    }

    public static Optional<ItemStack> getSmeltingResult(ItemStack input, Level level) {
        RecipeManager recipeManager = level.getRecipeManager();
        RegistryAccess registryAccess = level.registryAccess();

        SimpleContainer inventoryWrapper = new SimpleContainer(input);

        Optional<? extends Recipe<?>> recipe = recipeManager.getRecipeFor(RecipeType.SMELTING, inventoryWrapper, level);

        if (recipe.isPresent() && recipe.get() instanceof AbstractCookingRecipe) {
            return Optional.of(recipe.get().getResultItem(registryAccess));
        } else {
            return Optional.empty();
        }
    }
}