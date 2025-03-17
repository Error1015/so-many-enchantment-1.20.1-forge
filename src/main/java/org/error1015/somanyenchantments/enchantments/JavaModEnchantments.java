package org.error1015.somanyenchantments.enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.error1015.somanyenchantments.EnchantmentsMain;
import org.error1015.somanyenchantments.enchantments.tool.AutoSmelt;

public class JavaModEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EnchantmentsMain.MODID);
    public static final RegistryObject<Enchantment> AUTO_SMELT = REGISTRY.register("auto_smelt", AutoSmelt::new);
}
