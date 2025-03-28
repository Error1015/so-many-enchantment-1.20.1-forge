package org.error1015.somanyenchantments.enchantments

import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.error1015.somanyenchantments.MODID
import org.error1015.somanyenchantments.enchantments.curse.*
import org.error1015.somanyenchantments.enchantments.protection.*
import org.error1015.somanyenchantments.enchantments.runeword.*
import org.error1015.somanyenchantments.enchantments.weapon.*
import org.error1015.somanyenchantments.enchantments.weather.*
import org.error1015.somanyenchantments.init.EnchantmentInit
import thedarkcolour.kotlinforforge.forge.registerObject


object ModEnchantments {
    internal val Enchantments: DeferredRegister<Enchantment> = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID)

    // 工具
    val Revival by Enchantments.registerObject("revival") { RevivalEnchantment }

    // 战斗
    val LifeSteal by Enchantments.registerObject("life_steal") { LifeStealEnchantment }
    val blessSword by Enchantments.registerObject("bless_sword") { BlessSwordEnchantment }
    val breakMagic by Enchantments.registerObject(EnchantmentInit.breakMagic) { BreakMagicEnchantment }
    val SpeedIsUnbreakable by Enchantments.registerObject(EnchantmentInit.speedIsUnbreakable) { SpeedIsUnbreakableEnchantment }
    val MagicBless by Enchantments.registerObject(EnchantmentInit.magicBless) { MagicBlessEnchantment }
    val Antidote by Enchantments.registerObject(EnchantmentInit.antidote) { AntidoteEnchantment }

    // 诅咒
    val Unpredictable by Enchantments.registerObject("unpredictable") { UnpredictableEnchantment }
    val FrailtyCurse by Enchantments.registerObject("frailty_curse") { FrailtyCurseEnchantment }
    val SealedCurse by Enchantments.registerObject(EnchantmentInit.sealedCurse) { SealedCurseEnchantment }
    val RottenCurse by Enchantments.registerObject(EnchantmentInit.rottenCurse) { RottenCurseEnchantment }
    val MagicCurse by Enchantments.registerObject(EnchantmentInit.magicCurse) { MagicCurseEnchantment }

    // 保护
    val MagicProtection by Enchantments.registerObject(EnchantmentInit.magicProtection) { MagicProtectionEnchantment }
    val PhysicsProtection by Enchantments.registerObject(EnchantmentInit.physicsProtection) { PhysicsProtectionEnchantment }

    // 天气
    val ClearSkyLoveSong by Enchantments.registerObject(EnchantmentInit.clearSkyLoveSong) { ClearSkyLoveSongEnchantment }
    val RainBlessing by Enchantments.registerObject(EnchantmentInit.rainBlessing) { RainBlessingEnchantment }
}