package dev.vstd.shoppingcart.domain.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class SellerSearchResult(val data: Data) {
    class Data(private val stores: List<JsonObject>) {
        fun getStores(): List<Store> {
            val parsedStores = mutableListOf<Store>()

            stores.forEach { storeItem ->
                var productStore: String = ""
                var productPrice: String = ""
                var productStoreUrl: String = ""
                storeItem.entrySet().forEach { (key, value) ->
                    when (key) {
                        "product_store" -> productStore = value.asString
                        "product_price" -> productPrice = value.asString
                        "product_store_url" -> productStoreUrl = value.asString
                    }
                }
                if (productStore.isNotBlank() && productPrice.isNotBlank() && productStoreUrl.isNotBlank()) {
                    val store = Store(productStore, productPrice, productStoreUrl)
                    parsedStores.add(store)
                }
            }

            return parsedStores
        }

        class Store(
            @SerializedName("product_store")
            val storeName: String,
            @SerializedName("product_price")
            val price: String,
            @SerializedName("product_store_url")
            val url: String,
        )
    }
}