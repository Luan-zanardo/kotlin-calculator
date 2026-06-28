package com.example.kotlin_calculator

sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()
    object Decimal : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Percent : CalculatorAction()
    object ToggleSign : CalculatorAction()
    data class Operation(val operation: String) : CalculatorAction()
    object Calculate : CalculatorAction()
}
