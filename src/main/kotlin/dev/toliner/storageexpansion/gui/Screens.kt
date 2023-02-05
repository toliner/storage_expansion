package dev.toliner.storageexpansion.gui

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text

class CopperChestScreen(description: CopperChestGuiDescription, player: PlayerEntity, title: Text) :
    CottonInventoryScreen<CopperChestGuiDescription>(description, player, title)

class IronChestScreen(description: IronChestGuiDescription, player: PlayerEntity, title: Text) :
    CottonInventoryScreen<IronChestGuiDescription>(description, player, title)

class GoldChestScreen(description: GoldChestGuiDescription, player: PlayerEntity, title: Text) :
    CottonInventoryScreen<GoldChestGuiDescription>(description, player, title)

class DiamondChestScreen(description: DiamondChestGuiDescription, player: PlayerEntity, title: Text) :
    CottonInventoryScreen<DiamondChestGuiDescription>(description, player, title)

class NetheriteChestScreen(description: NetheriteChestGuiDescription, player: PlayerEntity, title: Text) :
    CottonInventoryScreen<NetheriteChestGuiDescription>(description, player, title)
