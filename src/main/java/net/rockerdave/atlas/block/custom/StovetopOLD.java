package net.rockerdave.atlas.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rockerdave.atlas.block.entity.custom.StovetopEntity;
import org.jetbrains.annotations.Nullable;

public class StovetopOLD extends BlockWithEntity implements BlockEntityProvider {
    public StovetopOLD(Settings settings) {
        super(settings);
    }
    public static final MapCodec<StovetopOLD> CODEC = StovetopOLD.createCodec(StovetopOLD::new);


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StovetopEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {  // only do server-side
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StovetopEntity) {
                player.openHandledScreen((StovetopEntity)be);
            }
        }
        return ActionResult.SUCCESS;
    }





}
