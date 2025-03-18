package org.error1015.somanyenchantments.enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.error1015.somanyenchantments.SoManyEnchantmentsKt;
import org.error1015.somanyenchantments.enchantments.tool.AutoSmelt;
import org.error1015.somanyenchantments.enchantments.tool.BetterLoot;
import org.error1015.somanyenchantments.init.EnchantmentInit;

public class JavaModEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SoManyEnchantmentsKt.MODID);
    public static final RegistryObject<Enchantment> AUTO_SMELT = REGISTRY.register(EnchantmentInit.autoSmelt, AutoSmelt::new);
    public static final RegistryObject<Enchantment> BETTER_LOOT = REGISTRY.register(EnchantmentInit.betterLoot, BetterLoot::new);
}