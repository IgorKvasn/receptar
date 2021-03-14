package sk.kvasnicka.receptar.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import sk.kvasnicka.receptar.data.dao.RecipesDao
import sk.kvasnicka.receptar.data.model.Recipe
import sk.kvasnicka.receptar.services.dto.TableResultDTO
import java.util.*


@Service
class RecipesService {

  @Autowired
  lateinit var recipesDao: RecipesDao;


  fun getRecipes(page: Int, pageSize: Int): TableResultDTO<Recipe> {
    val page = recipesDao.findAll(PageRequest.of(page, pageSize, Sort.Direction.ASC, "createDate"))
    return TableResultDTO(
      page.toList(),
      TableResultDTO.TableMetadata(page.hasNext(), page.hasPrevious(), page.totalPages, page.totalElements)
    );
  }

  fun saveRecipe(recipe: Recipe): Recipe {
    if (recipe.id == null) {
      recipe.createDate = Date();
    }
    return recipesDao.save(recipe);
  }

  fun findById(id: String): Optional<Recipe> {
    return recipesDao.findById(id);
  }

  /*  fun getHello(): String {
        val ing1 = Ingredient("jablka", 5, QuantityUnit.PIECES);
        val ing2 = Ingredient("muka", 100, QuantityUnit.MG);
        val r1 = Recipe("recept"+Math.round(Math.random()*100), "description1", Arrays.asList(ing1, ing2), Arrays.asList("tag1", "tag2"));

        recipesDao.save(r1)

        r1.description = "updated desc"
        recipesDao.save(r1)

        val findAll = recipesDao.findAll();
        val recepies = recipesDao.findByTags(Arrays.asList("tag1"));
        return """Ahoj Martinka <br> size: ${recipesDao.count()} <br><br> ${findAll.map({ recipe -> recipe.toString() + "<br>" })}"""
    }*/
}
