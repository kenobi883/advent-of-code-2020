package com.marcdenning.adventofcode.day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day21aTests {

    private val foods = listOf(
        Food(
            listOf("mxmxvkd", "kfcds", "sqjhc", "nhms"),
            listOf("dairy", "fish")
        ),
        Food(
            listOf("trh", "fvjkl", "sbzzf" ,"mxmxvkd"),
            listOf("dairy")
        ),
        Food(
            listOf("sqjhc", "fvjkl"),
            listOf("soy")
        ),
        Food(
            listOf("sqjhc", "mxmxvkd", "sbzzf"),
            listOf("fish")
        )
    )

    @Test
    fun testGetSafeIngredients() {
        val safeIngredients = getSafeIngredients(foods)

        assertTrue(safeIngredients.containsAll(listOf("sbzzf", "kfcds", "trh", "nhms")))
    }

    @Test
    fun testGetCommonIngredientsForAllergen() {
        assertTrue(getCommonIngredientsForAllergen(foods, "dairy").contains("mxmxvkd"))
        assertTrue(getCommonIngredientsForAllergen(foods, "fish").contains("sqjhc"))
        assertTrue(getCommonIngredientsForAllergen(foods, "soy").contains("fvjkl"))
    }

    @Test
    fun testGetIngredients() {
        val description = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)"
        val ingredients = getIngredients(description)

        assertEquals(4, ingredients.size)
        assertTrue(ingredients.containsAll(listOf("mxmxvkd", "kfcds", "sqjhc", "nhms")))
    }

    @Test
    fun testGetIdentifiedAllergens() {
        val description = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)"
        val allergens = getIdentifiedAllergens(description)

        assertEquals(2, allergens.size)
        assertTrue(allergens.containsAll(listOf("dairy", "fish")))
    }
}
