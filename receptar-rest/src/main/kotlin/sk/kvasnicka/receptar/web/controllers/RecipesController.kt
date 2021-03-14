package sk.kvasnicka.receptar.web.controllers

import com.fasterxml.jackson.annotation.JsonView
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sk.kvasnicka.receptar.data.model.Recipe
import sk.kvasnicka.receptar.services.RecipesService
import sk.kvasnicka.receptar.services.dto.TableResultDTO

@RestController
@RequestMapping("/api/recipes")
class RecipesController(
  val recipesService: RecipesService
) {
  private val log: Logger = LogManager.getLogger(RecipesController::class.java)

  @GetMapping(value = [""], produces = [MediaType.APPLICATION_PROBLEM_JSON_VALUE])
  @JsonView(value = [Recipe.Companion.OverviewRecipe::class])
  fun getAllRecipes(
    @RequestParam(name = "page") page: Int,
    @RequestParam(name = "pageSize", defaultValue = "20", required = false) pageSize: Int
  ): TableResultDTO<Recipe> {
    return recipesService.getRecipes(page, pageSize);
  }

  @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_PROBLEM_JSON_VALUE])
  @JsonView(value = [Recipe.Companion.DetailRecipe::class])
  fun getRecipe(
    @PathVariable("id") id: String
  ): ResponseEntity<Recipe> {
    val r = recipesService.findById(id)
    if (r.isPresent) {
      return ResponseEntity.ok(r.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(
    value = [""],
    produces = [MediaType.APPLICATION_PROBLEM_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
  )
  fun createRecipe(@RequestBody recipe: Recipe): Recipe {
    return recipesService.saveRecipe(recipe);
  }

  @PutMapping(
    value = [""],
    produces = [MediaType.APPLICATION_PROBLEM_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
  )
  fun updateRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
    if (recipe.id == null) {
      log.error("updating recipe with no ID")
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(recipesService.saveRecipe(recipe));
  }
}
