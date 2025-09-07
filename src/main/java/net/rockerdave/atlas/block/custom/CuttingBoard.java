package net.rockerdave.atlas.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import org.jetbrains.annotations.Nullable;

public class CuttingBoard extends HorizontalFacingBlock {
    public static final MapCodec<CuttingBoard> CODEC = createCodec(CuttingBoard::new);

    public CuttingBoard(Settings settings) {
        super(settings);
    }



    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
;
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }




    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }
}
