package com.example.proyectosaludo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.proyectosaludo.ui.theme.ProyectoSaludoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSaludoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Saludar()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Saludar() {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var counterAccept by rememberSaveable { mutableStateOf(0) }
    var counterCancel by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showDialog) {
            // Pantalla principal con el botón "Saludar" y el texto asociado
            Button(onClick = { showDialog = true }) {
                Text("Saludar")
            }
            Text(text = if (name.isNotEmpty()) "Hola, $name" else "")
            Text("A$counterAccept C$counterCancel")
        } else {
            Dialog(
                onDismissRequest = {
                    showDialog = false
                    counterCancel++
                },
                properties = DialogProperties(dismissOnBackPress = false),
                content = {
                    DialogContent(
                        name = name,
                        onNameChange = { newName -> name = newName },
                        onClearName = { name = "" },
                        onAccept = {
                            counterAccept++
                            showDialog = false
                        },
                        onCancel = {
                            showDialog = false
                            counterCancel++
                        }
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogContent(
    name: String,
    onNameChange: (String) -> Unit,
    onClearName: () -> Unit,
    onAccept: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text = "Configuración",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.End) // Para alinear a la derecha
        )
        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Introduce tu nombre") }
        )
        Spacer(modifier = Modifier.height(20.dp)) //Un espacio debajo del Texto a editar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onClearName) {
                Text("L")
            }
            Button(onClick = onAccept) {
                Text("A")
            }
            Button(onClick = onCancel) {
                Text("C")
            }
        }
    }
}