package fr.bixboy.csm.gui.playerSize;

import fr.bixboy.csm.CSM;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class customEntityRenderer extends LivingRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(CSM.MODID, "textures/entity/buff_zombie.png");

    public customEntityRenderer(EntityRendererManager renderManagerIn, boolean p_i46103_2_) {
        super(renderManagerIn, new PlayerModel<>(0.0F, p_i46103_2_), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayerEntity entity) {
        return TEXTURE;
    }
}
