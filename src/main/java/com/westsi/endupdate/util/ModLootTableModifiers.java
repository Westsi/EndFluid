package com.westsi.endupdate.util;

import com.westsi.endupdate.Endupdate;
import com.westsi.endupdate.block.ModBlocks;
import com.westsi.endupdate.enchants.EndSlayer;
import com.westsi.endupdate.item.ModItems;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BookItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier GRASS_BLOCK_ID
            = new Identifier("minecraft", "blocks/grass_block");

    private static final Identifier END_CITY_TREASURE_CHEST_ID
            = new Identifier("minecraft", "chests/end_city_treasure");

    public static void modifyLootTables() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            //check for leaves loot table.
            //if(GRASS_BLOCK_ID.equals(id)) {
                 //Adds enderite to the grass block loot table.
                //FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        //.rolls(ConstantLootNumberProvider.create(1))
                        //.conditionally(RandomChanceLootCondition.builder(0.35f)) // Drops 35% of the time
                       // .with(ItemEntry.builder(ModItems.ENDERITE))
                       // .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
               // supplier.withPool(poolBuilder.build());
           // }

            if(END_CITY_TREASURE_CHEST_ID.equals(id)) {
                // Adds an Ender Debris into the End Treasure Chest with 75% chance.
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time
                        .with(ItemEntry.builder(ModBlocks.ENDERITE_ORE))
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                supplier.withPool(poolBuilder.build());
            }

            if(END_CITY_TREASURE_CHEST_ID.equals(id)) {
                // Adds an Ender Debris into the End Treasure Chest with 75% chance.
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time
                        .with(ItemEntry.builder(Items.BOOK))
                        .withFunction(EnchantRandomlyLootFunction.create().add(Endupdate.ENDSLAYER).build());
                supplier.withPool(poolBuilder.build());
            }
        }));
    }
}
