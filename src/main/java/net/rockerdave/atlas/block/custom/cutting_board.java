package net.rockerdave.atlas.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.rockerdave.atlas.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class cutting_board extends HorizontalFacingBlock {
    public static final MapCodec<cutting_board> CODEC = cutting_board.createCodec(cutting_board::new);
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;





    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //Normal Bread
        if (stack.isOf(Items.BREAD)){
            stack.decrement(1);
            player.giveItemStack(new ItemStack(ModItems.SLICED_BREAD,2));


            return ActionResult.SUCCESS;

        }
        //Artisan Bread
        else if (stack.isOf(ModItems.ARTISAN_BREAD)){
            stack.decrement(1);
            player.giveItemStack(new ItemStack(ModItems.SLICED_ARTISAN_BREAD,2));


            return ActionResult.SUCCESS;

        }


        else {return ActionResult.FAIL;}
    }
    //voxel shape outline
    private static final VoxelShape SHAPE_N = Block.createCuboidShape(1, 0, 2, 15, 1, 14); // facing NORTH
    private static final VoxelShape SHAPE_E = Block.createCuboidShape(2, 0, 1, 14, 1, 15);
    private static final VoxelShape SHAPE_S = Block.createCuboidShape(1, 0, 2, 15, 1, 14); // same box, rotated 180
    private static final VoxelShape SHAPE_W = Block.createCuboidShape(2, 0, 1, 14, 1, 15);
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> SHAPE_S;
            case EAST  -> SHAPE_E;
            case WEST  -> SHAPE_W;
            default    -> SHAPE_N;
        };
    }

    public cutting_board(Settings settings) {
        super(settings);
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
        builder.add(FACING);
    }

    @Override
    public MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }
}
