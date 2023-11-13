package fr.bixboy.csm.data;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.data.recipe.RecipeGenerator;
import fr.bixboy.csm.entity.ModEntities;
import fr.bixboy.csm.entity.custom.MinotaurEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = CSM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent e)
    {
        DataGenerator generator = e.getGenerator();

        if (e.includeServer())
        {
            generator.addProvider(new RecipeGenerator(generator));
        }
    }
}
