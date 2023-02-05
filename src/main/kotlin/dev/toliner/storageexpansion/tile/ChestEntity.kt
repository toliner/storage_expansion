package dev.toliner.storageexpansion.tile

import dev.toliner.storageexpansion.ChestTier
import dev.toliner.storageexpansion.StorageExpansion
import dev.toliner.storageexpansion.gui.*
import dev.toliner.storageexpansion.inventory.SimpleSidedInventory
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage
import net.minecraft.block.BlockState
import net.minecraft.block.InventoryProvider
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.network.Packet
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldAccess

class ChestEntity @JvmOverloads constructor(pos: BlockPos, state: BlockState, private var tier: ChestTier = ChestTier.COPPER) :
    BlockEntity(StorageExpansion.chestEntityType, pos, state),
    InventoryProvider,
    NamedScreenHandlerFactory
{

    init {
        markDirty()
    }

    private val inventory by lazy { object : SimpleSidedInventory(tier.capacity) {
        override fun markDirty() {
            this@ChestEntity.markDirty()
            super.markDirty()
        }
    } }
    val storage by lazy { InventoryStorage.of(inventory, null) }

    override fun writeNbt(nbt: NbtCompound) {
        nbt.putInt("tier", tier.tier)
        nbt.put("inventory", inventory.toNbtList())
        super.writeNbt(nbt)
    }

    override fun readNbt(nbt: NbtCompound) {
        tier = ChestTier.fromInt(nbt.getInt("tier"))
        inventory.readNbtList(nbt.getList("inventory", NbtElement.COMPOUND_TYPE.toInt()))
        super.readNbt(nbt)
    }

    override fun toUpdatePacket(): Packet<ClientPlayPacketListener>? {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }

    override fun getInventory(state: BlockState, world: WorldAccess, pos: BlockPos): SidedInventory = inventory
    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): ScreenHandler {
        return when(tier) {
            ChestTier.COPPER -> CopperChestGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos))
            ChestTier.IRON -> IronChestGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos))
            ChestTier.GOLD -> GoldChestGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos))
            ChestTier.DIAMOND -> DiamondChestGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos))
            ChestTier.NETHERITE -> NetheriteChestGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos))
        }
    }

    override fun getDisplayName(): Text {
        return Text.translatable(cachedState.block.translationKey)
    }
}
