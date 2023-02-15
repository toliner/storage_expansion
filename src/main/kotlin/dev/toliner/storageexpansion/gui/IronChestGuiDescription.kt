package dev.toliner.storageexpansion.gui

import dev.toliner.storageexpansion.ChestTier
import dev.toliner.storageexpansion.SEScreenHandlers
import io.github.cottonmc.cotton.gui.SyncedGuiDescription
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WItemSlot
import io.github.cottonmc.cotton.gui.widget.data.Insets
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerContext

class IronChestGuiDescription(
    syncId: Int,
    playerInventory: PlayerInventory,
    context: ScreenHandlerContext
) : SyncedGuiDescription(
    SEScreenHandlers.IRON_CHEST,
    syncId,
    playerInventory,
    getBlockInventory(context, ChestTier.IRON.capacity),
    getBlockPropertyDelegate(context)
) {
    init {
        val root = WGridPanel()
        setRootPanel(root)
        root.insets = Insets.ROOT_PANEL

        val slot = WItemSlot.of(blockInventory, 0, 9, 6)
        root.add(slot, 0, 1)
        root.add(this.createPlayerInventoryPanel(), 0, 7)

        root.validate(this)
    }
}