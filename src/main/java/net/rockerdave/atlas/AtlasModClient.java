package net.rockerdave.atlas;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.rockerdave.atlas.screen.ModScreenHandlers;
import net.rockerdave.atlas.screen.custom.OvenScreen;
import net.rockerdave.atlas.screen.custom.StovetopScreen;

public class AtlasModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.STOVETOP_SCREEN_HANDLER, StovetopScreen::new);
        HandledScreens.register(ModScreenHandlers.OVEN_SCREEN_HANDLER, OvenScreen::new);
    }
}
