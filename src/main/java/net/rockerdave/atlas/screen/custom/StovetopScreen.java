package net.rockerdave.atlas.screen.custom;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rockerdave.atlas.AtlasMod;

//Renders the Screen texture
public class StovetopScreen extends HandledScreen<StovetopScreenHandler> {
    public static final Identifier GUI_TEXTURE =
            Identifier.of(AtlasMod.MOD_ID, "textures/gui/stovetop/stovetop_gui.png");

    public StovetopScreen(StovetopScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);
    }
}
