package net.insprill.advancementsapi.nms

import org.bukkit.Bukkit
import java.lang.UnsupportedOperationException

object NmsHandler {

    val nmsImpl = findImpl()

    private fun findImpl(): NmsImpl {
        val pckg = Bukkit.getServer()::class.java.packageName.split(".")
        @Suppress("UNREACHABLE_CODE") // False positive
        return if (pckg.size >= 4) {
            try {
                return Class.forName("${javaClass.packageName}.${pckg[3]}.NmsImpl").getConstructor().newInstance() as NmsImpl
            } catch (e: ClassNotFoundException) {
                throw UnsupportedOperationException("NMS version ${pckg[3]} is unsupported")
            }
        } else {
            throw UnsupportedOperationException("Failed to find NMS version")
        }
    }

}
