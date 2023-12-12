package fr.bixboy.csm.gui.playerSize;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.bixboy.csm.CSM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class PlayerSizeGUI extends Screen{
    public PlayerSizeGUI() {
        super(new StringTextComponent("Player Size GUI"));
    }

    private int xSize = 174;
    private int ySize = 230;

    private int guiLeft;
    private int guiTop;

    private int race = 1;
    static String lang_races;
    static String desc_races;

    int posCheckF = 89;
    int posCheckM = 89;
    boolean checkF = false;
    boolean checkM = false;

    private float xMouse;
    private float yMouse;
    private boolean ScreenInf = true;
    private boolean ScreenRace;
    private boolean ScreenAccessory;

    private boolean Skins;

    boolean checked1 = true;
    boolean checked2 = false;
    boolean checked3 = false;
    boolean checked4 = false;

    boolean check1 = true;
    boolean check2 = false;
    boolean check3 = false;
    boolean check4 = false;

    private static float rotationAngleY = 180.0F;
    boolean restart = true;

    private static float scale = 1.0F;
    private static float scaleLarge = 1.0F;

    private String skin;
    private int changeSkin = 0;
    List<String> listSkins = new ArrayList<>();

    private static final ResourceLocation BUTTON_IMAGE1 = new ResourceLocation(CSM.MODID, "textures/bg_gui_base.png");

    @Override
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.addButton(new ImageButton(guiLeft + 165, guiTop + (ySize / 2) - 50, 21, 22, 174, checkButton(checked1, 1), 0, BUTTON_IMAGE1, humanButton -> {
            ScreenInf = true;
            ScreenRace = false;
            ScreenAccessory = false;
            Skins = false;
            drawButtonGender();
            checked(1, 1);

        }));

        this.addButton(new ImageButton(guiLeft + 165,  guiTop + (ySize/2) - 20, 21, 22, 174, checkButton(checked2, 1), 0, BUTTON_IMAGE1, humanButton -> {
            ScreenRace = true;
            ScreenAccessory = false;
            ScreenInf = false;
            Skins = false;
            drawButtonGender();
            checked(2, 1);

        }));

        this.addButton(new ImageButton(guiLeft + 165,  guiTop + (ySize/2) + 10, 21, 22, 174, checkButton(checked3, 1), 0, BUTTON_IMAGE1, humanButton -> {
            ScreenAccessory = true;
            ScreenRace = false;
            ScreenInf = false;
            Skins = false;
            drawButtonGender();
            checked(3, 1);

        }));

        this.addButton(new ImageButton(guiLeft + 165,  guiTop + (ySize/2) + 40, 21, 22, 174, checkButton(checked4, 1), 0, BUTTON_IMAGE1, humanButton -> {
            Skins = true;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = false;
            drawButtonGender();
            checked(4, 1);

        }));

        this.addButton(new Button(guiLeft + 58,  guiTop + 215, 60, 20, new TranslationTextComponent("Save"), saveButton -> {
            this.minecraft.setScreen((Screen)null);
            this.minecraft.mouseHandler.grabMouse();
        }));

        //Races

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 65, 69, 16, 174, checkButton(check1, 2), 0, BUTTON_IMAGE1, humanButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 1;
            drawButtonGender();
            checked(1, 2);
            scale = 1.0F;
            scaleLarge = 1.0F;
        }));

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 85, 69, 16, 174, checkButton(check2, 2), 0, BUTTON_IMAGE1, DwarvesButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 2;
            drawButtonGender();
            checked(2, 2);
            scale = 0.7F;
            scaleLarge = 1.0F;

        }));

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 105, 69, 16, 174, checkButton(check3, 2), 0, BUTTON_IMAGE1, ElvesButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 3;
            drawButtonGender();
            checked(3, 2);
            scale = 1.0F;
            scaleLarge = 1.0F;

        }));

        this.addButton(new ImageButton(guiLeft - 60,  guiTop + 125, 69, 16, 174, checkButton(check4, 2), 0, BUTTON_IMAGE1, KullButton -> {
            Skins = false;
            ScreenAccessory = false;
            ScreenRace = false;
            ScreenInf = true;
            race = 4;
            drawButtonGender();
            checked(4, 2);
            scale = 1.2F;
            scaleLarge = 1.2F;

        }));

        //Rotate buttons
        this.addButton(new ImageButton(guiLeft + 56,  guiTop + 110, 11, 13, 174, 88, 0, BUTTON_IMAGE1, rotateLeft -> {
        rotateRight();
        }));

        this.addButton(new ImageButton(guiLeft + 105,  guiTop + 110, 11, 13, 174, 101, 0, BUTTON_IMAGE1, rotateRight -> {
        rotateLeft();
        }));

    }

    private void checked(int i, int A) {
        if (A == 1)
        {
            switch (i) {
                case 1:

                    checked1 = true;
                    checked2 = false;
                    checked3 = false;
                    checked4 = false;

                    break;

                case 2:

                    checked1 = false;
                    checked2 = true;
                    checked3 = false;
                    checked4 = false;

                    break;

                case 3:

                    checked1 = false;
                    checked2 = false;
                    checked3 = true;
                    checked4 = false;

                    break;

                case 4:

                    checked1 = false;
                    checked2 = false;
                    checked3 = false;
                    checked4 = true;

                    break;

            }
        } else {
            switch (i) {
                case 1:

                    check1 = true;
                    check2 = false;
                    check3 = false;
                    check4 = false;

                    break;

                case 2:

                    check1 = false;
                    check2 = true;
                    check3 = false;
                    check4 = false;

                    break;

                case 3:

                    check1 = false;
                    check2 = false;
                    check3 = true;
                    check4 = false;

                    break;

                case 4:

                    check1 = false;
                    check2 = false;
                    check3 = false;
                    check4 = true;

                    break;

            }
        }

    }

    private int checkButton(boolean check, int i) {
        int pose = 0;

        if (i == 1) {
            if (check) {
                return pose = 44;
            } else {
                return pose = 66;
            }
        }

        else if (i == 2) {
            if (check) {
                return pose = 185;
            }
            else {
                return pose = 169;
            }
        }

        return pose;
    }
    boolean first = true;
    private void skins(MatrixStack matrixStack) {

        if (first) {
            listSkins.add("skins1");
            listSkins.add("skins2");
            listSkins.add("skins3");
            listSkins.add("skins4");
            first = false;
        }

        if (Skins) {

            skin = listSkins.get(changeSkin);
            drawCenteredString(matrixStack, this.font, skin, guiLeft + 87, guiTop + 200, 11299672);

            this.addButton(new ImageButton(guiLeft + 56,  guiTop + 200, 11, 13, 174, 88, 0, BUTTON_IMAGE1, changeSkins1 -> {

                if (changeSkin > 0) {
                    changeSkin--;
                }
            }));

            this.addButton(new ImageButton(guiLeft + 105,  guiTop + 200, 11, 13, 174, 101, 0, BUTTON_IMAGE1, changeSkins2 -> {

                if (changeSkin < listSkins.size() - 1) {
                    changeSkin++;
                }
            }));
        }
    }

    private void drawButtonGender() {

        if (ScreenRace) {

            this.addButton(new ImageButton(guiLeft + 40,  guiTop + 70, 17, 16, posCheckF, 230, 0, BUTTON_IMAGE1, GirlButton -> {
                checkF = true;
                if(checkF) {
                    posCheckF = 106;
                    posCheckM = 89;
                    checkM = false;
                }
            }));

            this.addButton(new ImageButton(guiLeft + 40,  guiTop + 90, 17, 16, posCheckM, 230, 0, BUTTON_IMAGE1, BoyButton -> {
                checkM = true;
                if (checkM) {
                    posCheckM = 106;
                    posCheckF = 89;
                    checkF = false;
                }
            }));
        }
        else {
            this.buttons.clear();
            init();
        }
    }

    private void changeSkin() {
        switch (changeSkin)
        {
            case 0 :

                break;

            case 1 :

                break;

            case 2 :

                break;

            case 3 :

                break;
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        this.renderBackground(matrices);
        drawBackground(matrices);
        drawButtonGender();
        skins(matrices);
        drawText(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        resetRotation();

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

        if (ScreenRace) {
            drawCenteredString(matrixStack, this.font, "Gender", guiLeft + 40, guiTop + 53, 11299672);
            drawCenteredString(matrixStack, this.font, "F", guiLeft + 33, guiTop + 75, 11299672);
            drawCenteredString(matrixStack, this.font, "M", guiLeft + 33, guiTop + 95, 11299672);
        }

        switch (race) {
            case 1:
                lang_races = I18n.get("gui.race_tipo_1");
                desc_races = I18n.get("gui.description_human");

                if (!Skins && !ScreenAccessory && ScreenInf || ScreenRace) {
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

                if (!Skins && !ScreenAccessory && ScreenInf || ScreenRace) {
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

                if (!Skins && !ScreenAccessory && ScreenInf || ScreenRace) {
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

                if (!Skins && !ScreenAccessory && ScreenInf || ScreenRace) {
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
            lines.add(line.trim());
            index = endIndex;
        }

        int lineHeight = this.font.lineHeight;
        int totalTextHeight = lines.size() * lineHeight;
        int yOffset = 0;

        for (String line : lines) {
            int textWidth = this.font.width(line);
            int xOffset = (maxWidth - textWidth) / 2;
            if (textWidth < maxWidth) {
                xOffset = 0;
            }
            this.font.draw(matrixStack, line, x + xOffset, y + yOffset, color);
            yOffset += lineHeight;
        }
    }

    public static void rotateLeft() {
        rotationAngleY -= 20.0F;
    }

    private static void rotateRight() {
        rotationAngleY += 20.0F;
    }

    public void resetRotation() {
        if (restart) {
            rotationAngleY = 180.0F;
        }
        restart = false;
    }

    public static void renderEntityInInventory(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_, LivingEntity p_228187_5_) {
        float f = (float)Math.atan((double)(p_228187_3_ / 40.0F));
        float f1 = (float)Math.atan((double)(p_228187_4_ / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(scaleLarge, scale, -1.0F);

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
        p_228187_5_.yBodyRot = rotationAngleY + f * 20.0F;
        p_228187_5_.yRot = rotationAngleY + f * 40.0F;
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
