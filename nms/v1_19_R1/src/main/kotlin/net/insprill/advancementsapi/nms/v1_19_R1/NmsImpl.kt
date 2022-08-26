package net.insprill.advancementsapi.nms.v1_19_R1

import net.insprill.advancementsapi.nms.NmsImpl
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class NmsImpl : NmsImpl {

    override fun getItemKey(item: ItemStack): String {
        return CraftItemStack.asNMSCopy(item).item.toString()
    }

    override fun getItemNbt(item: ItemStack): String {
        return CraftItemStack.asNMSCopy(item).tag.toString()
    }

}
