package fr.bixboy.csm.gui.playerSize;

import fr.bixboy.csm.init.Moditems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RightClickListener {

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event) {
        // Récupérer l'objet tenu par le joueur lors du clic droit
        ItemStack heldItem = event.getPlayer().getItemInHand(Hand.MAIN_HAND);

        // Vérifier si l'objet tenu est celui qui doit ouvrir l'interface
        if (heldItem.getItem() == Moditems.MIRROR.get()) {
            // Ouvrir votre interface ici
            Minecraft.getInstance().setScreen(new PlayerSizeGUI());

            // Supprimer l'item après son utilisation
            PlayerEntity player = event.getPlayer();
            if (!player.isCreative()) {
                heldItem.shrink(1); // Diminuer la quantité de l'objet tenu
                if (heldItem.isEmpty()) {
                    player.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY); // Remplace l'objet tenu par un ItemStack vide
                }
            }
        }
    }

}
