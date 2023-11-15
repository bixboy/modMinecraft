package fr.bixboy.csm.world;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.world.gen.ModEntityGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CSM.MODID)
public class ModWorldEvent {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event)
    {
        ModEntityGeneration.onEntitySpawn(event);
    }
}
