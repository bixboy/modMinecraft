package fr.bixboy.csm.blocks;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Mod.EventBusSubscriber(modid = CSM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)

public class EventHandler {

    private static final Map<UUID, Long> explosionTimers = new HashMap<>();
    private static final Map<UUID, BlockPos> buttonPositions = new HashMap<>();

    @SubscribeEvent
    public static void onButtonPress(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() == ModBlocks.TNT_BUTTON.get()) {

            PlayerEntity player = event.getPlayer();
            BlockPos buttonPos = event.getPos();
            UUID playerUUID = player.getUUID();

            buttonPositions.put(playerUUID, buttonPos);

            if (!explosionTimers.containsKey(playerUUID) || System.currentTimeMillis() - explosionTimers.get(playerUUID) >= 10000) {
                // Supprime l'ancienne temporisation si elle existe
                explosionTimers.remove(playerUUID);
                // Démarre le timer pour ce joueur
                explosionTimers.put(playerUUID, System.currentTimeMillis());
            }
            else {
                player.level.addParticle(ParticleTypes.SMOKE, buttonPos.getX(), buttonPos.getY(), buttonPos.getZ(), 0.0D, 0.0D, 0.0D);

            }
        }
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase == TickEvent.Phase.START) {

            checkPressurePlate(event.player);
        }

        if (event.phase == TickEvent.Phase.START && event.side == LogicalSide.SERVER) {
            UUID playerUUID = event.player.getUUID();

            if (explosionTimers.containsKey(playerUUID)) {

                long elapsedTime = System.currentTimeMillis() - explosionTimers.get(playerUUID);
                BlockPos buttonPos = buttonPositions.get(playerUUID);

                BlockState buttonState = event.player.level.getBlockState(buttonPos);


                if (elapsedTime >= 3000) {
                    // Le délai est écoulé, supprime la temporisation et déclenche l'explosion
                    explosionTimers.remove(playerUUID);
                    if (buttonState.getBlock() == ModBlocks.TNT_BUTTON.get()) {
                        Explode(event.player, buttonPos);
                    }
                } else {
                    event.player.sendMessage(new StringTextComponent("ui."), playerUUID);
                    for (int i = 0; i < 20; i++) {
                        displayParticles((ServerWorld) event.player.level, buttonPos);
                    }
                }
            }
        }
    }

    private static void checkPressurePlate(PlayerEntity player) {
        BlockPos platePos = player.blockPosition();
        BlockState state = player.level.getBlockState(platePos.below()).getBlockState();


        if (player.level.getBlockState(platePos).getBlock() == ModBlocks.TNT_PRESSURE_PLATE.get() && state.getValue(PressurePlateBlock.POWERED)) {
            player.getPose();
            Explode(player, platePos);
        }
    }

    private static void Explode(PlayerEntity player, BlockPos buttonPos){
        player.level.explode(null, buttonPos.getX(), buttonPos.getY(),
                buttonPos.getZ(), 3.0f, Explosion.Mode.BREAK);
    }

    private static void displayParticles(ServerWorld player, BlockPos pos) {
        player.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY(), pos.getZ(), 0.5D, 1.0D, 1.0D);
    }

    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
    }
}
