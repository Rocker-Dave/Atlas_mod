package net.rockerdave.atlas.item;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rockerdave.atlas.AtlasMod;
import net.rockerdave.atlas.block.ModBlocks;


public class ModItemGroups {

    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AtlasMod.MOD_ID, "pink_garnet_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET))
                    .displayName(Text.translatable("itemgroup.atlasmod.pink_garnet_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);
                        entries.add(ModItems.CHISEL);
                        entries.add(ModItems.CAULIFLOWER);
                        entries.add(ModItems.STARLIGHT_ASHES);
                    }).build());

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AtlasMod.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))
                    .displayName(Text.translatable("itemgroup.atlasmod.pink_garnet_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.PINK_GARNET_ORE);
                        entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);
                        entries.add(net.rockerdave.atlas.block.ModBlocks.COUNTER_TOP);
                    }).build());

    public static final ItemGroup COOKING_INGREDIENTS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AtlasMod.MOD_ID, "cooking_ingredients"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.FLOUR))
                    .displayName(Text.translatable("itemgroup.atlasmod.cooking_ingredients"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.FLOUR);
                        entries.add(ModItems.CRUSHED_ICE);
                        entries.add(ModItems.SALT);
                        entries.add(ModItems.PEPPER);
                        entries.add(ModItems.DOUGH);
                        entries.add(ModBlocks.COUNTER_TOP);
                        entries.add(ModBlocks.STOVE_TOP);

                    }).build());


    public static void registerItemGroups(){
        AtlasMod.LOGGER.info("Registering Item Groups for "+ AtlasMod.MOD_ID);
    }
}
