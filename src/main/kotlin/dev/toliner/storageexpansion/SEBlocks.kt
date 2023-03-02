package dev.toliner.storageexpansion

import dev.toliner.storageexpansion.block.ChestBlock
import net.devtech.arrp.json.blockstate.JBlockModel
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.blockstate.JVariant
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.models.JTextures
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object SEBlocks {
    val blockItems: Map<Block, Item>
        get() = _blockItems
    private val _blockItems: MutableMap<Block, Item> = mutableMapOf()

    val copperChest by lazy {
        addBlock(
            block = ChestBlock(ChestTier.COPPER),
            name = "copper_chest",
            localizationEn = "Copper Chest",
            localizationJp = "銅のチェスト",
            useDefaultState = false,
            useDefaultModel = false,
        )
    }

    val ironChest by lazy {
        addBlock(
            block = ChestBlock(ChestTier.IRON),
            name = "iron_chest",
            localizationEn = "Iron Chest",
            localizationJp = "鉄のチェスト",
            useDefaultState = false,
            useDefaultModel = false,
        )
    }

    val goldChest by lazy {
        addBlock(
            block = ChestBlock(ChestTier.GOLD),
            name = "gold_chest",
            localizationEn = "Gold Chest",
            localizationJp = "金のチェスト",
            useDefaultState = false,
            useDefaultModel = false,
        )
    }

    val diamondChest by lazy {
        addBlock(
            block = ChestBlock(ChestTier.DIAMOND),
            name = "diamond_chest",
            localizationEn = "Diamond Chest",
            localizationJp = "ダイヤモンドのチェスト",
            useDefaultState = false,
            useDefaultModel = false,
        )
    }

    val netheriteChest by lazy {
        addBlock(
            block = ChestBlock(ChestTier.NETHERITE),
            name = "netherite_chest",
            localizationEn = "Netherite Chest",
            localizationJp = "ネザライトのチェスト",
            useDefaultState = false,
            useDefaultModel = false,
        )
    }

    private fun addSimpleBlock(
        name: String,
        localizationEn: String,
        localizationJp: String = localizationEn,
        material: Material = Material.STONE,
        useDefaultState: Boolean = true,
        useDefaultModel: Boolean = true,
    ): Block = addBlock(
        Block(FabricBlockSettings.of(material)),
        name, localizationEn, localizationJp, useDefaultState, useDefaultModel
    )

    private fun addBlock(
        block: Block,
        name: String,
        localizationEn: String,
        localizationJp: String = localizationEn,
        useDefaultState: Boolean = true,
        useDefaultModel: Boolean = true
    ): Block {
        val id = id(name)
        val registeredBlock = Registry.register(Registries.BLOCK, id, block)
        val blockItem = SEItems.addBlockItem(block, name, localizationEn, localizationJp)
        _blockItems[registeredBlock] = blockItem
        StorageExpansion.localeEn_Us.block(id, localizationEn)
        StorageExpansion.localeJa_Jp.block(id, localizationJp)
        val path = id("block/$name")
        if (useDefaultState) {
            StorageExpansion.resourcePack.addBlockState(
                JState.state().add(JVariant().put("", JBlockModel(path))),
                id
            )
        }
        if (useDefaultModel) {
            StorageExpansion.resourcePack.addModel(
                JModel.model("block/cube_all").textures(JTextures().`var`("all", path.toString())),
                path
            )
        }
        return registeredBlock
    }
}