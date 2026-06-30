package com.kata.berlinclock.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kata.berlinclock.domain.model.BerlinClockState

@Composable
fun BerlinClockRoute(
    viewModel: BerlinClockViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    BerlinClockScreen(
        onConvertClick =  viewModel ::convertTime,
        uiState = uiState
    )
}

@Composable
fun BerlinClockScreen(
    onConvertClick: (String) -> Unit,
    uiState: BerlinClockUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var timeInput by remember { mutableStateOf("12:56:01") }

        // Title
        Text(
            text = "Berlin Clock",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = timeInput,
            onValueChange = { timeInput = it },
            label = { Text("Enter time (HH:MM:SS)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Button(
            onClick = { onConvertClick.invoke(timeInput) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Convert to Berlin Clock")
        }

        when (uiState) {
            is BerlinClockUiState.Idle -> {
                Text(
                    text = "Enter a time to see the Berlin Clock",
                    modifier = Modifier.padding(16.dp)
                )
            }
            is BerlinClockUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is BerlinClockUiState.Success -> {
                BerlinClockDisplay(uiState.berlinClockState)
            }
            else -> {}
        }
    }
}

@Composable
fun BerlinClockDisplay(berlinClockState: BerlinClockState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Seconds Row
        ClockRow(
            label = "Seconds",
            value = berlinClockState.secondsRow,
            description = "Blink (Y=even, O=odd)"
        )
    }
}

@Composable
fun ClockRow(label: String, value: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, fontSize = 12.sp)
            Text(text = description, fontSize = 10.sp, color = Color.Gray)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(2f, fill = false)
        ) {
            value.forEach { char ->
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            when (char) {
                                'Y' -> Color.Yellow
                                else -> Color.Black
                            }
                        )
                        .border(1.dp, Color.DarkGray)
                )
            }
        }
    }
}