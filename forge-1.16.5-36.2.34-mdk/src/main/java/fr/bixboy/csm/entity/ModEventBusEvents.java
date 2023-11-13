package fr.bixboy.csm.entity;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.entity.custom.MinotaurEntity;
import fr.bixboy.csm.init.Moditems;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CSM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.MINOTAUR.get(), MinotaurEntity.createAttributes().build());
    }

    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        Moditems.MINOTAUR.get();
    }
}
