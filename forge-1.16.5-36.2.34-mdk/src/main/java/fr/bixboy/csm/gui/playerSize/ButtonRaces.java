package fr.bixboy.csm.gui.playerSize;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class ButtonRaces extends Button {


    public ButtonRaces(int x, int y, int width, int height, int id) {
        super(x, y, width, height, new StringTextComponent("Button " + (id + 1)), button -> {
            // Action à effectuer lors du clic sur le bouton

        });
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
        // Personnalisation supplémentaire si nécessaire
    }
}
