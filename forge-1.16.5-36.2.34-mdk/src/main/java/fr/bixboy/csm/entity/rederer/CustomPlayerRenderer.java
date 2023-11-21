package fr.bixboy.csm.entity.rederer;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.entity.model.CustomPlayerModel;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class CustomPlayerRenderer extends LivingRenderer<AbstractClientPlayerEntity, CustomPlayerModel<AbstractClientPlayerEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(CSM.MODID, "textures/entity/buff_zombie.png");

    public CustomPlayerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CustomPlayerModel<>(1.0F, true), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayerEntity resource) {
        return TEXTURE;
    }
}
