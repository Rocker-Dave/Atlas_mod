package net.rockerdave.atlas.screen.custom;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.rockerdave.atlas.screen.ModScreenHandlers;


//Actually makes the slots and stuff
public class OvenScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final FuelRegistry fuelRegistry;




    public OvenScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(2));
    }


    public OvenScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.OVEN_SCREEN_HANDLER,syncId);
        this.inventory = ((Inventory) blockEntity);

        // Build the default fuel registry from the server world's registries & features
        var world = playerInventory.player.getWorld();
        this.fuelRegistry = FuelRegistry.createDefault(
                world.getRegistryManager(),
                world.getEnabledFeatures()
        );

        // top input slot
        this.addSlot(new Slot(inventory, 0, 56, 17));

        // right output slot
        this.addSlot(new Slot(inventory, 2, 116, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });




        // fuel slot
        this.addSlot(new Slot(inventory, 1, 56, 53) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return fuelRegistry.isFuel(stack);
            }

            @Override
            public int getMaxItemCount(ItemStack stack) {
                return stack.isOf(Items.BUCKET) ? 1 : super.getMaxItemCount(stack);
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

    }




    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }


    //Helpers, stay the same for player inventory
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }


}