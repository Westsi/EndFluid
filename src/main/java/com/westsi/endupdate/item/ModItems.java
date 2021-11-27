package com.westsi.endupdate.item;

import com.westsi.endupdate.Endupdate;
import com.westsi.endupdate.item.custom.ModAxeItem;
import com.westsi.endupdate.item.custom.ModHoeItem;
import com.westsi.endupdate.item.custom.ModPickaxeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item ENDERITE = registerItem("enderite",
            new Item(new FabricItemSettings().fireproof().group(ItemGroup.MISC)));

    //reciever special item

    public static final Item RECIEVER_SPECIAL = registerItem("reciever_special",
            new Item(new FabricItemSettings().fireproof().group(ItemGroup.MISC).rarity(Rarity.EPIC)));

    //foods

    public static final Item COOKED_ENDIFIED_PORKCHOP = registerItem("cooked_endified_porkchop",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(10).saturationModifier(0.5f).meat()
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,20*3), 0.6f).build()).group(ItemGroup.FOOD)));

    public static final Item ENDIFIED_PORKCHOP = registerItem("endified_porkchop",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).meat()
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER,20*5), 1f).build()).group(ItemGroup.FOOD)));


    //tools

    public static final Item ENDERITE_SHOVEL = registerItem("enderite_shovel",
            new ShovelItem(ModToolMaterial.ENDERITE, -2, 1,
                    new FabricItemSettings().fireproof().group(ItemGroup.TOOLS)));
    public static final Item ENDERITE_SWORD = registerItem("enderite_sword",
            new SwordItem(ModToolMaterial.ENDERITE,4,-3,
                    new FabricItemSettings().fireproof().group(ItemGroup.COMBAT)));

    public static final Item ENDERITE_AXE = registerItem("enderite_axe",
            new ModAxeItem(ModToolMaterial.ENDERITE, 8, -3f,
                    new FabricItemSettings().fireproof().group(ItemGroup.TOOLS)));
    public static final Item ENDERITE_PICKAXE = registerItem("enderite_pickaxe",
            new ModPickaxeItem(ModToolMaterial.ENDERITE, 0, 1,
                    new FabricItemSettings().fireproof().group(ItemGroup.TOOLS)));
    public static final Item ENDERITE_HOE = registerItem("enderite_hoe",
            new ModHoeItem(ModToolMaterial.ENDERITE, -4, 0,
                    new FabricItemSettings().fireproof().group(ItemGroup.TOOLS)));

    //armour
    public static final Item ENDERITE_HELMET = registerItem("enderite_helmet",
            new ArmorItem(ModArmorMaterial.ENDERITE, EquipmentSlot.HEAD,
                    new FabricItemSettings().fireproof().group(ItemGroup.COMBAT)));
    public static final Item ENDERITE_CHESTPLATE = registerItem("enderite_chestplate",
            new ArmorItem(ModArmorMaterial.ENDERITE, EquipmentSlot.CHEST,
                    new FabricItemSettings().fireproof().group(ItemGroup.COMBAT)));
    public static final Item ENDERITE_LEGGINGS = registerItem("enderite_leggings",
            new ArmorItem(ModArmorMaterial.ENDERITE, EquipmentSlot.LEGS,
                    new FabricItemSettings().fireproof().group(ItemGroup.COMBAT)));
    public static final Item ENDERITE_BOOTS = registerItem("enderite_boots",
            new ArmorItem(ModArmorMaterial.ENDERITE, EquipmentSlot.FEET,
                    new FabricItemSettings().fireproof().group(ItemGroup.COMBAT)));

    //totem
    public static final Item TOTEM_VOIDYING = registerItem("totem_voidying",
            new Item(new FabricItemSettings().fireproof().group(ItemGroup.MISC)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Endupdate.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + Endupdate.MOD_ID);
    }

}
