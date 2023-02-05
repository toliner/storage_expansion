package dev.toliner.storageexpansion.gui

import dev.toliner.storageexpansion.ChestTier
import dev.toliner.storageexpansion.SEScreenHandlers
import io.github.cottonmc.cotton.gui.SyncedGuiDescription
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WItemSlot
import io.github.cottonmc.cotton.gui.widget.data.Insets
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerContext

class CopperChestGuiDescription(
    syncId: Int,
    playerInventory: PlayerInventory,
    context: ScreenHandlerContext
) : SyncedGuiDescription(
    SEScreenHandlers.COPPER_CHEST,
    syncId,
    playerInventory,
    getBlockInventory(context, ChestTier.COPPER.capacity),
    getBlockPropertyDelegate(context)
) {
    init {
        val root = WGridPanel()
        setRootPanel(root)
        root.insets = Insets.ROOT_PANEL

        val slot = WItemSlot.of(blockInventory, 0, 9, 4)
        root.add(slot, 0, 0)
        root.add(this.createPlayerInventoryPanel(), 0, 5)

        root.validate(this)
    }
}