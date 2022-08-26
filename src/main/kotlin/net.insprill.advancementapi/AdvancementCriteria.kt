package net.insprill.advancementapi

import com.google.gson.Gson
import com.google.gson.JsonElement

class AdvancementCriteria(val key: String, val trigger: String, val conditions: Map<String, Any> = emptyMap()) {

    fun serializeConditions(): JsonElement {
        return Gson().toJsonTree(conditions)
    }

}
