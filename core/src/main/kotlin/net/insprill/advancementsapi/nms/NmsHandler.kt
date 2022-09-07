package net.insprill.advancementsapi.nms

import org.bukkit.Bukkit

object NmsHandler {

    val nmsImpl = findImpl()

    private fun findImpl(): NmsImpl {
        val pckg = Bukkit.getServer()::class.java.`package`.name.split(".")
        return if (pckg.size >= 4) {
            try {
                Class.forName("${javaClass.`package`.name}.${pckg[3]}.NmsImpl").getConstructor().newInstance() as NmsImpl
            } catch (e: ClassNotFoundException) {
                throw UnsupportedOperationException("NMS version ${pckg[3]} is unsupported")
            }
        } else {
            throw UnsupportedOperationException("Failed to find NMS version")
        }
    }

}
