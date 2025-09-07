package net.rockerdave.atlas.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.rockerdave.atlas.AtlasMod;
import net.rockerdave.atlas.block.custom.*;

import java.util.function.Function;

public class ModBlocks {

    //PINK GARNET BLOCK
    public static final Block PINK_GARNET_BLOCK = registerBlock(
            "pink_garnet_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
    );

    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock(
            "raw_pink_garnet_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
    );
    //Pink Garnet Ore Blocks
    public static final Block PINK_GARNET_ORE = registerBlock(
            "pink_garnet_ore",                     // 1) name
            settings ->                                     // 2) factory lambda
                    new ExperienceDroppingBlock(
                            UniformIntProvider.create(2, 5),         // XP provider first
                            settings                                 // then Settings
                    ),
            AbstractBlock.Settings.create()                  // 3) the Settings you pass in
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
    );
    public static final Block PINK_GARNET_DEEPSLATE_ORE = registerBlock(
            "pink_garnet_deepslate_ore",                     // 1) name
            settings ->                                     // 2) factory lambda
                    new ExperienceDroppingBlock(
                            UniformIntProvider.create(2, 5),         // XP provider first
                            settings                                 // then Settings
                    ),
            AbstractBlock.Settings.create()                  // 3) the Settings you pass in
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
    );
    public static final Block COUNTER_TOP = registerBlock(
            "counter_top",
            counter_top::new,
            AbstractBlock.Settings.create()
                    .strength(2f)
                    .sounds(BlockSoundGroup.WOOD)
                    .nonOpaque()
    );
    public static final Block STOVE_TOP = registerBlock(
            "stove_top",
            Stove_top::new,
            AbstractBlock.Settings.create()
                    .strength(2f)
                    .sounds(BlockSoundGroup.WOOD)
    );
    public static final Block OVEN = registerBlock(
            "oven",
            Oven::new,
            AbstractBlock.Settings.create()
                    .strength(2f)
                    .sounds(BlockSoundGroup.WOOD)
    );
    public static final Block TILE = registerBlock(
            "tile",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(2f)
                    .sounds(BlockSoundGroup.IRON)

    );
    public static final Block HOOD = registerBlock(
            "hood",
            hood::new,
            AbstractBlock.Settings.create()
                    .strength(2f)
                    .sounds(BlockSoundGroup.IRON)
                    .nonOpaque()

    );
    public static final Block CUTTING_BOARD = registerBlock(
            "cutting_board",
            cutting_board::new,
            AbstractBlock.Settings.create()
                    .strength(2f)
                    .sounds(BlockSoundGroup.TUFF)
                    .nonOpaque()

    );







    //Helper Class
    private static <B extends Block> B registerBlock(
            String name,
            Function<AbstractBlock.Settings, B> factory,
            AbstractBlock.Settings settings
    ) {
        // 1) Build the registry key
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AtlasMod.MOD_ID, name));
        // 2) Attach the key to the settings
        AbstractBlock.Settings keyedSettings = settings.registryKey(key);
        // 3) Let the factory make your block
        B block = factory.apply(keyedSettings);
        // 4) Item registration
        registerBlockItem(name, block);
        // 5) Final registry
        return Registry.register(Registries.BLOCK, key, block);
    }


    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);
    }

    public static void registermodblocks() {
        AtlasMod.LOGGER.info("Registering Mod Blocks for " + AtlasMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.PINK_GARNET_BLOCK);
            fabricItemGroupEntries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
        });

    }

}
