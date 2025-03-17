package org.error1015.somanyenchantments;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import org.error1015.somanyenchantments.enchantments.JavaModEnchantments;
import org.error1015.somanyenchantments.event.EventHandler;
import thedarkcolour.kotlinforforge.KotlinModLoadingContext;

@Mod(EnchantmentsMain.MODID)
public class EnchantmentsMain {
    public static final String MODID = "somanyenchantments";
    public EnchantmentsMain()
    {
        IEventBus bus = KotlinModLoadingContext.Companion.get().getKEventBus();
        JavaModEnchantments.REGISTRY.register(bus);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}
