package org.error1015.somanyenchantments.enchantments

import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.error1015.somanyenchantments.MODID
import org.error1015.somanyenchantments.enchantments.curse.FrailtyCurseEnchantment
import org.error1015.somanyenchantments.enchantments.curse.UnpredictableEnchantment
import org.error1015.somanyenchantments.enchantments.runeword.*
import org.error1015.somanyenchantments.enchantments.weapon.*
import thedarkcolour.kotlinforforge.forge.registerObject


object ModEnchantments {
    val Enchantments: DeferredRegister<Enchantment> = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID)

    val Piercing by Enchantments.registerObject("piercing") { PiercingEnchantment }
    val Revival by Enchantments.registerObject("revival") { RevivalEnchantment }

    val LifeSteal by Enchantments.registerObject("life_steal") { LifeStealEnchantment }
    val blessSword by Enchantments.registerObject("bless_sword") { BlessSwordEnchantment }

    val Unpredictable by Enchantments.registerObject("unpredictable") { UnpredictableEnchantment }
    val FrailtyCurse by Enchantments.registerObject("frailty_curse") { FrailtyCurseEnchantment }
}