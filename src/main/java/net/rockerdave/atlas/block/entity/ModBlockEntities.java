package net.rockerdave.atlas.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rockerdave.atlas.AtlasMod;
import net.rockerdave.atlas.block.ModBlocks;
import net.rockerdave.atlas.block.entity.custom.OvenEntity;
import net.rockerdave.atlas.block.entity.custom.StovetopEntity;
import net.rockerdave.atlas.block.entity.custom.ToasterBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<StovetopEntity> STOVETOP_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AtlasMod.MOD_ID, "stovetop_be"),
                    FabricBlockEntityTypeBuilder.create(StovetopEntity::new, ModBlocks.STOVE_TOP).build());

    public static final BlockEntityType<OvenEntity> OVEN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AtlasMod.MOD_ID, "oven_be"),
                    FabricBlockEntityTypeBuilder.create(OvenEntity::new, ModBlocks.OVEN).build());

    public static final BlockEntityType<ToasterBlockEntity> TOASTER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AtlasMod.MOD_ID, "toaster_be"),
                    FabricBlockEntityTypeBuilder.create(ToasterBlockEntity::new, ModBlocks.TOASTER).build());

    public static void registerBlockEntities(){
        AtlasMod.LOGGER.info("Registering Block Entities for AtlasMod");
    }
}
