package com.example.kotlin_calculator

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testInitialState() {
        assertEquals("0", viewModel.displayText.value)
        assertEquals("", viewModel.historyText.value)
    }

    @Test
    fun testEnterNumbers() {
        viewModel.onAction(CalculatorAction.Number(5))
        assertEquals("5", viewModel.displayText.value)

        viewModel.onAction(CalculatorAction.Number(9))
        assertEquals("59", viewModel.displayText.value)
    }

    @Test
    fun testEnterDecimal() {
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Decimal)
        viewModel.onAction(CalculatorAction.Number(2))
        assertEquals("5.2", viewModel.displayText.value)

        // Test that we can't add multiple decimals
        viewModel.onAction(CalculatorAction.Decimal)
        assertEquals("5.2", viewModel.displayText.value)
    }

    @Test
    fun testClear() {
        viewModel.onAction(CalculatorAction.Number(8))
        viewModel.onAction(CalculatorAction.Clear)
        assertEquals("0", viewModel.displayText.value)
        assertEquals("", viewModel.historyText.value)
    }

    @Test
    fun testDelete() {
        viewModel.onAction(CalculatorAction.Number(8))
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Delete)
        assertEquals("8", viewModel.displayText.value)

        viewModel.onAction(CalculatorAction.Delete)
        assertEquals("0", viewModel.displayText.value)
    }

    @Test
    fun testToggleSign() {
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.ToggleSign)
        assertEquals("-5", viewModel.displayText.value)

        viewModel.onAction(CalculatorAction.ToggleSign)
        assertEquals("5", viewModel.displayText.value)
    }

    @Test
    fun testPercent() {
        viewModel.onAction(CalculatorAction.Number(50))
        viewModel.onAction(CalculatorAction.Percent)
        assertEquals("0.5", viewModel.displayText.value)
    }

    @Test
    fun testAddition() {
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2)) // 12
        viewModel.onAction(CalculatorAction.Operation("+"))
        viewModel.onAction(CalculatorAction.Number(5)) // 5
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("17", viewModel.displayText.value)
        assertEquals("12 + 5 =", viewModel.historyText.value)
    }

    @Test
    fun testSubtraction() {
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(0)) // 20
        viewModel.onAction(CalculatorAction.Operation("-"))
        viewModel.onAction(CalculatorAction.Number(8)) // 8
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("12", viewModel.displayText.value)
        assertEquals("20 - 8 =", viewModel.historyText.value)
    }

    @Test
    fun testMultiplication() {
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.Operation("×"))
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("20", viewModel.displayText.value)
        assertEquals("4 × 5 =", viewModel.historyText.value)
    }

    @Test
    fun testDivision() {
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(0)) // 20
        viewModel.onAction(CalculatorAction.Operation("÷"))
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("4", viewModel.displayText.value)
        assertEquals("20 ÷ 5 =", viewModel.historyText.value)
    }

    @Test
    fun testDivisionByZero() {
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Operation("÷"))
        viewModel.onAction(CalculatorAction.Number(0))
        viewModel.onAction(CalculatorAction.Calculate)
        assertEquals("Error", viewModel.displayText.value)
    }
}
