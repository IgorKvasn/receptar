package sk.kvasnicka.receptar.data.model

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "recipes")
@JsonView(Recipe.Companion.DetailRecipe::class)
data class Recipe(
  @JsonView(value =[Recipe.Companion.OverviewRecipe::class, Recipe.Companion.DetailRecipe::class])
  val name: String,

  @JsonView(Recipe.Companion.DetailRecipe::class)
  val description: String,

  @JsonView(Recipe.Companion.DetailRecipe::class)
  val ingredients: List<Ingredient>,

  @JsonView(Recipe.Companion.DetailRecipe::class)
  val tags: List<String>,

  @JsonView(value =[Recipe.Companion.OverviewRecipe::class, Recipe.Companion.DetailRecipe::class])
  var createDate: Date?,

  @JsonView(value =[Recipe.Companion.OverviewRecipe::class, Recipe.Companion.DetailRecipe::class])
  var rating: Int,

  @JsonView(value =[Recipe.Companion.OverviewRecipe::class, Recipe.Companion.DetailRecipe::class])
  @Id val id: String?
) {

  companion object {
    class OverviewRecipe{}
    class DetailRecipe{}
  }
}

