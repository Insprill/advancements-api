package net.insprill.advancementapi

class AdvancementReward(
    val recipes: List<String>,
    val loot: List<String>,
    val experience: Int,
    val function: String,
) {

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    @Suppress("UNUSED")
    class Builder {

        private var recipes: List<String> = ArrayList()
        private var loot: List<String> = ArrayList()
        private var experience = 0
        private var function = ""

        fun recipes(recipes: List<String>) = apply { this.recipes = recipes }
        fun loot(loot: List<String>) = apply { this.loot = loot }
        fun experience(experience: Int) = apply { this.experience = experience }
        fun function(function: String) = apply { this.function = function }

        fun build(): AdvancementReward {
            return AdvancementReward(recipes, loot, experience, function)
        }

    }

}
