package dev.toliner.storageexpansion

enum class ChestTier(val capacity: Int) {
    COPPER(36),          // 9x4
    IRON(54),            // 9x6
    GOLD(108),            // 12x9
    DIAMOND(216),        // 18x6x2
    NETHERITE(432),      // 18x6x4

    ;

    val tier: Int
        get() = ordinal
    companion object {
        fun fromInt(tier: Int) = values()[tier]
    }
}