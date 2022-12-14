package net.insprill.advancementsapi

import com.google.common.base.Preconditions
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.insprill.advancementsapi.nms.NmsHandler
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement

class CustomAdvancement() {

    lateinit var key: NamespacedKey
    var parent: NamespacedKey? = null
    var display: AdvancementDisplay? = null
    var criteria: MutableList<AdvancementCriteria> = ArrayList()
    lateinit var requirements: List<List<String>>
    lateinit var reward: AdvancementReward

    private constructor(
        key: NamespacedKey,
        parent: NamespacedKey?,
        display: AdvancementDisplay?,
        criteria: MutableList<AdvancementCriteria>,
        requirements: List<List<String>>,
        reward: AdvancementReward
    ) : this() {
        this.key = key
        this.parent = parent
        this.display = display
        this.criteria = criteria
        this.requirements = requirements
        this.reward = reward
    }

    fun display(init: AdvancementDisplay.() -> Unit) {
        display = AdvancementDisplay()
        display!!.init()
    }

    fun criteria(init: AdvancementCriteria.() -> Unit) {
        val criteria = AdvancementCriteria()
        criteria.init()
        this.criteria.add(criteria)
    }

    fun reward(init: AdvancementReward.() -> Unit) {
        reward = AdvancementReward()
        reward.init()
    }

    fun build(): Advancement {
        return NmsHandler.nmsImpl.createCraftAdvancement(
            CustomAdvancement(
                key, parent, display, criteria, requirements, reward
            )
        )
    }

    @Suppress("DEPRECATION") // Unsafe
    fun register() {
        Preconditions.checkArgument(Bukkit.getAdvancement(key) == null, "Advancement already registered. Check #isRegistered before calling again")
        Bukkit.getUnsafe().loadAdvancement(key, toJson())
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
    fun toJson(): String {
        val json = JsonObject()

        if (this.parent != null) {
            json.addProperty("parent", this.parent.toString())
        }

        if (this.display != null) {
            val advDisplay = this.display!!
            val display = JsonObject()
            val icon = JsonObject()
            icon.addProperty("item", NmsHandler.nmsImpl.getItemKey(advDisplay.icon))
            icon.addProperty("nbt", NmsHandler.nmsImpl.getItemNbt(advDisplay.icon))
            display.add("icon", icon)
            display.addProperty("title", advDisplay.title)
            display.addProperty("frame", advDisplay.displayType.name.lowercase())
            display.addProperty("background", advDisplay.background)
            display.addProperty("description", advDisplay.description)
            display.addProperty("show_toast", advDisplay.showToast)
            display.addProperty("announce_to_chat", advDisplay.announceToChat)
            display.addProperty("hidden", advDisplay.hidden)
            json.add("display", display)
        }

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

        return json.toString()
    }

    companion object {
        fun customAdvancement(init: CustomAdvancement.() -> Unit): Advancement {
            val builder = CustomAdvancement()
            builder.init()
            return builder.build()
        }
    }

}
