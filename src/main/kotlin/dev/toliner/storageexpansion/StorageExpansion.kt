package dev.toliner.storageexpansion

import com.google.gson.JsonElement
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.devtech.arrp.json.lang.JLang
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object StorageExpansion : ModInitializer{

    internal val itemGroup = FabricItemGroup.builder(id("general")).icon { ItemStack(Items.CHEST) }.build()
    internal val resourcePack = RuntimeResourcePack.create(id("runtime"))
    internal val localeEn_Us = JLang.lang().apply {
        itemGroup(id("general"), "Storage Expansion")
    }
    internal val localeJa_Jp = JLang.lang().apply {
        itemGroup(id("general"), "Storage Expansion")
    }
    internal val recipes = mutableMapOf<Identifier, JsonElement>()

    override fun onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register { group ->

        }
        resourcePack.addLang(id("en_us"), localeEn_Us)
        resourcePack.addLang(id("ja_jp"), localeJa_Jp)
        RRPCallback.BEFORE_VANILLA.register { it.add(resourcePack) }
    }
}