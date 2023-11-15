package fr.bixboy.csm;

import fr.bixboy.csm.entity.ModEntities;
import fr.bixboy.csm.entity.rederer.MinotaurRenderer;
import fr.bixboy.csm.gui.playerSize.RightClickListener;
import fr.bixboy.csm.init.ModBlocks;
import fr.bixboy.csm.init.ModFeatures;
import fr.bixboy.csm.init.ModTileEntities;
import fr.bixboy.csm.init.Moditems;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CSM.MODID)
public class CSM {

    public static final String MODID = "csm";

    public CSM()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Moditems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModTileEntities.TILE_ENTITIES.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
    }

    private void setup(FMLCommonSetupEvent e)
    {
        ModFeatures features = new ModFeatures();
        features.init();
        MinecraftForge.EVENT_BUS.register(features);

        EntitySpawnPlacementRegistry.register(ModEntities.MINOTAUR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);

        MinecraftForge.EVENT_BUS.register(new RightClickListener());
    }

    private void clientSetup(FMLClientSetupEvent e)
    {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.MINOTAUR.get(), MinotaurRenderer::new);
    }
}
