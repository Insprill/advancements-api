package net.insprill.advancementsapi

import com.google.common.base.Preconditions
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.insprill.advancementsapi.nms.NmsHandler
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementDisplay

class CustomAdvancement(
    private val key: NamespacedKey,
    private val parent: NamespacedKey?,
    private val display: CustomAdvancementDisplay,
    private val criteria: List<AdvancementCriteria>,
    private val requirements: List<List<String>>,
    private val reward: AdvancementReward,
) : Advancement {

    private val json = toJson()

    @Suppress("DEPRECATION") // Unsafe
    fun register() {
        Preconditions.checkArgument(Bukkit.getAdvancement(key) == null, "Advancement already registered. Check #isRegistered before calling again")
        Bukkit.getUnsafe().loadAdvancement(key, json)
    }

    fun isRegistered(): Boolean {
        return Bukkit.getAdvancement(key) != null
    }

    @Suppress("DEPRECATION") // Unsafe
    fun unregister() {
        Preconditions.checkArgument(Bukkit.getAdvancement(key) != null, "Advancement not registered. Check #isRegistered before calling again")
        Bukkit.getUnsafe().removeAdvancement(key)
    }

    /**
     * [Format](https://minecraft.fandom.com/wiki/Advancement/JSON_format#File_format)
     */
    private fun toJson(): String {
        val json = JsonObject()

        if (parent != null) {
            json.addProperty("parent", parent.toString())
        }

        val display = JsonObject()
        val icon = JsonObject()
        icon.addProperty("item", NmsHandler.nmsImpl.getItemKey(this.display.icon))
        icon.addProperty("nbt", NmsHandler.nmsImpl.getItemNbt(this.display.icon))
        display.add("icon", icon)
        display.addProperty("title", this.display.title)
        display.addProperty("frame", this.display.type.name.lowercase())
        display.addProperty("background", this.display.background)
        display.addProperty("description", this.display.description)
        display.addProperty("show_toast", this.display.shouldShowToast())
        display.addProperty("announce_to_chat", this.display.shouldAnnounceChat())
        display.addProperty("hidden", this.display.hidden)
        json.add("display", display)

        val criteria = JsonObject()
        this.criteria.forEach {
            val c = JsonObject()
            c.addProperty("trigger", it.trigger)
            c.add("conditions", it.serializeConditions())
            criteria.add(it.key, c)
        }
        json.add("criteria", criteria)

        val requirements = JsonArray()
        this.requirements.forEach {
            val arr = JsonArray()
            it.forEach { arr.add(it) }
            requirements.add(arr)
        }
        json.add("requirements", requirements)

        val rewards = JsonObject()
        val recipes = JsonArray()
        reward.recipes.forEach { recipes.add(it) }
        rewards.add("recipes", recipes)
        val loot = JsonArray()
        reward.loot.forEach { loot.add(it) }
        rewards.add("loot", recipes)
        rewards.addProperty("experience", reward.experience)
        rewards.addProperty("function", reward.function)
        json.add("rewards", rewards)

        println(json.toString())

        return json.toString()
    }

    override fun getKey(): NamespacedKey {
        return key
    }

    override fun getCriteria(): MutableCollection<String> {
        return criteria.flatMap { it.conditions.keys }.toMutableSet()
    }

    override fun getDisplay(): AdvancementDisplay {
        return display
    }

    companion object {
        @JvmStatic
        fun builder(key: NamespacedKey): Builder {
            return Builder(key)
        }
    }

    @Suppress("UNUSED")
    class Builder(private val key: NamespacedKey) {

        private var parent: NamespacedKey? = null
        private var display = CustomAdvancementDisplay.builder().build()
        private var criteria: List<AdvancementCriteria> = ArrayList()
        private var requirements: List<List<String>> = ArrayList()
        private var reward = AdvancementReward.builder().build()

        fun parent(parent: NamespacedKey?) = apply { this.parent = parent }
        fun display(display: CustomAdvancementDisplay) = apply { this.display = display }
        fun criteria(criteria: List<AdvancementCriteria>) = apply { this.criteria = criteria }
        fun requirements(requirements: List<List<String>>) = apply { this.requirements = requirements }
        fun reward(reward: AdvancementReward) = apply { this.reward = reward }

        fun build(): CustomAdvancement {
            Preconditions.checkArgument(criteria.isNotEmpty(), "Advancement criteria cannot be empty")
            return CustomAdvancement(key, parent, display, criteria, requirements, reward)
        }

    }

}
