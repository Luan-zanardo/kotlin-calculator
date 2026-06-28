package com.example.kotlin_calculator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _displayText = mutableStateOf("0")
    val displayText: State<String> = _displayText

    private val _historyText = mutableStateOf("")
    val historyText: State<String> = _historyText

    private var operand1: Double? = null
    private var operator: String? = null
    private var isEquationFinished = false

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> clear()
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Percent -> percent()
            is CalculatorAction.ToggleSign -> toggleSign()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun enterNumber(number: Int) {
        if (isEquationFinished) {
            _displayText.value = number.toString()
            _historyText.value = ""
            isEquationFinished = false
            return
        }

        if (_displayText.value == "0") {
            _displayText.value = number.toString()
        } else {
            // Prevent entering excessively long numbers that overflow screen
            if (_displayText.value.length < 15) {
                _displayText.value += number.toString()
            }
        }
    }

    private fun enterDecimal() {
        if (isEquationFinished) {
            _displayText.value = "0."
            _historyText.value = ""
            isEquationFinished = false
            return
        }

        if (!_displayText.value.contains(".")) {
            _displayText.value += "."
        }
    }

    private fun clear() {
        _displayText.value = "0"
        _historyText.value = ""
        operand1 = null
        operator = null
        isEquationFinished = false
    }

    private fun delete() {
        if (isEquationFinished) {
            _historyText.value = ""
            isEquationFinished = false
            return
        }

        if (_displayText.value.length > 1) {
            _displayText.value = _displayText.value.dropLast(1)
            // Handle deleting to negative sign alone
            if (_displayText.value == "-") {
                _displayText.value = "0"
            }
        } else {
            _displayText.value = "0"
        }
    }

    private fun percent() {
        val currentValue = _displayText.value.toDoubleOrNull() ?: return
        val result = currentValue / 100.0
        _displayText.value = formatNumber(result)
    }

    private fun toggleSign() {
        val currentValue = _displayText.value.toDoubleOrNull() ?: return
        if (currentValue != 0.0) {
            if (_displayText.value.startsWith("-")) {
                _displayText.value = _displayText.value.substring(1)
            } else {
                _displayText.value = "-" + _displayText.value
            }
        }
    }

    private fun enterOperation(op: String) {
        val currentValue = _displayText.value.toDoubleOrNull() ?: return

        if (operand1 != null && operator != null && !isEquationFinished) {
            // Perform intermediate calculation
            val result = performCalculation(operand1!!, currentValue, operator!!)
            if (result == null) {
                _displayText.value = "Error"
                operand1 = null
                operator = null
                return
            }
            operand1 = result
            _displayText.value = "0" // Clear for the next input, but show intermediate in history
        } else {
            operand1 = currentValue
            _displayText.value = "0"
        }

        operator = op
        _historyText.value = "${formatNumber(operand1!!)} $op"
        isEquationFinished = false
    }

    private fun calculate() {
        val currentValue = _displayText.value.toDoubleOrNull() ?: return
        val currentOp = operator ?: return
        val currentOp1 = operand1 ?: return

        val result = performCalculation(currentOp1, currentValue, currentOp)
        if (result == null) {
            _displayText.value = "Error"
        } else {
            _historyText.value = "${formatNumber(currentOp1)} $currentOp ${formatNumber(currentValue)} ="
            _displayText.value = formatNumber(result)
        }

        operand1 = null
        operator = null
        isEquationFinished = true
    }

    private fun performCalculation(num1: Double, num2: Double, op: String): Double? {
        return when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            "÷" -> {
                if (num2 == 0.0) null else num1 / num2
            }
            else -> null
        }
    }

    private fun formatNumber(value: Double): String {
        if (value.isNaN() || value.isInfinite()) return "Error"
        val intValue = value.toLong()
        return if (value == intValue.toDouble()) {
            intValue.toString()
        } else {
            // Format double nicely
            val str = String.format(java.util.Locale.US, "%.10f", value)
            val trimmed = str.replace(Regex("0+$"), "")
            if (trimmed.endsWith(".")) {
                trimmed.dropLast(1)
            } else {
                trimmed
            }
        }
    }
}
