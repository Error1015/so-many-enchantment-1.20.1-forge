package org.error1015.somanyenchantments.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
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
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.error1015.somanyenchantments.enchantments.JavaModEnchantments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventHandler {
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;

        // 检查是否持有 AutoSmelt 附魔的工具
        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getEnchantmentLevel(JavaModEnchantments.AUTO_SMELT.get()) == 0) return;

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
