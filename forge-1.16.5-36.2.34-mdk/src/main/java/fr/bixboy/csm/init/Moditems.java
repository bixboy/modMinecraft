package fr.bixboy.csm.init;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.entity.ModEntities;
import fr.bixboy.csm.utils.CustomArmorMaterials;
import fr.bixboy.csm.utils.CustomItemTiers;
import fr.bixboy.csm.utils.ModItemGroups;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;

public class Moditems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CSM.MODID);

    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item(new Item.Properties().tab(ModItemGroups.CSM_TAB)));
    public static final RegistryObject<Item> COPPER_STICK = ITEMS.register( "copper_stick", () -> new Item(new Item.Properties().tab(ModItemGroups.CSM_TAB).stacksTo(1)));

    public static final RegistryObject<Item> MIRROR = ITEMS.register( "mirror", () -> new Item(new Item.Properties().tab(ModItemGroups.CSM_TAB).stacksTo(1)));

    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new SwordItem(CustomItemTiers.COPPER, 2, -2.4f, new Item.Properties().stacksTo(1).tab(ModItemGroups.CSM_TAB)));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(CustomItemTiers.COPPER, 0, -2.4f, new Item.Properties().stacksTo(1).tab(ModItemGroups.CSM_TAB)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new AxeItem(CustomItemTiers.COPPER, 0, -2.4f, new Item.Properties().stacksTo(1).tab(ModItemGroups.CSM_TAB)));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new ShovelItem(CustomItemTiers.COPPER, 0, -2.4f, new Item.Properties().stacksTo(1).tab(ModItemGroups.CSM_TAB)));

    public static final RegistryObject<Item> AMETHYST_GEM = ITEMS.register("amethyst_gem", () -> new Item(new Item.Properties().tab(ModItemGroups.CSM_TAB)));

    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", () -> new ArmorItem(CustomArmorMaterials.AMETHYST_AMOR, EquipmentSlotType.HEAD, new Item.Properties().tab(ModItemGroups.CSM_TAB)));
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", () -> new ArmorItem(CustomArmorMaterials.AMETHYST_AMOR, EquipmentSlotType.CHEST, new Item.Properties().tab(ModItemGroups.CSM_TAB)));
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", () -> new ArmorItem(CustomArmorMaterials.AMETHYST_AMOR, EquipmentSlotType.LEGS, new Item.Properties().tab(ModItemGroups.CSM_TAB)));
    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", () -> new ArmorItem(CustomArmorMaterials.AMETHYST_AMOR, EquipmentSlotType.FEET, new Item.Properties().tab(ModItemGroups.CSM_TAB)));

    public static final  RegistryObject<Item> DIVINE_APPLE = ITEMS.register("divine_apple", () -> new Item(new Item.Properties()
            .tab(ModItemGroups.CSM_TAB)
            .food(new Food.Builder().nutrition(3).saturationMod(0.8f).effect(() -> new EffectInstance(Effects.LEVITATION, 20*5, 3), 1.0f).build())));

    public static final RegistryObject<Item> DIVINE_JUICE = ITEMS.register("divine_juice", () -> new Item(new Item.Properties()
            .tab(ModItemGroups.CSM_TAB)
            .food(new Food.Builder().nutrition(2).saturationMod(0.3f).effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 20*15, 3), 1.0f).build()))
    {
        @Override
        public UseAction getUseAnimation(ItemStack stack) {
            return UseAction.DRINK;
        }
    });

    public static final RegistryObject<ForgeSpawnEggItem> MINOTAUR = ITEMS.register("minotaur",
            () -> new ForgeSpawnEggItem(ModEntities.MINOTAUR, 0xFF55AA, 0x27DA9F, new Item.Properties().stacksTo(64).tab(ModItemGroups.CSM_TAB)));

}
