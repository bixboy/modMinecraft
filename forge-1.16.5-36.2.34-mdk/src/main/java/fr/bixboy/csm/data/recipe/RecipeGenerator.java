package fr.bixboy.csm.data.recipe;

import fr.bixboy.csm.CSM;
import fr.bixboy.csm.init.ModBlocks;
import fr.bixboy.csm.init.Moditems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {

    public RecipeGenerator(DataGenerator p_i48262_1_)
    {
        super(p_i48262_1_);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(Blocks.GRASS_BLOCK)
                .pattern(" H ")
                .pattern("HTH")
                .pattern(" H ")
                .define('H', Blocks.GRASS)
                .define('T', Blocks.DIRT)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(Blocks.GRASS, Blocks.DIRT))
                .save(consumer, new ResourceLocation(CSM.MODID, "grass1"));

        ShapedRecipeBuilder.shaped(Blocks.BLACK_WOOL)
                .pattern("TTT")
                .define('T', Blocks.DIRT)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(Blocks.DIRT))
                .save(consumer, new ResourceLocation(CSM.MODID, "wool1"));

        ShapelessRecipeBuilder.shapeless(Moditems.DIVINE_JUICE.get())
                .requires(Moditems.DIVINE_APPLE.get())
                .requires(Blocks.PISTON)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(Moditems.DIVINE_APPLE.get()))
                .save(consumer, new ResourceLocation(CSM.MODID, "divine_juice"));

        //FURNACE

        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.AMETHYST_ORE.get()),
                 Moditems.AMETHYST_GEM.get(), 0.15f, 200)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(ModBlocks.AMETHYST_ORE.get()))
                .save(consumer, new ResourceLocation(CSM.MODID, "amethyst_furn"));

        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.COPPER_ORE.get()),
                        Moditems.COPPER_INGOT.get(), 0.15f, 200)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(ModBlocks.COPPER_ORE.get()))
                .save(consumer, new ResourceLocation(CSM.MODID, "copper_furn"));

        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.AMETHYST_ORE.get()),
                        Moditems.AMETHYST_GEM.get(), 0.15f, 150)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(ModBlocks.AMETHYST_ORE.get()))
                .save(consumer, new ResourceLocation(CSM.MODID, "amethyst_blast"));

        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.COPPER_ORE.get()),
                        Moditems.COPPER_INGOT.get(), 0.15f, 150)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(ModBlocks.COPPER_ORE.get()))
                .save(consumer, new ResourceLocation(CSM.MODID, "copper_blast"));



    }
}
