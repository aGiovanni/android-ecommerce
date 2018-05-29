package com.example.alan.e_commerce.Services

import com.example.alan.e_commerce.Model.Category
import com.example.alan.e_commerce.Model.Product

object DataService {
    val categories = listOf(
            Category("SHIRTS", "shirtimage"),
            Category("HOODIES", "hoodieimage"),
            Category("HATS", "hatimage"),
            Category("DIGITAL", "digitalgoodsimage")
    )

    val products = listOf(
            Product("Devslopes Graphic Beanie", "hats", "$18", "hat1"),
            Product("Devslopes Hat Black", "hats", "$20", "hat2"),
            Product("Devslopes Hat White", "hats", "$18", "hat3"),
            Product("Devslopes Hat Snapback", "hats", "$22", "hat4"),
            Product("Devslopes Hoodie Gray", "hoodies", "$28", "hoodie1"),
            Product("Devslopes Hoodie Red", "hoodies", "$32", "hoodie2"),
            Product("Devslopes Gray Hoodie", "hoodies", "$28", "hoodie3"),
            Product("Devslopes Black Hoodie", "hoodies", "$32", "hoodie4"),
            Product("Devslopes Shirt Black", "shirts", "$18", "shirt1"),
            Product("Devslopes Badge Light Gray", "shirts", "$20", "shirt2"),
            Product("Devslopes Logo Shirt Red", "shirts", "$18", "shirt3"),
            Product("Devslopes Hustle", "shirts", "$22", "shirt4"),
            Product("Kickflip Studios", "shirts", "$18", "shirt5")
    )
    val digitalProducts = listOf<Product>()

    fun getProducts(search : String) : List<Product> {
        var returnList = listOf<Product>()
        // Dividimos las palabras buscadas; en caso de múltiples categorías:
        if (search.contains(" ")) {
            val words = search.split(" ")
            for (word in words) {
                for (product in products) {
                    if (product.title.toLowerCase().contains(word.toLowerCase())
                            and !(product in returnList)) {
                        returnList += product
                    }
                }
            }
            return returnList
        } else {
            return getListBySearch(search)
        }
    }

    private fun getListBySearch(search: String) : List<Product> {
        var returnList = listOf<Product>()
        for (product in products) {
            if (product.title.toLowerCase().contains(search.toLowerCase())
                    or product.category.toLowerCase().contains(search.toLowerCase())) {
                returnList += product
            }
        }
        return returnList
    }
}