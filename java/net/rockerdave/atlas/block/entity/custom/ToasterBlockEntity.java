package net.rockerdave.atlas.block.entity.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rockerdave.atlas.block.custom.toaster;
import net.rockerdave.atlas.block.entity.ModBlockEntities;

public class ToasterBlockEntity extends BlockEntity {
    // Simple 1-slot toaster: when cooking, slot holds the "input"; when done, it holds the result
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    // 200 ticks = 10s; tweak to taste
    private static final int COOK_TIME_TOTAL = 200;
    private int cookTime = 0;
    private boolean cooking = false;

    public ToasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TOASTER_BE, pos, state);
    }

    public boolean isEmpty() { return items.get(0).isEmpty(); }
    public boolean hasResult() { return !isEmpty() && !cooking; }

    public void startCooking() {
        // put an "input" in slot to track; could be Items.BREAD or a custom item
        items.set(0, new ItemStack(Items.BREAD, 1));
        cookTime = 0;
        cooking = true;
    }

    public static void tick(World world, BlockPos pos, BlockState state, ToasterBlockEntity be) {
        if (!be.cooking) return;

        be.cookTime++;
        if (be.cookTime >= COOK_TIME_TOTAL) {
            // produce result — replace with your own item if desired (e.g., ModItems.TOAST)
            be.items.set(0, new ItemStack(Items.BREAD, 1));
            be.cooking = false;
            be.cookTime = 0;

            // turn off the light
            if (state.get(toaster.LIT)) {
                world.setBlockState(pos, state.with(toaster.LIT, false), Block.NOTIFY_ALL);
            }
            be.markDirtyAndSync();
        }
    }

    public ItemStack takeResult() {
        ItemStack out = items.get(0);
        items.set(0, ItemStack.EMPTY);
        return out;
    }

    public void markDirtyAndSync() {
        this.markDirty();
        if (world != null) world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
    }


}
