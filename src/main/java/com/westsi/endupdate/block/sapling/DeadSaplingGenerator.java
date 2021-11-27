package com.westsi.endupdate.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;
import java.util.Random;
import com.westsi.endupdate.Endupdate;

public class DeadSaplingGenerator extends SaplingGenerator {
    private final ConfiguredFeature<TreeFeatureConfig, ?> feature;

    public DeadSaplingGenerator(ConfiguredFeature<?, ?> feature) {
        this.feature = (ConfiguredFeature<TreeFeatureConfig, ?>) feature;
    }

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return feature;
    }
}
