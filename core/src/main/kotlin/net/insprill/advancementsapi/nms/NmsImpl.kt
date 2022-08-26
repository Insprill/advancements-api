package net.insprill.advancementsapi.nms

import org.bukkit.inventory.ItemStack

interface NmsImpl {

    fun getItemKey(item: ItemStack): String

    fun getItemNbt(item: ItemStack): String

}
