package dev.toliner.storageexpansion

import dev.toliner.storageexpansion.gui.*
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.ScreenHandlerType

object SEScreenHandlers {

    fun registerAll() {
        register("copper_chest", COPPER_CHEST)
        register("iron_chest", IRON_CHEST)
        register("gold_chest", GOLD_CHEST)
        register("diamond_chest", DIAMOND_CHEST)
    }

    val COPPER_CHEST = ScreenHandlerType { syncId, playerInventory ->
        CopperChestGuiDescription(
            syncId,
            playerInventory,
            ScreenHandlerContext.EMPTY
        )
    }

    val IRON_CHEST = ScreenHandlerType { syncId, playerInventory ->
        IronChestGuiDescription(
            syncId,
            playerInventory,
            ScreenHandlerContext.EMPTY
        )
    }

    val GOLD_CHEST = ScreenHandlerType { syncId, playerInventory ->
        GoldChestGuiDescription(
            syncId,
            playerInventory,
            ScreenHandlerContext.EMPTY
        )
    }

    val DIAMOND_CHEST = ScreenHandlerType { syncId, playerInventory ->
        DiamondChestGuiDescription(
            syncId,
            playerInventory,
            ScreenHandlerContext.EMPTY
        )
    }

    val NETHERITE_CHEST = ScreenHandlerType { syncId, playerInventory ->
        NetheriteChestGuiDescription(
            syncId,
            playerInventory,
            ScreenHandlerContext.EMPTY
        )
    }

    private fun register(name: String, entry: ScreenHandlerType<*>) = Registry.register(Registries.SCREEN_HANDLER, id(name), entry)
}