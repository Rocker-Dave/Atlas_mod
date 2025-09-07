package net.rockerdave.atlas.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.rockerdave.atlas.block.ModBlocks;
import net.rockerdave.atlas.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PINK_GARNET_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TILE);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.STOVE_TOP);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.COUNTER_TOP);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.HOOD);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CUTTING_BOARD);



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);

        itemModelGenerator.register(ModItems.ARTISAN_BREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLOUR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUSHED_ICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KNIFE, Models.GENERATED);

    }
}
