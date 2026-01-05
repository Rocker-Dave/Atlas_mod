package net.rockerdave.atlas.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rockerdave.atlas.block.custom.Stove_top;
import net.rockerdave.atlas.block.entity.ImplementedInventory;
import net.rockerdave.atlas.block.entity.ModBlockEntities;
import net.rockerdave.atlas.item.ModItems;
import net.rockerdave.atlas.screen.custom.StovetopScreenHandler;
import org.jetbrains.annotations.Nullable;

import static net.rockerdave.atlas.block.custom.Stove_top.LIT;

public class StovetopEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3,ItemStack.EMPTY);
    //variables
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 2;
    private static final int FUEL_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;


    public StovetopEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STOVETOP_BE, pos, state);
        //propertyDelegate assignment
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> StovetopEntity.this.progress;
                    case 1 -> StovetopEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: StovetopEntity.this.progress = value;
                    case 1: StovetopEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    //Inventory
    @Override
    public void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
        view.putInt("stove_top.progress", progress);
        view.putInt("stove_top.max_progress", maxProgress);
    }

    @Override
    public void readData(ReadView view) {
        Inventories.readData(view, inventory);
        progress = view.getInt("stove_top.progress", 0);
        maxProgress = view.getInt("stove_top.max_progress", 0);
        super.readData(view);
    }
    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState) {
        ItemScatterer.spawn(world, pos, (this));
        super.onBlockReplaced(pos, oldState);
    }
    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    //Menu


    @Override
    public Text getDisplayName() {
        return Text.literal("Stovetop");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new StovetopScreenHandler(syncId,playerInventory,this, this.propertyDelegate);
    }






    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }


    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasBreadRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);
            world.setBlockState(pos,state.with(LIT, true));
            state = world.getBlockState(pos);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
                
            }
        } else {
            resetProgress();
        }
        updateLitWhenInputEmpty();
        


    }

    private void updateLitWhenInputEmpty() {
        if (world == null || world.isClient) return;

        if (this.getStack(INPUT_SLOT).isEmpty()) {
            BlockState state = world.getBlockState(pos);

            // Only flip if the property exists and is currently true
            if (state.contains(Stove_top.LIT) && state.get(Stove_top.LIT)) {
                world.setBlockState(pos, state.with(Stove_top.LIT, false), Block.NOTIFY_LISTENERS);
                // optional: sync UI/clients if you maintain a helper
                // markDirtyAndSync();
            }
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 72;
    }

    private void craftItem() {
        ItemStack output = new ItemStack(ModItems.ARTISAN_BREAD, 1);
        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(FUEL_SLOT, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));

    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasBreadRecipe() {
        Item input = ModItems.DOUGH;
        Item fuel1 = Items.COAL;
        Item fuel2 = Items.CHARCOAL;
        ItemStack output = new ItemStack(ModItems.ARTISAN_BREAD, 1);
        return this.getStack(INPUT_SLOT).isOf(input) && getStack(FUEL_SLOT).isOf(fuel1)||getStack(FUEL_SLOT).isOf(fuel2) &&
                canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);

    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();

    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();
        //
        return maxCount >= currentCount + count;
    }
}
