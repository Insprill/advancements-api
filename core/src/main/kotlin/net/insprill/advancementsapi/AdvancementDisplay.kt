package net.insprill.advancementsapi

import org.bukkit.advancement.AdvancementDisplayType
import org.bukkit.inventory.ItemStack

class AdvancementDisplay {

    lateinit var icon: ItemStack
    lateinit var title: String
    lateinit var displayType: AdvancementDisplayType
    lateinit var background: String
    lateinit var description: String
    var showToast: Boolean = true
    var announceToChat: Boolean = true
    var hidden: Boolean = false
    var x: Float = 0.0f
    var y: Float = 0.0f

}
