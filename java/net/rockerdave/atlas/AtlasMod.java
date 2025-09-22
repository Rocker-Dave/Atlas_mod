package net.rockerdave.atlas;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.FuelRegistry;
import net.rockerdave.atlas.block.ModBlocks;
import net.rockerdave.atlas.block.entity.ModBlockEntities;
import net.rockerdave.atlas.item.ModItemGroups;
import net.rockerdave.atlas.item.ModItems;
import net.rockerdave.atlas.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtlasMod implements ModInitializer {
	public static final String MOD_ID = "atlasmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registermodblocks();
		ModItemGroups.registerItemGroups();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();

		FuelRegistryEvents.BUILD.register(((builder, context) -> {
			builder.add(ModItems.STARLIGHT_ASHES, 200);
		}));
		LOGGER.info("Hello Fabric world!");
	}
}