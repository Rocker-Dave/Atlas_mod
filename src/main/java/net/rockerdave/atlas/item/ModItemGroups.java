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
    //Items Group
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
                        entries.add(ModItems.ARTISAN_BREAD);
                        entries.add(ModItems.SLICED_ARTISAN_BREAD);
                        entries.add(ModItems.SLICED_BREAD);
                        entries.add(ModItems.TOAST);
                        entries.add(ModItems.KNIFE);
                        entries.add(ModItems.RAW_GROUND_BEEF);
                        entries.add(ModItems.COOKED_GROUND_BEEF);
                        entries.add(ModItems.CHEESE);
                        entries.add(ModItems.RAW_BACON);
                        entries.add(ModItems.COOKED_BACON);


                    }).build());

    //Blocks Group
    public static final ItemGroup COOKING_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AtlasMod.MOD_ID, "cooking_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.TILE))
                    .displayName(Text.translatable("itemgroup.atlasmod.cooking_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.COUNTER_TOP);
                        entries.add(ModBlocks.STOVE_TOP);
                        entries.add(ModBlocks.CUTTING_BOARD);
                        entries.add(ModBlocks.TILE);
                        entries.add(ModBlocks.HOOD);

                    }).build());


    public static void registerItemGroups(){
        AtlasMod.LOGGER.info("Registering Item Groups for "+ AtlasMod.MOD_ID);
    }
}
