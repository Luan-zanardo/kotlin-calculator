package com.example.kotlin_calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    val displayText = viewModel.displayText.value
    val historyText = viewModel.historyText.value

    val isDark = isSystemInDarkTheme()

    // Premium Color Palette
    val backgroundColor = if (isDark) Color(0xFF17171C) else Color(0xFFF1F2F3)
    val historyColor = if (isDark) Color(0xFF8E8E93) else Color(0xFF7A7A80)
    val displayColor = if (isDark) Color.White else Color.Black

    val digitBg = if (isDark) Color(0xFF2E2F38) else Color(0xFFFFFFFF)
    val digitFg = if (isDark) Color.White else Color.Black

    val opBg = Color(0xFFFF9F0A)
    val opFg = Color.White

    val helperBg = if (isDark) Color(0xFF4E505F) else Color(0xFFD4D4D2)
    val helperFg = if (isDark) Color.White else Color.Black

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Display Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                // History text (e.g. 12 + 4 =)
                if (historyText.isNotEmpty()) {
                    Text(
                        text = historyText,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Light,
                            color = historyColor,
                            textAlign = TextAlign.End
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                    )
                }

                // Main display text (e.g. 16)
                // Dynamically adjust text size if the number is very long
                val fontSize = when {
                    displayText.length > 10 -> 40.sp
                    displayText.length > 7 -> 56.sp
                    else -> 72.sp
                }

                Text(
                    text = displayText,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        color = displayColor,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Keyboard Section (Grid)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Row 1: AC, Del, %, /
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CalculatorButton(
                        text = "AC",
                        containerColor = helperBg,
                        contentColor = helperFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Clear) }
                    )
                    CalculatorButton(
                        text = "⌫",
                        containerColor = helperBg,
                        contentColor = helperFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Delete) }
                    )
                    CalculatorButton(
                        text = "%",
                        containerColor = helperBg,
                        contentColor = helperFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Percent) }
                    )
                    CalculatorButton(
                        text = "÷",
                        containerColor = opBg,
                        contentColor = opFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Operation("÷")) }
                    )
                }

                // Row 2: 7, 8, 9, x
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CalculatorButton(
                        text = "7",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(7)) }
                    )
                    CalculatorButton(
                        text = "8",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(8)) }
                    )
                    CalculatorButton(
                        text = "9",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(9)) }
                    )
                    CalculatorButton(
                        text = "×",
                        containerColor = opBg,
                        contentColor = opFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Operation("×")) }
                    )
                }

                // Row 3: 4, 5, 6, -
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CalculatorButton(
                        text = "4",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(4)) }
                    )
                    CalculatorButton(
                        text = "5",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(5)) }
                    )
                    CalculatorButton(
                        text = "6",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(6)) }
                    )
                    CalculatorButton(
                        text = "−",
                        containerColor = opBg,
                        contentColor = opFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Operation("-")) }
                    )
                }

                // Row 4: 1, 2, 3, +
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CalculatorButton(
                        text = "1",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(1)) }
                    )
                    CalculatorButton(
                        text = "2",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(2)) }
                    )
                    CalculatorButton(
                        text = "3",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(3)) }
                    )
                    CalculatorButton(
                        text = "+",
                        containerColor = opBg,
                        contentColor = opFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Operation("+")) }
                    )
                }

                // Row 5: +/-, 0, ., =
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CalculatorButton(
                        text = "±",
                        containerColor = helperBg,
                        contentColor = helperFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.ToggleSign) }
                    )
                    CalculatorButton(
                        text = "0",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Number(0)) }
                    )
                    CalculatorButton(
                        text = ".",
                        containerColor = digitBg,
                        contentColor = digitFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Decimal) }
                    )
                    CalculatorButton(
                        text = "=",
                        containerColor = opBg,
                        contentColor = opFg,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onAction(CalculatorAction.Calculate) }
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(containerColor)
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            }
    ) {
        Text(
            text = text,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            color = contentColor,
            textAlign = TextAlign.Center
        )
    }
}
