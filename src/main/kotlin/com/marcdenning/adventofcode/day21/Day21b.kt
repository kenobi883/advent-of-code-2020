package com.marcdenning.adventofcode.day21

import java.io.File

fun main(args: Array<String>) {
    val foods = File(args[0]).readLines().map { Food(getIngredients(it), getIdentifiedAllergens(it)) }

    println(getDangerousIngredientsList(foods).joinToString(","))
}

fun getDangerousIngredientsList(foods: List<Food>): List<String> {
    val allAllergensSet = foods.flatMap { it.allergens }.toSet()
    val mapOfAllergenToIngredients = mutableMapOf<String, MutableSet<String>>()

    for (allergen in allAllergensSet) {
        mapOfAllergenToIngredients[allergen] = getCommonIngredientsForAllergen(foods, allergen).toMutableSet()
    }
    for (entry in mapOfAllergenToIngredients) {
        if (entry.value.size == 1) {
            val ingredient = entry.value.last()

            mapOfAllergenToIngredients.filter { it.key != entry.key }
                .forEach { (allergen, ingredientSet) -> ingredientSet.remove(ingredient) }
        }
    }
    return mapOfAllergenToIngredients.keys.sorted().map { mapOfAllergenToIngredients[it]!!.last() }
}
