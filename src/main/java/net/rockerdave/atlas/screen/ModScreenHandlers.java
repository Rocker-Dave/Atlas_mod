package net.rockerdave.atlas.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.rockerdave.atlas.AtlasMod;
import net.rockerdave.atlas.screen.custom.StovetopScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<StovetopScreenHandler> STOVETOP_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AtlasMod.MOD_ID,"stovetop_screen_handler"),
                    new ExtendedScreenHandlerType<>(StovetopScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        AtlasMod.LOGGER.info("Registering Screen Handlers for " + AtlasMod.MOD_ID);
    }

}
