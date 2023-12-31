package net.vyrek.tutorialmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.vyrek.tutorialmod.block.ModBlocks
import net.vyrek.tutorialmod.item.ModItemGroups
import net.vyrek.tutorialmod.item.ModItems
import net.vyrek.tutorialmod.util.ModLootTableModifiers
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TutorialMod : ModInitializer {
	@JvmStatic
	val MOD_ID: String = "tutorialmod"
	val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItemGroups.registerItemGroups()
		ModItems.registerModItems()

		ModBlocks.registerModBlocks()

		ModLootTableModifiers.modifyLootTables()

		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200)
	}
}