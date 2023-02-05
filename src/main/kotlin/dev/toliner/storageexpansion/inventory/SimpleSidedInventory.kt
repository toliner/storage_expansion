package dev.toliner.storageexpansion.inventory

import net.minecraft.inventory.SidedInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Direction

open class SimpleSidedInventory(
    size: Int
): SimpleInventory(size), SidedInventory{
    override fun getAvailableSlots(side: Direction?): IntArray = (0 until size()).toList().toIntArray()

    override fun canInsert(slot: Int, stack: ItemStack?, dir: Direction?): Boolean = isValid(slot, stack)

    override fun canExtract(slot: Int, stack: ItemStack?, dir: Direction?): Boolean = true
}
