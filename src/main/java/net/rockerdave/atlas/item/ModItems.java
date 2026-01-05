package net.rockerdave.atlas.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.rockerdave.atlas.AtlasMod;

public class ModItems {

    //Actual ingredients
    public static final Item FLOUR = registerItem("flour", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID,"flour")))));
    public static final Item CRUSHED_ICE = registerItem(
            "crushed_ice",
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "crushed_ice"))))
    );

    public static final Item SALT = registerItem(
            "salt",
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "salt"))))
    );

    public static final Item PEPPER = registerItem(
            "pepper",
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "pepper"))))
    );

    public static final Item DOUGH = registerItem(
            "dough",
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "dough"))))
    );
    public static final Item ARTISAN_BREAD = registerItem(
            "artisan_bread",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "artisan_bread"))))
    );
    public static final Item SLICED_BREAD = registerItem(
            "sliced_bread",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "sliced_bread"))))
    );
    public static final Item SLICED_ARTISAN_BREAD = registerItem(
            "sliced_artisan_bread",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "sliced_artisan_bread"))))
    );
    public static final Item TOAST = registerItem(
            "toast",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "toast"))))
    );
    public static final Item KNIFE = registerItem(
            "knife",
            new Item(new Item.Settings()
                    .sword(ToolMaterial.IRON,3,2)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "knife")))
            )
    );
    public static final Item RAW_GROUND_BEEF = registerItem(
            "raw_ground_beef",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "raw_ground_beef"))))
    );
    public static final Item COOKED_GROUND_BEEF = registerItem(
            "cooked_ground_beef",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "cooked_ground_beef"))))
    );
    public static final Item CHEESE = registerItem(
            "cheese",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "cheese"))))
    );
    public static final Item RAW_BACON = registerItem(
            "raw_bacon",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "raw_bacon"))))
    );
    public static final Item COOKED_BACON = registerItem(
            "cooked_bacon",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.TEST)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "cooked_bacon"))))
    );


    //Helper Class
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AtlasMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AtlasMod.LOGGER.info("Registering Mod Items for " + AtlasMod.MOD_ID);



    }
}
