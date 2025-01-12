package com.example.movieapp

class BadCodeExample {

    // Hardcoded credentials (security vulnerability)
    val username = "admin"
    val password = "password123"

    // Unused constant (code smell)
    companion object {
        const val UNUSED_CONSTANT = "Not used anywhere"
    }

    // Magic numbers (maintainability issue)
    fun calculateDiscount(price: Double): Double {
        return price * 0.15 // Discount is hardcoded
    }

    // Function with high cyclomatic complexity
    fun deeplyNestedLogic(input: Int): String {
        if (input > 0) {
            if (input < 10) {
                if (input % 2 == 0) {
                    return "Input is a small even number"
                } else {
                    return "Input is a small odd number"
                }
            } else {
                if (input % 2 == 0) {
                    return "Input is a large even number"
                } else {
                    return "Input is a large odd number"
                }
            }
        } else {
            return "Input is zero or negative"
        }
    }

    // Empty catch block (error handling issue)
    fun riskyOperationWrapper() {
        try {
            riskyOperation()
        } catch (e: Exception) {
            // No handling or logging
        }
    }

    private fun riskyOperation() {
        // Simulate risky operation
        throw Exception("Something went wrong")
    }
}