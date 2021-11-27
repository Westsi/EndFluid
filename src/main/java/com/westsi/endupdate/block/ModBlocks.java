package com.westsi.endupdate.block;

import com.westsi.endupdate.Endupdate;
import com.westsi.endupdate.block.sapling.DeadSaplingBlock;
import com.westsi.endupdate.block.sapling.DeadSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.westsi.endupdate.Endupdate.TREE_DEAD;

public class ModBlocks {

    //new end valuables
    public static final Block ENDERITE_ORE = registerBlock("enderite_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(30f, 1200)
                    .breakByTool(FabricToolTags.PICKAXES, 4).requiresTool()));

    public static final Block ENDERITE_BLOCK = registerBlock("enderite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(50f, 1200)
                    .breakByTool(FabricToolTags.PICKAXES, 4).requiresTool()));

    //end woods
    public static final Block DEAD_WOOD = registerBlock("dead_wood",
            new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2f, 2)
                    .breakByTool(FabricToolTags.AXES)));

    public static final Block DEAD_WOOD_PLANKS = registerBlock("dead_wood_planks",
            new Block(FabricBlockSettings.of(Material.WOOD).strength(2f, 2)
                    .breakByTool(FabricToolTags.AXES)));

    //new end nature blocks
    public static final Block END_DIRT = registerBlock("end_dirt",
            new Block(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRASS).strength(0.5f, 1)
                    .breakByTool(FabricToolTags.SHOVELS)));

    public static final Block END_GRASS = registerBlock("end_grass",
            new Block(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRASS).strength(0.5f, 1)
                    .breakByTool(FabricToolTags.SHOVELS)));

    //dead tree sapling

    public static final DeadSaplingBlock DEAD_SAPLING = new DeadSaplingBlock(new DeadSaplingGenerator(TREE_DEAD), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).nonOpaque().ticksRandomly());


    //end glass
    public static final Block END_GLASS = registerBlock("end_glass",
            new GlassBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(50f, 1200)
                    .breakByTool(FabricToolTags.PICKAXES, 3).requiresTool()));

    //reciever
    public static final Block RECIEVER_BLOCK = registerBlock("reciever_block",
            new RecieverBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(50f, 1200)
                    .breakByTool(FabricToolTags.PICKAXES, 3).requiresTool()));


    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(Endupdate.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(Endupdate.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + Endupdate.MOD_ID);
    }
}
