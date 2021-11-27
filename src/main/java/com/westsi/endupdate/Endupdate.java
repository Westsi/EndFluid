package com.westsi.endupdate;

import com.westsi.endupdate.block.ModBlocks;
import com.westsi.endupdate.block.Reciever;
import com.westsi.endupdate.enchants.EndSlayer;
import com.westsi.endupdate.fluids.BloodFluid;
import com.westsi.endupdate.item.ModItems;
import com.westsi.endupdate.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.heightprovider.TrapezoidHeightProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import static com.westsi.endupdate.block.ModBlocks.DEAD_SAPLING;


public class Endupdate implements ModInitializer {

    public static final String MOD_ID = "endupdate";

    //ore gen
    private static ConfiguredFeature<?, ?> ORE_ENDERITE_END = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.ENDERITE_ORE.getDefaultState(),
                    3)) // Vein size
            .range(new RangeDecoratorConfig(
                    // You can also use one of the other height providers if you don't want a uniform distribution
                    TrapezoidHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64), 30))) // Inclusive min and max height
            .spreadHorizontally()
            .repeat(2); // Number of veins per chunk

    //enchant
    public static Enchantment ENDSLAYER = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("endupdate", "end_slayer"),
            new EndSlayer()
    );

    //surface builder
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> ENDGRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    ModBlocks.END_GRASS.getDefaultState(),
                    ModBlocks.END_DIRT.getDefaultState(),
                    Blocks.END_STONE.getDefaultState()));

    //biome creation
    private static final Biome ENDPLAINS = createEndPlains();

    private static Biome createEndPlains() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        //   custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addEndMobs(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ENDGRASS_SURFACE_BUILDER);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.THEEND)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.8F)
                .downfall(0.4F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x050533)
                        .fogColor(0xc0d8ff)
                        .skyColor(0x77adff)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
    public static final RegistryKey<Biome> ENDPLAINS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("endupdate", "endplains"));


    //custom tree
    public static final ConfiguredFeature<?, ?> TREE_DEAD = Feature.TREE
            // Configure the feature using the builder
            .configure(new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.DEAD_WOOD.getDefaultState()), // Trunk block provider
                    new StraightTrunkPlacer(4, 3, 0), // places a straight trunk
                    new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), // Foliage block provider
                    new SimpleBlockStateProvider(Blocks.GRASS_BLOCK.getDefaultState()), // Sapling provider; used to determine what blocks the tree can generate on
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2), // places leaves as a blob (radius, offset from trunk, height)
                    new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
            ).build())
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)))
            .spreadHorizontally()
            .applyChance(10); // About a 10% chance to generate per chunk (1/x)

    //reciever and sender
    //reciever
    public static BlockEntityType<Reciever> RECIEVER;


    //fluids
    public static FlowableFluid STILL_BLOOD;
    public static FlowableFluid FLOWING_BLOOD;
    public static Item BLOOD_BUCKET;
    public static Block BLOOD;





    @Override
    public void onInitialize() {




        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModLootTableModifiers.modifyLootTables();
        Registry.register(Registry.BLOCK, new Identifier("endupdate", "dead_sapling"), DEAD_SAPLING);
        Registry.register(Registry.ITEM, new Identifier("endupdate", "dead_sapling"), new BlockItem(DEAD_SAPLING, new FabricItemSettings().group(ItemGroup.MISC)));

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_GLASS , RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DEAD_SAPLING , RenderLayer.getTranslucent());


        RegistryKey<ConfiguredFeature<?, ?>> oreEnderiteEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("endupdate", "ore_enderite_end"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreEnderiteEnd.getValue(), ORE_ENDERITE_END);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, oreEnderiteEnd);

        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("endupdate", "endgrass"), ENDGRASS_SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, ENDPLAINS_KEY.getValue(), ENDPLAINS);

        //biome adding to zones
        TheEndBiomes.addHighlandsBiome(ENDPLAINS_KEY, 2D);
        TheEndBiomes.addMidlandsBiome(ENDPLAINS_KEY, ENDPLAINS_KEY,2D);

        //tree implementation
        RegistryKey<ConfiguredFeature<?, ?>> treeDead = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("endupdate", "tree_dead"));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, treeDead.getValue(), TREE_DEAD);

        // You should use the VEGETAL_DECORATION generation step for trees
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.VEGETAL_DECORATION, treeDead);


        //reciever
        RECIEVER = Registry.register(Registry.BLOCK_ENTITY_TYPE, "endupdate:reciever", FabricBlockEntityTypeBuilder.create(Reciever::new, ModBlocks.RECIEVER_BLOCK).build(null));

        //fluids
        STILL_BLOOD = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "acid"), new BloodFluid.Still());
        FLOWING_BLOOD = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "flowing_acid"), new BloodFluid.Flowing());
        BLOOD_BUCKET = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "acid_bucket"),
                new BucketItem(STILL_BLOOD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

        BLOOD = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "acid"), new FluidBlock(STILL_BLOOD, FabricBlockSettings.copy(Blocks.WATER)){});

    }
}
