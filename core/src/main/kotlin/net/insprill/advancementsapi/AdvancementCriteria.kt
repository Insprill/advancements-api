package net.insprill.advancementsapi

import com.google.gson.Gson
import com.google.gson.JsonElement

class AdvancementCriteria {

    lateinit var key: String
    lateinit var trigger: String
    var conditions: Map<String, Any> = emptyMap()

    fun serializeConditions(): JsonElement {
        return Gson().toJsonTree(conditions)
    }

}
