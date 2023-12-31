package net.vyrek.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.DataOutput
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.RecipeProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.util.Identifier
import net.vyrek.tutorialmod.block.ModBlocks
import net.vyrek.tutorialmod.item.ModItems
import net.vyrek.tutorialmod.util.ModIdentifier
import java.util.function.Consumer
import kotlin.math.exp

class ModRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
	private val rubySmeltables: List<ItemConvertible> = listOf(
		ModItems.RAW_RUBY,
		ModBlocks.RUBY_ORE,
		ModBlocks.DEEPSLATE_RUBY_ORE,
		ModBlocks.NETHER_RUBY_ORE,
		ModBlocks.END_STONE_RUBY_ORE
	)

	override fun generate(exporter: Consumer<RecipeJsonProvider>) {
		offerSmelting(
			exporter,
			rubySmeltables,
			RecipeCategory.MISC,
			ModItems.RUBY,
			0.6f,
			400,
			"ruby"
		)
		offerBlasting(
			exporter,
			rubySmeltables,
			RecipeCategory.MISC,
			ModItems.RUBY,
			0.6f,
			200,
			"ruby"
		)
		offerSmelting(
			exporter,
			listOf(ModBlocks.RAW_RUBY_BLOCK),
			RecipeCategory.MISC,
			ModBlocks.RUBY_BLOCK,
			5.4f,
			600,
			"ruby"
		)
		offerBlasting(
			exporter,
			listOf(ModBlocks.RAW_RUBY_BLOCK),
			RecipeCategory.MISC,
			ModBlocks.RUBY_BLOCK,
			5.4f,
			400,
			"ruby"
		)

		offerReversibleCompactingRecipes(
			exporter,
			RecipeCategory.BUILDING_BLOCKS,
			ModItems.RUBY,
			RecipeCategory.DECORATIONS,
			ModBlocks.RUBY_BLOCK
		)
		offerReversibleCompactingRecipes(
			exporter,
			RecipeCategory.BUILDING_BLOCKS,
			ModItems.RAW_RUBY,
			RecipeCategory.DECORATIONS,
			ModBlocks.RAW_RUBY_BLOCK
		)

		ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.TOMATO_SEEDS, 4)
			.input(ModItems.TOMATO)
			.criterion(hasItem(ModItems.TOMATO), RecipeProvider.conditionsFromItem(ModItems.TOMATO))
			.criterion(hasItem(ModItems.TOMATO_SEEDS), RecipeProvider.conditionsFromItem(ModItems.TOMATO_SEEDS))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.TOMATO_SEEDS)))

		ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COAL_BRIQUETTE, 2)
			.input(Items.COAL)
			.criterion(hasItem(Items.COAL), RecipeProvider.conditionsFromItem(Items.COAL))
			.criterion(hasItem(ModItems.COAL_BRIQUETTE), RecipeProvider.conditionsFromItem(ModItems.COAL_BRIQUETTE))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.COAL_BRIQUETTE)))

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.COAL, 1)
			.pattern("B")
			.pattern("B")
			.input('B', ModItems.COAL_BRIQUETTE)
			.criterion(hasItem(Items.COAL), RecipeProvider.conditionsFromItem(Items.COAL))
			.criterion(hasItem(ModItems.COAL_BRIQUETTE), RecipeProvider.conditionsFromItem(ModItems.COAL_BRIQUETTE))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(Items.COAL)))

		createStairsRecipe(ModBlocks.RUBY_STAIRS, Ingredient.ofItems(ModItems.RUBY))
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_STAIRS)))
		createSlabRecipe(
			RecipeCategory.BUILDING_BLOCKS,
			ModBlocks.RUBY_SLAB,
			Ingredient.ofItems(ModItems.RUBY)
		).criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_SLAB)))

		ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.RUBY_BUTTON, 1)
			.input(ModItems.RUBY)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_BUTTON)))
		createPressurePlateRecipe(
			RecipeCategory.REDSTONE,
			ModBlocks.RUBY_PRESSURE_PLATE,
			Ingredient.ofItems(ModItems.RUBY)
		).criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_PRESSURE_PLATE)))

		createFenceRecipe(ModBlocks.RUBY_FENCE, Ingredient.ofItems(ModItems.RUBY))
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_FENCE)))
		createFenceGateRecipe(ModBlocks.RUBY_FENCE_GATE, Ingredient.ofItems(ModItems.RUBY))
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_FENCE_GATE)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBY_WALL, 6)
			.pattern("RRR")
			.pattern("RRR")
			.input('R', ModItems.RUBY)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_WALL)))

		createDoorRecipe(ModBlocks.RUBY_DOOR, Ingredient.ofItems(ModItems.RUBY))
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_DOOR)))
		createTrapdoorRecipe(ModBlocks.RUBY_TRAPDOOR, Ingredient.ofItems(ModItems.RUBY))
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModBlocks.RUBY_TRAPDOOR)))

		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RUBY_SWORD, 1)
			.pattern(" R ")
			.pattern(" R ")
			.pattern(" S ")
			.input('R', ModItems.RUBY)
			.input('S', Items.STICK)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_SWORD)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RUBY_AXE, 1)
			.pattern("RR ")
			.pattern("RS ")
			.pattern(" S ")
			.input('R', ModItems.RUBY)
			.input('S', Items.STICK)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_AXE)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.RUBY_SHOVEL, 1)
			.pattern(" R ")
			.pattern(" S ")
			.pattern(" S ")
			.input('R', ModItems.RUBY)
			.input('S', Items.STICK)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_SHOVEL)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.RUBY_HOE, 1)
			.pattern("RR ")
			.pattern(" S ")
			.pattern(" S ")
			.input('R', ModItems.RUBY)
			.input('S', Items.STICK)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_HOE)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.RUBY_PICKAXE, 1)
			.pattern("RRR")
			.pattern(" S ")
			.pattern(" S ")
			.input('R', ModItems.RUBY)
			.input('S', Items.STICK)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_PICKAXE)))

		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RUBY_HELMET, 1)
			.pattern("RRR")
			.pattern("R R")
			.input('R', ModItems.RUBY)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_HELMET)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RUBY_CHESTPLATE, 1)
			.pattern("R R")
			.pattern("RRR")
			.pattern("RRR")
			.input('R', ModItems.RUBY)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_CHESTPLATE)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RUBY_LEGGINGS, 1)
			.pattern("RRR")
			.pattern("R R")
			.pattern("R R")
			.input('R', ModItems.RUBY)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_LEGGINGS)))
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.RUBY_BOOTS, 1)
			.pattern("R R")
			.pattern("R R")
			.input('R', ModItems.RUBY)
			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
			.offerTo(exporter, ModIdentifier.identifier(getRecipeName(ModItems.RUBY_BOOTS)))

		// raw ruby from ruby and stone
//		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_RUBY, 1)
//			.pattern("SSS")
//			.pattern("SRS")
//			.pattern("SSS")
//			.input('S', Items.STONE)
//			.input('R', ModItems.RUBY)
//			.criterion(hasItem(Items.STONE), RecipeProvider.conditionsFromItem(Items.STONE))
//			.criterion(hasItem(ModItems.RUBY), RecipeProvider.conditionsFromItem(ModItems.RUBY))
//			.offerTo(exporter, Identifier(getRecipeName(ModItems.RAW_RUBY)))
	}
}