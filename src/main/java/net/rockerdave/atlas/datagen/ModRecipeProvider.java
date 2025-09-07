package net.rockerdave.atlas.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.rockerdave.atlas.block.ModBlocks;
import net.rockerdave.atlas.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.MISC, ModBlocks.STOVE_TOP,1)
                        .pattern("iii")
                        .pattern("c c")
                        .pattern("ccc")
                        .input('i', Items.IRON_INGOT)
                        .input('c', Items.COBBLESTONE)
                        .criterion(hasItem(Items.FURNACE), conditionsFromItem(Items.FURNACE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.HOOD,1)
                        .pattern(" i ")
                        .pattern("i i")
                        .pattern("   ")
                        .input('i', Items.IRON_INGOT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.KNIFE,1)
                        .pattern("i")
                        .pattern("s")
                        .input('i', Items.IRON_INGOT)
                        .input('s', Items.STICK)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.CUTTING_BOARD,1)
                        .pattern(" k ")
                        .pattern("iii")
                        .input('i', Items.IRON_INGOT)
                        .input('k', ModItems.KNIFE)
                        .criterion(hasItem(ModItems.KNIFE), conditionsFromItem(ModItems.KNIFE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.TILE,4)
                        .pattern("wb")
                        .pattern("bw")
                        .input('b', Items.BLACK_CONCRETE)
                        .input('w', Items.WHITE_CONCRETE)
                        .criterion(hasItem(Items.WHITE_CONCRETE), conditionsFromItem(Items.WHITE_CONCRETE))
                        .criterion(hasItem(Items.BLACK_CONCRETE), conditionsFromItem(Items.BLACK_CONCRETE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.DOUGH,2)
                        .input(ModItems.FLOUR, 1)
                        .input(Items.EGG,1)
                        .criterion(hasItem(ModItems.FLOUR), conditionsFromItem(ModItems.FLOUR))
                        .offerTo(exporter);









            }
        };
    }

    @Override
    public String getName() {
        return "Atlasmod Recipes";
    }
}
