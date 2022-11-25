package net.insprill.advancementsapiplugin

import net.insprill.advancementsapi.CustomAdvancement.Companion.customAdvancement
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class AdvancementsAPI : JavaPlugin() {

    override fun onEnable() {
        customAdvancement {
            key = NamespacedKey(this@AdvancementsAPI, "MyAdvancement")
            register()
        }
    }

}
