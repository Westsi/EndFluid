package com.westsi.endupdate.block.sapling;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class DeadSaplingBlock extends SaplingBlock {
    public DeadSaplingBlock(DeadSaplingGenerator generator, AbstractBlock.Settings settings) {
        super(generator, settings);
    }
}
