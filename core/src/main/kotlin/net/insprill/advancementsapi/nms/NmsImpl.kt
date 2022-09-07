package net.insprill.advancementsapi.nms

import net.insprill.advancementsapi.CustomAdvancement
import org.bukkit.inventory.ItemStack

interface NmsImpl {

    fun getItemKey(item: ItemStack): String

    fun getItemNbt(item: ItemStack): String

    fun createCraftAdvancement(adv: CustomAdvancement): org.bukkit.advancement.Advancement

}
