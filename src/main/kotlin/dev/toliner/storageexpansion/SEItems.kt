package dev.toliner.storageexpansion

import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.models.JTextures
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object SEItems {

    private val items = mutableListOf<Item>()

    val storageCore by lazy {
        addSimpleItem("storage_core", "Storage Core", "収納の核")
    }

    private fun addSimpleItem(name: String, localizationEn: String, localizationJp: String = localizationEn): Item =
        addItem(Item(Item.Settings()), name, localizationEn, localizationJp)

    internal fun addBlockItem(
        block: Block,
        name: String,
        localizationEn: String,
        localizationJp: String = localizationEn,
    ): Item =
        addItem(
            BlockItem(block, FabricItemSettings()),
            name,
            localizationEn,
            localizationJp,
            id("block/$name").toString()
        )

    private fun addItem(
        item: Item,
        name: String,
        localizationEn: String,
        localizationJp: String = localizationEn,
        modelParent: String = "item/generated",
    ): Item {
        val registered = Registry.register(Registries.ITEM, id(name), item)
        StorageExpansion.localeEn_Us.item(id(name), localizationEn)
        StorageExpansion.localeJa_Jp.item(id(name), localizationJp)
        StorageExpansion.resourcePack.addModel(
            JModel.model(modelParent).textures(JTextures().layer0("$MOD_ID:item/$name")),
            id("item/$name")
        )
        items.add(registered)
        return registered
    }

    internal fun addAllItemToCreativeTab(group: FabricItemGroupEntries) {
        items.forEach { group.add(it) }
    }
}