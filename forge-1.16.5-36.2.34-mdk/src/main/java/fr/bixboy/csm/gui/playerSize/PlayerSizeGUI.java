package fr.bixboy.csm.gui.playerSize;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.bixboy.csm.CSM;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class PlayerSizeGUI extends Screen{
    public PlayerSizeGUI() {
        super(new StringTextComponent("Player Size GUI"));
    }

    private final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(CSM.MODID, "textures/ui_background.png");
    private int xSize = 256;
    private int ySize = 202;

    private int guiLeft;
    private int guiTop;
    @Override
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawBackground(matrices);

        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void drawBackground(MatrixStack matrices)
    {
        this.minecraft.getTextureManager().bind(TEXTURE_LOCATION);
        this.blit(matrices, guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Empêche la pause du jeu lorsque cet écran est ouvert
    }
}
