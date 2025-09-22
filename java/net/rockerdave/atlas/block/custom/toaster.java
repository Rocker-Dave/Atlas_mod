package net.rockerdave.atlas.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import net.rockerdave.atlas.block.entity.custom.ToasterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class toaster extends BlockWithEntity {
    public static final MapCodec<toaster> CODEC = toaster.createCodec(toaster::new);
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        ToasterBlockEntity be = (ToasterBlockEntity) world.getBlockEntity(pos);
        if (be == null) return ActionResult.PASS;

        // If holding bread and toaster is idle+empty: insert and start cooking
        if (stack.isOf(Items.BREAD) && !state.get(LIT) && be.isEmpty()) {
            if (!player.isCreative()) stack.decrement(1);
            be.startCooking();
            world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL);
            be.markDirtyAndSync();
            return ActionResult.CONSUME;
        }

        // If holding nothing and toaster has finished bread: give it to the player
        if (stack.isEmpty() && !state.get(LIT) && be.hasResult()) {
            player.giveItemStack(be.takeResult());
            be.markDirtyAndSync();
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }


    //voxel shape outline
    private static final VoxelShape SHAPE = Block.createCuboidShape(4,0,3,12,9,13);

    //voxel shape outline
    private static final VoxelShape SHAPE_N = Block.createCuboidShape(4, 0, 3, 12, 9, 13); // facing NORTH
    private static final VoxelShape SHAPE_E = Block.createCuboidShape(3, 0, 4, 13, 9, 12);
    private static final VoxelShape SHAPE_S = Block.createCuboidShape(4, 0, 3, 12, 9, 13); // same box, rotated 180
    private static final VoxelShape SHAPE_W = Block.createCuboidShape(3, 0, 4, 13, 9, 12);
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> SHAPE_S;
            case EAST  -> SHAPE_E;
            case WEST  -> SHAPE_W;
            default    -> SHAPE_N;
        };
    }








    public toaster(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(LIT, false));
    }


    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
