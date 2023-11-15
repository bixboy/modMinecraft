package fr.bixboy.csm.gui.playerSize;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class PlayerSizeGUI extends Screen{
    public PlayerSizeGUI() {
        super(new StringTextComponent("Player Size GUI"));
    }

    @Override
    protected void init() {
        // Définir la largeur et la hauteur de l'écran
        int width = this.width;
        int height = this.height;

        // Position du fond blanc au centre de l'écran
        int centerX = width / 2 - 100;
        int centerY = height / 2 - 100;

        // Dessiner un fond blanc au centre de l'écran
        fill(centerX, centerY, centerX + 200, centerY + 200, 0xFFFFFFFF); // Remplacez la couleur par celle que vous souhaitez

        // Dessiner une barre latérale à gauche avec quatre boutons
        int sidebarWidth = 50;
        int buttonHeight = 40;
        int gap = 10;
        int startY = centerY + gap; // Commencer la barre latérale un peu en bas du fond blanc
        int startX = centerX - sidebarWidth - gap; // Positionner la barre latérale à gauche du fond blanc

        for (int i = 0; i < 4; i++) {
            int buttonY = startY + i * (buttonHeight + gap);

            // Dessiner chaque bouton dans la barre latérale
            addButton(new ButtonRaces(startX, buttonY, sidebarWidth, buttonHeight, i));
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Empêche la pause du jeu lorsque cet écran est ouvert
    }
}
