package dev.vstd.shoppingcart.utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import java.io.BufferedReader

class VietnamAdministrationProvider {
    private val gson = GsonBuilder().create()

    fun getCities(context: Context): List<City> {
        val jsonArray = gson.fromJson(
            context.assets.open("cities.json").bufferedReader().use(BufferedReader::readText),
            JsonArray::class.java
        )

        return jsonArray.map {
            City(
                it.asJsonObject.get("id").asInt,
                it.asJsonObject.get("name").asString
            )
        }
    }

    fun getDistricts(context: Context, cityId: Int): List<District> {
        val jsonArray = gson.fromJson(
            context.assets.open(String.format("%02d.json", cityId)).bufferedReader()
                .use(BufferedReader::readText),
            JsonArray::class.java
        )

        return jsonArray.map {
            District(
                it.asJsonObject.get("id").asInt,
                it.asJsonObject.get("name").asString
            )
        }
    }

    data class City(
        val id: Int,
        val displayName: String,
    )

    data class District(
        val id: Int,
        val displayName: String
    )
}