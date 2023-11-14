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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

/**
 * Funcion que da comportamiento al programa
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Saludar() {
    //El diálogo que se va a mostrar una vez insertado en el dialogo (el nombre)
    var showDialog by rememberSaveable { mutableStateOf(false) }
    //El nombre que se crea por defecto vacío
    var name by rememberSaveable { mutableStateOf("") }
    //Los contadores que señalaran cuantas veces hemos pulsado Aceptar y Cancelar:
    var counterAccept by rememberSaveable { mutableStateOf(0) }
    var counterCancel by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showDialog) { //Mientras sea showDialog false se muestra el boton saludar
            // Pantalla principal con el botón "Saludar" y el texto asociado
            Button(onClick = { showDialog = true }) {
                Text("Saludar")
            }
            //El text se muestra solo si el nombre no está vació, si no no muestra nada
            Text(text = if (name.isNotEmpty()) "Hola, $name" else "")
            Text("A$counterAccept C$counterCancel")
        } else { //Si showDialog es false se muestra el AlertDialog
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = { Text("Configuración", textAlign = TextAlign.End,  modifier = Modifier.fillMaxWidth()) },
                text = {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text("Introduce tu nombre")
                        TextField(value = name, onValueChange = { name = it }) //Cambia el valor de nombre
                        Spacer(modifier = Modifier.height(20.dp)) //Un espacio debajo del Texto a editar
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = { name = "" }) { //Botón Limpiar
                                Text("L")
                            }
                            Button(onClick = { //Boton Aceptar
                                counterAccept++
                                showDialog = false
                            }) {
                                Text("A")
                            }
                            Button(onClick = { //Boton Cancelar
                                counterCancel++
                                showDialog = false
                            }) { Text("C")
                            }
                        }
                    }
                },
                confirmButton = {}
            )
        }
    }
}