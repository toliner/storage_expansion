package dev.toliner.storageexpansion

import com.google.gson.JsonElement
import dev.toliner.storageexpansion.tile.ChestEntity
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.devtech.arrp.json.lang.JLang
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object StorageExpansion : ModInitializer {

    internal val itemGroup = FabricItemGroup.builder(id("general")).icon { ItemStack(Items.CHEST) }.build()
    internal val resourcePack = RuntimeResourcePack.create(id("runtime"))
    internal val localeEn_Us = JLang.lang().apply {
        itemGroup(id("general"), "Storage Expansion")
    }
    internal val localeJa_Jp = JLang.lang().apply {
        itemGroup(id("general"), "Storage Expansion")
    }
    internal val recipes = mutableMapOf<Identifier, JsonElement>()

    val chestEntityType: BlockEntityType<ChestEntity> by lazy {
        FabricBlockEntityTypeBuilder.create(
            ::ChestEntity,
            SEBlocks.copperChest, SEBlocks.ironChest, SEBlocks.goldChest, SEBlocks.diamondChest, SEBlocks.netheriteChest
        ).build()
    }

    override fun onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register { group ->
            SEBlocks.blockItems.values.forEach { group.add(it) }
        }

        resourcePack.addLang(id("en_us"), localeEn_Us)
        resourcePack.addLang(id("ja_jp"), localeJa_Jp)
        RRPCallback.BEFORE_VANILLA.register { it.add(resourcePack) }
    }
}
