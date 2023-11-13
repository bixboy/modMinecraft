package fr.bixboy.csm.utils;
import fr.bixboy.csm.init.Moditems;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {

    public static final ItemGroup CSM_TAB = new ItemGroup("csmtab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Moditems.AMETHYST_GEM.get());
        }
        final String translatedGroupName = I18n.get("itemGroup.csmtab");
    };
}
