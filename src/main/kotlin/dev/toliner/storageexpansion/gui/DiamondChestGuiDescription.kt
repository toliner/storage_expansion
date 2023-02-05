package dev.toliner.storageexpansion.gui

import dev.toliner.storageexpansion.ChestTier
import dev.toliner.storageexpansion.SEScreenHandlers
import io.github.cottonmc.cotton.gui.SyncedGuiDescription
import io.github.cottonmc.cotton.gui.widget.*
import io.github.cottonmc.cotton.gui.widget.data.Insets
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.text.Text

class DiamondChestGuiDescription(
    syncId: Int,
    playerInventory: PlayerInventory,
    context: ScreenHandlerContext
) : SyncedGuiDescription(
    SEScreenHandlers.DIAMOND_CHEST,
    syncId,
    playerInventory,
    getBlockInventory(context, ChestTier.DIAMOND.capacity),
    getBlockPropertyDelegate(context)
) {

    private var index = 0
    private val maxPage = 2

    init {
        val rootPanel = WGridPanel()
        rootPanel.insets = Insets.ROOT_PANEL
        setRootPanel(rootPanel)
        val cardPanel = WCardPanel()
        for (i in 0 until maxPage) {
            cardPanel.add(i, WItemSlot.of(blockInventory, 108 * i, 18, 6))
        }
        rootPanel.add(cardPanel, 0, 1, 18, 6)

        val text = WText(Text.literal("${index + 1} / $maxPage"))
        rootPanel.add(text, 8, 0, 2, 1)

        val buttonRight = WButton(Text.literal(">")).setOnClick {
            if (index < (maxPage - 1)) {
                index++
                cardPanel.selectedIndex = index
                cardPanel.layout()
                text.text = Text.literal("${index + 1} / $maxPage")
            }
        }
        val buttonLeft = WButton(Text.literal("<")).setOnClick {
            if (index > 0) {
                index--
                cardPanel.selectedIndex = index
                cardPanel.layout()
                text.text = Text.literal("${index + 1} / $maxPage")
            }
        }
        rootPanel.add(buttonRight, 14, 8, 2, 1)
        rootPanel.add(buttonLeft, 1, 8, 2, 1)

        rootPanel.add(createPlayerInventoryPanel(), 4, 7)

        rootPanel.validate(this)
    }
}