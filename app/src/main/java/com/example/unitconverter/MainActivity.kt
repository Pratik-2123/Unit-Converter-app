package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import org.w3c.dom.Text
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {

    var inputvalue by remember { mutableStateOf("")}
    var outputvalue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iexpanded by remember { mutableStateOf(false)}
    var oexpanded by remember { mutableStateOf(false)}
    val conversionfactor = remember { mutableStateOf(1.00)}
    val oConversionfactor = remember { mutableStateOf(1.00) }


    fun convertUnits() {
        // ?: is a elvis operator which means agar voh null hy tho usse 0.0 allocate kr do warna jo string me hy vahi karo
        val inputUnitDouble = inputvalue.toDoubleOrNull() ?: 0.0
        val result = (inputUnitDouble * conversionfactor.value * 100.0 / oConversionfactor.value).roundToInt() / 100.0
        outputvalue = result.toString()
    }


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
            Text(text = "UNIT CONVERTER", fontWeight = FontWeight.Bold, fontSize = 35.sp)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = inputvalue, onValueChange = {
                 inputvalue = it
                 convertUnits()
                //The amount when the text field get changes execute over here
            },
            label = { Text(text = "Enter number ")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
           Box {
               //Input Box
                Button(onClick = {
                    iexpanded = true
                } ) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
               DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded = false}) {
                   DropdownMenuItem(
                       text = { Text(text = "Centimeters") },
                       onClick = {
                           inputUnit = "Centimeters"
                           iexpanded = false
                           conversionfactor.value = 0.01
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = { Text(text = "Meters") },
                       onClick = {
                           inputUnit = "Meters"
                           iexpanded = false
                           conversionfactor.value = 1.00
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                           text = { Text(text = "Feet") },
                   onClick = {
                       inputUnit = "Feet"
                       iexpanded = false
                       conversionfactor.value = 0.3048
                       convertUnits()
                   }
                   )
                   DropdownMenuItem(
                       text = { Text(text = "Inch") },
                       onClick = {
                           inputUnit = "Inch"
                           iexpanded = false
                           conversionfactor.value = 0.001
                           convertUnits()
                       }
                   )
               }

           }
           Spacer(modifier = Modifier.width(16.dp))
           Box {
               //Output Box
                Button(onClick = {
                    oexpanded = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
               DropdownMenu(expanded = oexpanded, onDismissRequest = {oexpanded = false}) {
                   DropdownMenuItem(
                       text = { Text(text = "Centimeters") },
                       onClick = {
                           outputUnit = "Centimeters"
                           oexpanded = false
                           oConversionfactor.value = 0.01
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = { Text(text = "Meters") },
                       onClick = {
                           outputUnit = "Meters"
                           oexpanded = false
                           oConversionfactor.value = 1.00
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = { Text(text = "Feet") },
                       onClick = {
                           outputUnit = "Feet"
                           oexpanded = false
                           oConversionfactor.value = 0.3048
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = { Text(text = "Inch") },
                       onClick = {
                           outputUnit = "Inch"
                           oexpanded = false
                           oConversionfactor.value = 0.001
                           convertUnits()
                       }
                   )
               }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : $outputvalue in $inputUnit to $outputUnit", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
