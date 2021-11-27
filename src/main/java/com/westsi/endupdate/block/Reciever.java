package com.westsi.endupdate.block;

import com.westsi.endupdate.Endupdate;
import com.westsi.endupdate.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class Reciever extends BlockEntity implements ImplementedInventory {

    // Store the current value of the number
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public Reciever(BlockPos pos, BlockState state) {
        super(Endupdate.RECIEVER, pos, state);
    }


    // Serialize the BlockEntity
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        return super.writeNbt(nbt);
    }
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}
