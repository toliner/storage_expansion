package dev.toliner.storageexpansion.gui

import dev.toliner.storageexpansion.ChestTier
import dev.toliner.storageexpansion.SEScreenHandlers
import io.github.cottonmc.cotton.gui.SyncedGuiDescription
import io.github.cottonmc.cotton.gui.widget.WGridPanel
import io.github.cottonmc.cotton.gui.widget.WItemSlot
import io.github.cottonmc.cotton.gui.widget.data.Insets
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerContext

class GoldChestGuiDescription(
    syncId: Int,
    playerInventory: PlayerInventory,
    context: ScreenHandlerContext
) : SyncedGuiDescription(
    SEScreenHandlers.GOLD_CHEST,
    syncId,
    playerInventory,
    getBlockInventory(context, ChestTier.GOLD.capacity),
    getBlockPropertyDelegate(context)
) {
    init {
        val root = WGridPanel()
        setRootPanel(root)
        root.insets = Insets.ROOT_PANEL
        val slot = WItemSlot.of(blockInventory, 0, 18, 6)
        root.add(slot, 0, 1)
        root.add(this.createPlayerInventoryPanel(), 4, 7)

        root.validate(this)
    }
}