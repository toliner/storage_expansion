package dev.toliner.storageexpansion

import dev.toliner.storageexpansion.recipe.RecipeGenerator
import dev.toliner.storageexpansion.recipe.RecipeIngredient
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object SERecipes {
    private val generator by lazy { RecipeGenerator(StorageExpansion.resourcePack) }

    internal fun addRecipes() {
        generator.addShapedRecipe(
            id("storage_core"),
            listOf(recipeIngredient(Items.LAPIS_LAZULI), recipeIngredient(Items.CHEST)),
            ItemStack(SEItems.storageCore)
        ) { (lapis, chest) ->
            set(
                null, lapis, null,
                lapis, chest, lapis,
                null, lapis, null
            )
        }

        generator.addShapedRecipe(
            id("copper_chest"),
            listOf(
                recipeIngredient(SEItems.storageCore),
                recipeIngredient(Items.CHEST),
                recipeIngredient(Items.COPPER_INGOT)
            ),
            ItemStack(SEBlocks.copperChest)
        ) { (core, chest, ingot) ->
            set(
                ingot, core, ingot,
                ingot, chest, ingot,
                ingot, ingot, ingot
            )
        }

        generator.addShapedRecipe(
            id("iron_chest"),
            listOf(
                recipeIngredient(SEItems.storageCore),
                recipeIngredient(SEBlocks.copperChest),
                recipeIngredient(Items.IRON_INGOT)
            ),
            ItemStack(SEBlocks.ironChest)
        ) { (core, chest, ingot) ->
            set(
                ingot, core, ingot,
                ingot, chest, ingot,
                ingot, ingot, ingot
            )
        }

        generator.addShapedRecipe(
            id("gold_chest"),
            listOf(
                recipeIngredient(SEItems.storageCore),
                recipeIngredient(SEBlocks.ironChest),
                recipeIngredient(Items.GOLD_INGOT)
            ),
            ItemStack(SEBlocks.goldChest)
        ) { (core, chest, ingot) ->
            set(
                ingot, core, ingot,
                ingot, chest, ingot,
                ingot, ingot, ingot
            )
        }

        generator.addShapedRecipe(
            id("diamond_chest"),
            listOf(
                recipeIngredient(SEItems.storageCore),
                recipeIngredient(SEBlocks.goldChest),
                recipeIngredient(Items.DIAMOND),
                recipeIngredient(Items.GLASS)
            ),
            ItemStack(SEBlocks.diamondChest)
        ) { (core, chest, ingot, glass) ->
            set(
                ingot, core, ingot,
                glass, chest, glass,
                ingot, glass, ingot
            )
        }

        generator.addShapedRecipe(
            id("netherite_chest"),
            listOf(
                recipeIngredient(SEItems.storageCore),
                recipeIngredient(SEBlocks.diamondChest),
                recipeIngredient(Items.NETHERITE_INGOT),
                recipeIngredient(Items.GLOWSTONE),
                recipeIngredient(Items.EMERALD)
            ),
            ItemStack(SEBlocks.netheriteChest)
        ) { (core, chest, ingot, gs, emerald) ->
            set(
                ingot, core, ingot,
                emerald, chest, emerald,
                gs, gs, gs
            )
        }
    }

    private fun recipeIngredient(item: Item) = RecipeIngredient.Item(item)
    private fun recipeIngredient(block: Block) = RecipeIngredient.Item(SEBlocks.blockItems.getValue(block))
    private fun recipeIngredient(tag: Identifier) = RecipeIngredient.Tag(tag.toString())
    private fun recipeIngredient(tag: String) = RecipeIngredient.Tag(tag)
}