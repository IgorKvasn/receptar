package sk.kvasnicka.receptar.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import sk.kvasnicka.receptar.data.enums.RecipeRating
import java.util.*

@Document(collection = "recipes")
data class Recipe(
    val name: String,
    val description: String,
    val ingredients: List<Ingredient>,
    val tags: List<String>,
    var createDate: Date?,
    var rating: RecipeRating,
    @Id val id: String?
) {

}
