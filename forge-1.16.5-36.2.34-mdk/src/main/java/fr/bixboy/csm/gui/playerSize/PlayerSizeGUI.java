package fr.bixboy.csm.gui.playerSize;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.bixboy.csm.CSM;
import fr.bixboy.csm.entity.model.CustomPlayerModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerSizeGUI extends Screen{
    public PlayerSizeGUI() {
        super(new StringTextComponent("Player Size GUI"));
    }

    private final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(CSM.MODID, "textures/ui_background.png");

    private int xSize = 174;
    private int ySize = 230;

    private int guiLeft;
    private int guiTop;

    private int race = 1;
    static String lang_races;
    static String desc_races;

    private float xMouse;
    private float yMouse;
    private boolean ScreenInf = true;
    private boolean ScreenRace;
    private boolean ScreenAccessory;

    private boolean Skins;

    private static final ResourceLocation BUTTON_IMAGE1 = new ResourceLocation(CSM.MODID, "textures/bg_gui_base.png");

    private CustomPlayerModel customPlayerModel; // Instance de votre modèle de joueur personnalisé

    // Définir des méthodes pour changer l'apparence du modèle
    private void changeToRaceModel() {
        // Code pour changer le modèle en fonction de la race choisie
    }

    private void changeToGenderModel() {
        // Code pour changer le modèle en fonction du genre choisi
    }

    private void changeToAccessoryModel() {
        // Code pour changer le modèle en fonction de l'accessoire choisi
    }

    private String titre;
    private String paragraphe;

    @Override
    protected void init() {

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.addButton(new ImageButton(guiLeft + 165, guiTop + (ySize / 2) - 50, 21, 22, 174, 44, 0, BUTTON_IMAGE1, humanButton -> {
            ScreenInf = true;
            ScreenRace = false;
            ScreenAccessory = false;
            Skins = false;
        }));

        this.addButton(new ImageButton(guiLeft + 165,  guiTop + (ySize/2) - 20, 21, 22, 174, 44, 0, BUTTON_IMAGE1, humanButton -> {
            ScreenRace = true;
            ScreenAccessory = false;
            ScreenInf = false;
            Skins = false;
        }));

        this.addButton(new ImageButton(guiLeft + 165,  guiTop + (ySize/2) + 10, 21, 22, 174, 66, 0, BUTTON_IMAGE1, humanButton -> {
            ScreenAccessory = true;
            ScreenRace = false;
            ScreenInf = false;
            Skins = false;
        }));

        this.addButton(new ImageButton(guiLeft + 165,  guiTop + (ySize/2) + 40, 21, 22, 174, 66, 0, BUTTON_IMAGE1, humanButton -> {
            Skins = true;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = false;
        }));

        this.addButton(new Button(guiLeft + 58,  guiTop + 215, 60, 20, new TranslationTextComponent("Save"), saveButton -> {
            this.minecraft.setScreen((Screen)null);
            this.minecraft.mouseHandler.grabMouse();
        }));

        //Races

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 65, 69, 16, 174, 169, 0, BUTTON_IMAGE1, humanButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 1;
        }));

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 85, 69, 16, 174, 169, 0, BUTTON_IMAGE1, DwarvesButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 2;
        }));

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 105, 69, 16, 174, 169, 0, BUTTON_IMAGE1, ElvesButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 3;
        }));

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 125, 69, 16, 174, 169, 0, BUTTON_IMAGE1, KullButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 4;
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        this.renderBackground(matrices);
        drawBackground(matrices);
        drawText(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
    }

    private void drawBackground(MatrixStack matrices)
    {
        this.minecraft.getTextureManager().bind(BUTTON_IMAGE1);
        this.blit(matrices, guiLeft, guiTop, 0, 0, 174, 230);
        renderEntityInInventory(guiLeft + 87, guiTop + 120, 40, (float)(guiLeft + 51) - this.xMouse, (float)(guiTop + 75 - 50) - this.yMouse, this.minecraft.player);
    }


    @Override
    public boolean isPauseScreen() {
        return false; // Empêche la pause du jeu lorsque cet écran est ouvert
    }

    public void drawText(MatrixStack matrixStack) {
        if (ScreenInf && !ScreenAccessory && !ScreenRace && !Skins)
        {
            drawCenteredString(matrixStack, this.font, "Chose your Race", guiLeft + 87, guiTop + 20, 11299672);

        } else if (ScreenRace && !ScreenAccessory && !ScreenInf && !Skins)
        {
            drawCenteredString(matrixStack, this.font, "Chose your Gender", guiLeft + 87, guiTop + 20, 11299672);

        } else if (ScreenAccessory && !ScreenInf && !ScreenRace && !Skins)
        {
            drawCenteredString(matrixStack, this.font, "Chose your Accessory", guiLeft + 87, guiTop + 20, 11299672);
        } else if (Skins && !ScreenAccessory && !ScreenInf && !ScreenRace)
        {
            drawCenteredString(matrixStack, this.font, "Chose your Skin", guiLeft + 87, guiTop + 20, 11299672);
        }

        switch (race) {
            case 1:
                lang_races = I18n.get("gui.race_tipo_1");
                desc_races = I18n.get("gui.description_human");

                if (!Skins && !ScreenAccessory && ScreenInf && !ScreenRace) {
                    renderTextWithLineBreaks(matrixStack, desc_races, guiLeft + 75, guiTop + 150, 11299672, 27);
                }

                drawCenteredString(matrixStack, this.font, lang_races, guiLeft + (xSize/2) - 95, guiTop + 70, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_2"), guiLeft + (xSize/2) - 95, guiTop + 90, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_3"), guiLeft + (xSize/2) - 95, guiTop + 110, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_4"), guiLeft + (xSize/2) - 95, guiTop + 130, 11299672);

                break;

            case 2:
                lang_races = I18n.get("gui.race_tipo_2");
                desc_races = I18n.get("gui.description_dwarves");

                if (!Skins && !ScreenAccessory && ScreenInf && !ScreenRace) {
                    renderTextWithLineBreaks(matrixStack, desc_races, guiLeft + 75, guiTop + 140, 11299672, 27);
                }
                drawCenteredString(matrixStack, this.font, lang_races, guiLeft + (xSize/2) - 95, guiTop + 90, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_1"), guiLeft + (xSize/2) - 95, guiTop + 70, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_3"), guiLeft + (xSize/2) - 95, guiTop + 110, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_4"), guiLeft + (xSize/2) - 95, guiTop + 130, 11299672);

                break;
            case 3:
                lang_races = I18n.get("gui.race_tipo_3");
                desc_races = I18n.get("gui.description_elves");

                if (!Skins && !ScreenAccessory && ScreenInf && !ScreenRace) {
                    renderTextWithLineBreaks(matrixStack, desc_races, guiLeft + 75, guiTop + 140, 11299672, 27);
                }
                drawCenteredString(matrixStack, this.font, lang_races, guiLeft + (xSize/2) - 95, guiTop + 110, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_2"),  guiLeft+ (xSize/2) - 95, guiTop + 90, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_1"), guiLeft + (xSize/2) - 95, guiTop + 70, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_4"), guiLeft + (xSize/2) - 95, guiTop + 130, 11299672);

                break;
            case 4:
                lang_races = I18n.get("gui.race_tipo_4");
                desc_races = I18n.get("gui.description_kull");

                if (!Skins && !ScreenAccessory && ScreenInf && !ScreenRace) {
                    renderTextWithLineBreaks(matrixStack, desc_races, guiLeft + 75, guiTop + 150, 11299672, 27);
                }
                drawCenteredString(matrixStack, this.font, lang_races, guiLeft + (xSize/2) - 95, guiTop + 130, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_2"), guiLeft + (xSize/2) - 95, guiTop + 90, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_3"), guiLeft + (xSize/2) - 95, guiTop + 110, 11299672);
                drawCenteredString(matrixStack, this.font, I18n.get("gui.race_tipo_1"), guiLeft + (xSize/2) - 95, guiTop + 70, 11299672);

                break;
            case 5:
                lang_races = I18n.get("gui.race_tipo_5");
                desc_races = I18n.get("gui.description_human");
                renderTextWithLineBreaks(matrixStack, desc_races, guiLeft + 75, guiTop + 150, 11299672, 27);

                break;
        }

    }

    private void renderTextWithLineBreaks(MatrixStack matrixStack, String text, int x, int y, int color, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while (index < text.length()) {
            int endIndex = Math.min(index + maxWidth, text.length());
            String line = text.substring(index, endIndex);
            int lineBreakIndex = line.lastIndexOf("2" );
            if (lineBreakIndex != -1 && lineBreakIndex < endIndex - index) {
                endIndex = index + lineBreakIndex + 1;
                line = text.substring(index, endIndex);
            }
            lines.add(line.trim()); // Ajouter la ligne sans les espaces en fin de ligne
            index = endIndex;
        }

        int lineHeight = this.font.lineHeight;
        int totalTextHeight = lines.size() * lineHeight;
        int yOffset = 0;

        for (String line : lines) {
            int textWidth = this.font.width(line);
            int xOffset = (maxWidth - textWidth) / 2; // Calcul du décalage pour centrer le texte horizontalement
            if (textWidth < maxWidth) {
                xOffset = 0; // Ne pas centrer si la largeur du texte est inférieure à la largeur maximale
            }
            this.font.draw(matrixStack, line, x + xOffset, y + yOffset, color);
            yOffset += lineHeight;
        }
    }

    public static void renderEntityInInventory(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_, LivingEntity p_228187_5_) {
        float f = (float)Math.atan((double)(p_228187_3_ / 40.0F));
        float f1 = (float)Math.atan((double)(p_228187_4_ / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        matrixstack.mulPose(quaternion);
        float f2 = p_228187_5_.yBodyRot;
        float f3 = p_228187_5_.yRot;
        float f4 = p_228187_5_.xRot;
        float f5 = p_228187_5_.yHeadRotO;
        float f6 = p_228187_5_.yHeadRot;
        p_228187_5_.yBodyRot = 180.0F + f * 20.0F;
        p_228187_5_.yRot = 180.0F + f * 40.0F;
        p_228187_5_.xRot = -f1 * 20.0F;
        p_228187_5_.yHeadRot = p_228187_5_.yRot;
        p_228187_5_.yHeadRotO = p_228187_5_.yRot;
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderermanager.overrideCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.render(p_228187_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.endBatch();
        entityrenderermanager.setRenderShadow(true);
        p_228187_5_.yBodyRot = f2;
        p_228187_5_.yRot = f3;
        p_228187_5_.xRot = f4;
        p_228187_5_.yHeadRotO = f5;
        p_228187_5_.yHeadRot = f6;
        RenderSystem.popMatrix();
    }
}
