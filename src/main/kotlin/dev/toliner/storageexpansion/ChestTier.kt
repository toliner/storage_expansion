package dev.toliner.storageexpansion

enum class ChestTier(val capacity: Int) {
    COPPER(36),          // 9x4
    IRON(54),            // 9x6
    GOLD(108),            // 18x6
    DIAMOND(216),        // 18x6x2
    NETHERITE(648),      // 18x9x4

    ;

    val tier: Int
        get() = ordinal
    companion object {
        fun fromInt(tier: Int) = values()[tier]
    }
}