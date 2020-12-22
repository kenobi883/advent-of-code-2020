package com.marcdenning.adventofcode.day21

import java.io.File

fun main(args: Array<String>) {
    val foods = File(args[0]).readLines().map { Food(getIngredients(it), getIdentifiedAllergens(it)) }
    val allIngredientsList = foods.flatMap { it.ingredients }
    val safeIngredients = getSafeIngredients(foods)
    val countOfInstancesOfSafeIngredients = allIngredientsList.count { safeIngredients.contains(it) }

    println("Number of instances of ingredients with no known allergens: $countOfInstancesOfSafeIngredients")
}

fun getSafeIngredients(foods: List<Food>): Set<String> {
    val allIngredientsList = foods.flatMap { it.ingredients }
    val allIngredientsSet = allIngredientsList.toSet()
    val allAllergensSet = foods.flatMap { it.allergens }.toSet()
    val ingredientsContainingAllergens = mutableSetOf<String>()

    for (allergen in allAllergensSet) {
        ingredientsContainingAllergens.addAll(getCommonIngredientsForAllergen(foods, allergen))
    }
    return allIngredientsSet.subtract(ingredientsContainingAllergens)
}

fun getCommonIngredientsForAllergen(foods: List<Food>, allergen: String): List<String> {
    return foods
        .filter { it.allergens.contains(allergen) }
        .map { it.ingredients }
        .reduce { acc, list -> acc.intersect(list).toList() }
}

fun getIngredients(description: String): List<String> =
    description.split('(')[0].split(' ').filter { it.isNotBlank() }.toList()

fun getIdentifiedAllergens(description: String) =
    description.split('(')[1].removePrefix("contains").split(',').map { it.removeSuffix(")").trim() }.toList()

data class Food(
    val ingredients: List<String>,
    val allergens: List<String>
)