package sk.kvasnicka.receptar.data.dao

import org.springframework.data.repository.PagingAndSortingRepository
import sk.kvasnicka.receptar.data.model.Recipe

interface RecipesDao : PagingAndSortingRepository<Recipe, String>, RecipesDaoCustom {
}
