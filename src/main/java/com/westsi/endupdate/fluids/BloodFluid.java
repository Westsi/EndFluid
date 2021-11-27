package com.westsi.endupdate.fluids;

import com.westsi.endupdate.Endupdate;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class BloodFluid extends AbstractBloodFluid {
    @Override
    public Fluid getStill() {
        return Endupdate.STILL_BLOOD;
    }

    @Override
    public Fluid getFlowing() {
        return Endupdate.FLOWING_BLOOD;
    }

    @Override
    public Item getBucketItem() {
        return Endupdate.BLOOD_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return Endupdate.BLOOD.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends BloodFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends BloodFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
