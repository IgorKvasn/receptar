package sk.kvasnicka.receptar.data.dao

import sk.kvasnicka.receptar.data.model.Recipe


interface RecipesDaoCustom {

    fun findByTags(tags: List<String>): List<Recipe>;
}
