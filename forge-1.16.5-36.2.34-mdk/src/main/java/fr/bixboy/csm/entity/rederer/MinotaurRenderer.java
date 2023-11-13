package fr.bixboy.csm.entity.rederer;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.entity.custom.MinotaurEntity;
import fr.bixboy.csm.entity.model.MinotaurModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MinotaurRenderer extends MobRenderer<MinotaurEntity, MinotaurModel<MinotaurEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(CSM.MODID, "textures/entity/buff_zombie.png");

    public MinotaurRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MinotaurModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(MinotaurEntity entity) {
        return TEXTURE;
    }
}
