package dev.toliner.storageexpansion.client

import dev.toliner.storageexpansion.SEScreenHandlers
import dev.toliner.storageexpansion.gui.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Environment(EnvType.CLIENT)
object StorageExpansionClient: ClientModInitializer {
    override fun onInitializeClient() {
        HandledScreens.register(SEScreenHandlers.COPPER_CHEST) { gui, inventory, title -> CopperChestScreen(gui, inventory.player, title)}
        HandledScreens.register(SEScreenHandlers.IRON_CHEST) { gui, inventory, title -> IronChestScreen(gui, inventory.player, title)}
        HandledScreens.register(SEScreenHandlers.GOLD_CHEST) { gui, inventory, title -> GoldChestScreen(gui, inventory.player, title)}
        HandledScreens.register(SEScreenHandlers.DIAMOND_CHEST) { gui, inventory, title -> DiamondChestScreen(gui, inventory.player, title) }
        HandledScreens.register(SEScreenHandlers.NETHERITE_CHEST) { gui, inventory, title -> NetheriteChestScreen(gui, inventory.player, title) }
    }
}
