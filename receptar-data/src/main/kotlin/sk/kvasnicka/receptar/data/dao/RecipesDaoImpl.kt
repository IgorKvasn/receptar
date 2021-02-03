package sk.kvasnicka.receptar.data.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import sk.kvasnicka.receptar.data.model.Recipe

class RecipesDaoImpl : RecipesDaoCustom {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate;

    override fun findByTags(tags: List<String>): List<Recipe> {
        return mongoTemplate.find(Query.query(Criteria.where("tags").`in`(tags)), Recipe::class.java)
    }
}
