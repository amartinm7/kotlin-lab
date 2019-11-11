package com.amm.lab.domain.lesson1

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import java.io.StringReader
import java.net.URL

fun jsonParser(string: String) = string.let { Klaxon().parseJsonObject(StringReader(it)) as JsonObject }

fun dataFrom(url: URL, parser: (String) -> Map<String, Any?>): Map<String, Any?> =
    url.readText().let(parser)

fun main(args: Array<String>) {
    val url = URL("https://api.github.com")
    dataFrom(url, ::jsonParser).apply{ println(this) }
}

