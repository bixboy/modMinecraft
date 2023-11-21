package fr.bixboy.csm.entity;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.entity.custom.MinotaurEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, CSM.MODID);

    public static final RegistryObject<EntityType<MinotaurEntity>> MINOTAUR = ENTITY_TYPES.register("minotaur",
            () -> EntityType.Builder.of(MinotaurEntity::new, EntityClassification.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(CSM.MODID, "minotaur").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
