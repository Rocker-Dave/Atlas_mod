package net.rockerdave.atlas.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rockerdave.atlas.block.entity.custom.StovetopEntity;
import org.jetbrains.annotations.Nullable;

public class Stovetop extends BlockWithEntity implements BlockEntityProvider {
    public Stovetop(Settings settings) {
        super(settings);
    }
    public static final MapCodec<Stovetop> CODEC = Stovetop.createCodec(Stovetop::new);


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
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
