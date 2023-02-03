package dev.toliner.storageexpansion.recipe

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.toliner.storageexpansion.StorageExpansion
import dev.toliner.storageexpansion.id
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

internal object RecipeGenerator {
    fun addShapedRecipe(id: Identifier, ingredients: List<RecipeIngredient<*>>, output: ItemStack, configure: ShapedRecipeBuilder.(List<RecipeIngredient<*>>) -> Unit) {
        val (pattern, patternMap) = ShapedRecipeBuilder(ingredients).apply { configure(ingredients) }.build()
        @Suppress("UNCHECKED_CAST")
        val patternList = patternMap.toList().filter { it.second != null } as  List<Pair<Char, RecipeIngredient<*>>>
        val json = createShapedRecipeJson(
            patternList.map { it.first },
            patternList.map { it.second },
            pattern.map { list -> list.joinToString(separator = "") { it?.toString() ?: " " } },
            output
        )
        StorageExpansion.recipes += id to json
    }

    private fun createShapedRecipeJson(keys: List<Char>, items: List<RecipeIngredient<*>>, pattern: List<String>, out: ItemStack): JsonObject =
        jsonObject {
            addProperty("type", "minecraft:crafting_shaped")
            addProperty("group", "voidtech")
            add("pattern", jsonArray {
                for (i in 0..2) {
                    add(pattern[i])
                }
            })
            add("key", keys.indices.map {
                keys[it] to jsonObject { addProperty(items[it].type, items[it].id.toString()) }
            }.fold(JsonObject()) { r, t ->
                r.apply { add(t.first.toString(), t.second)}
            })
            add("result", jsonObject {
                addProperty("item", Registries.ITEM.getId(out.item).toString())
                addProperty("count", out.count)
            })
        }

    fun addShapelessRecipe(id: Identifier, ingredients: List<RecipeIngredient<*>>, output: ItemStack) {
        val json = createShapelessRecipeJson(ingredients, output)
        StorageExpansion.recipes += id to json
    }

    fun addShapelessCompressRecipe(id: Identifier, count: Int, input: RecipeIngredient<*>, output: ItemStack) {
        val list = buildList {
            repeat(count) {
                add(input)
            }
        }
        addShapelessRecipe(id, list, output)
    }

    fun addShapelessDecompressRecipe(id: Identifier, input: RecipeIngredient<*>, output: ItemStack) {
        addShapelessRecipe(id, listOf(input), output)
    }

    fun addShapelessReversableCompressRecipe(id: Identifier, count: Int, small: Item, large: Item) {
        addShapelessCompressRecipe(id("${id.path}_up"), count, RecipeIngredient.Item(small), ItemStack(large))
        addShapelessDecompressRecipe(id("${id.path}_down"), RecipeIngredient.Item(large), ItemStack(small, count))
    }

    private fun createShapelessRecipeJson(items: List<RecipeIngredient<*>>, out: ItemStack): JsonObject =
        jsonObject {
            addProperty("type", "minecraft:crafting_shapeless")
            addProperty("group", "voidtech")
            add("ingredients", jsonArray {
                for (item in items) {
                    add(JsonObject().apply {
                        addProperty(item.type, item.id.toString())
                    })
                }
            })
            add("result", jsonObject {
                addProperty("item", Registries.ITEM.getId(out.item).toString())
                addProperty("count", out.count)
            })
        }

    private inline fun jsonObject(configure: JsonObject.() -> Unit): JsonObject = JsonObject().apply(configure)
    private inline fun jsonArray(configure: JsonArray.() -> Unit): JsonArray = JsonArray().apply(configure)
}
