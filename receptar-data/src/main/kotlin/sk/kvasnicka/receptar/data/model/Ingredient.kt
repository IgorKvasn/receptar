package sk.kvasnicka.receptar.data.model

import sk.kvasnicka.receptar.data.enums.QuantityUnit

data class Ingredient(val name: String, val quantity: Int, val unit: QuantityUnit)
