package fr.bixboy.csm.gui.playerSize;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.bixboy.csm.CSM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PlayerSizeGUI extends Screen {
    public PlayerSizeGUI() {
        super(new StringTextComponent("Player Size GUI"));
    }

    private final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(CSM.MODID, "textures/ui_background.png");

    private int xSize = 256;
    private int ySize = 202;

    private int guiLeft;
    private int guiTop;

    private float xMouse;
    private float yMouse;

    private float MAX_SIZE = 1f;

    private boolean ScreenInf = true;
    private boolean ScreenRace;
    private boolean ScreenAccessory;

    private static final ResourceLocation BUTTON_IMAGE1 = new ResourceLocation(CSM.MODID, "textures/bg_gui_base.png");
    private static final ResourceLocation BUTTON_IMAGE2 = new ResourceLocation(CSM.MODID, "textures/baguette-magique.png");
    private static final ResourceLocation BUTTON_IMAGE3 = new ResourceLocation(CSM.MODID, "textures/portrait.png");


    @Override
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.addButton(new ImageButton(guiLeft + (xSize / 2) + 70, guiTop + (ySize / 2) - 10, 21, 22, 174, 44, 0, BUTTON_IMAGE1, humanButton -> {
            ScreenInf = true;
            ScreenRace = false;
            ScreenAccessory = false;
        }));

        this.addButton(new ImageButton(guiLeft + (xSize/2) + 70,  guiTop + (ySize/2) + 30, 21, 22, 174, 44, 0, BUTTON_IMAGE1, humanButton -> {
            ScreenRace = true;
            ScreenAccessory = false;
            ScreenInf = false;
        }));

        this.addButton(new ImageButton(guiLeft + (xSize/2) + 70,  guiTop + (ySize/2) + 60, 21, 22, 174, 66, 0, BUTTON_IMAGE1, humanButton -> {
            ScreenAccessory = true;
            ScreenRace = false;
            ScreenInf = false;
        }));

        this.addButton(new Button(guiLeft + (xSize/2) - 70,  guiTop + (ySize/2) + 70, 60, 20, new TranslationTextComponent("Save"), saveButton -> {
            this.minecraft.setScreen((Screen)null);
            this.minecraft.mouseHandler.grabMouse();
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
        this.minecraft.getTextureManager().bind(TEXTURE_LOCATION);
        this.blit(matrices, guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
        renderEntityInInventory(guiLeft + 51, guiTop + 75, 30, (float)(guiLeft + 51) - this.xMouse, (float)(guiTop + 75 - 50) - this.yMouse, this.minecraft.player);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Empêche la pause du jeu lorsque cet écran est ouvert
    }

    public void drawText(MatrixStack matrixStack) {
        if (ScreenInf && !ScreenAccessory && !ScreenRace)
        {
            drawCenteredString(matrixStack, this.font, "Chose your Race", guiLeft + 128, guiTop + 20, 0xFFFFFF);

        } else if (ScreenRace && !ScreenAccessory && !ScreenInf)
        {
            drawCenteredString(matrixStack, this.font, "Chose your Gender", guiLeft + 128, guiTop + 20, 0xFFFFFF);

        } else if (ScreenAccessory && !ScreenInf && !ScreenRace)
        {
            drawCenteredString(matrixStack, this.font, "Chose your Accessory", guiLeft + 128, guiTop + 20, 0xFFFFFF);
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
