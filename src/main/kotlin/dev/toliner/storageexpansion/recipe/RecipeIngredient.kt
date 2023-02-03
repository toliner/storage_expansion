package dev.toliner.storageexpansion.recipe

import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.item.Item as McItem

internal sealed interface RecipeIngredient<T> {
    val type: String
    val content: T
    val id: Identifier

    data class Item(override val content: McItem) : RecipeIngredient<McItem> {
        override val type = "item"
        override val id: Identifier
            get() = Registries.ITEM.getId(content)
    }

    data class Tag(override val content: String) : RecipeIngredient<String> {
        override val type = "tag"
        override val id: Identifier
            get() = Identifier(content)
    }
}