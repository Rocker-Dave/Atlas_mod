package net.rockerdave.atlas.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.rockerdave.atlas.AtlasMod;
import net.rockerdave.atlas.item.custom.ChiselItem;

public class ModItems {




    // Registering new Items
    public static final Item PINK_GARNET = registerItem( "pink_garnet", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID,"pink_garnet")))));
    public static final Item RAW_PINK_GARNET = registerItem( "raw_pink_garnet", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID,"raw_pink_garnet")))));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID,"chisel")))));

    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID,"cauliflower")))));

    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID,"starlight_ashes")))));
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
                    .food(ModFoodComponents.CAULIFLOWER)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "artisan_bread"))))
    );
    public static final Item SLICED_BREAD = registerItem(
            "sliced_bread",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.CAULIFLOWER)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "sliced_bread"))))
    );
    public static final Item SLICED_ARTISAN_BREAD = registerItem(
            "sliced_artisan_bread",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.CAULIFLOWER)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "sliced_artisan_bread"))))
    );
    public static final Item TOAST = registerItem(
            "toast",
            new Item(new Item.Settings()
                    .food(ModFoodComponents.CAULIFLOWER)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "toast"))))
    );
    public static final Item KNIFE = registerItem(
            "knife",
            new Item(new Item.Settings()
                    .sword(ToolMaterial.IRON,3,2)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AtlasMod.MOD_ID, "knife")))
            )
    );

    //Helper Class
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AtlasMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AtlasMod.LOGGER.info("Registering Mod Items for " + AtlasMod.MOD_ID);

        //adding item to item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PINK_GARNET);
            fabricItemGroupEntries.add(RAW_PINK_GARNET);
        });

    }
}
