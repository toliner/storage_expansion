package dev.toliner.storageexpansion

import dev.toliner.storageexpansion.recipe.RecipeIngredient
import net.minecraft.item.Item
import net.minecraft.util.Identifier

object SERecipes {

    internal fun addRecipes() {

    }

    private fun recipeIngredient(item: Item) = RecipeIngredient.Item(item)
    private fun recipeIngredient(tag: Identifier) = RecipeIngredient.Tag(tag.toString())
    private fun recipeIngredient(tag: String) = RecipeIngredient.Tag(tag)
}