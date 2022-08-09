package com.example.vectorcalc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
//fun HomeScreen()
fun HomeScreen(navHostController: NavHostController)
{

    // defining Vectors
    val vector1 = remember {
        mutableStateMapOf(
            "i" to "",
            "j" to "",
            "k" to ""
        )
    }

    val vector2 = remember {
        mutableStateMapOf(
            "i" to "",
            "j" to "",
            "k" to ""
        )
    }

    Scaffold {
        LazyColumn {

            // Title
            item {
                Text(
                    text = "Vector Calc",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .padding(top = 50.dp)
                )
            }

            // Vector 1 Label
            item {
                Text(
                    text = "Vector 1",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            item {
                // for i direction
                OutlinedTextField(
                    value = vector1["i"]!!,
                    onValueChange = {
                        vector1["i"] = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(43.dp),

                    label = { Text(text = "X Direction") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                // for j direction
                OutlinedTextField(
                    value = vector1["j"]!!,
                    onValueChange = {
                        vector1["j"] = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(43.dp),
                    label = { Text(text = "Y Direction") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                // for k direction
                OutlinedTextField(
                    value = vector1["k"]!!,
                    onValueChange = {
                        vector1["k"] = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(43.dp),
                    label = { Text(text = "Z Direction") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }

            // Vector 2 Label
            item {
                Text(
                    text = "Vector 2",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            item {
                // for i direction
                OutlinedTextField(
                    value = vector2["i"]!!,
                    onValueChange = {
                        vector2["i"] = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(43.dp),

                    label = { Text(text = "X Direction") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                // for j direction
                OutlinedTextField(
                    value = vector2["j"]!!,
                    onValueChange = {
                        vector2["j"] = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(43.dp),
                    label = { Text(text = "Y Direction") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                // for k direction
                OutlinedTextField(
                    value = vector2["k"]!!,
                    onValueChange = {
                        vector2["k"] = it
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(43.dp),
                    label = { Text(text = "Z Direction") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }

            item {
                OutlinedButton(
                    onClick = {

                        if (vector1["i"] == "") {
                            vector1["i"] = "1"
                        }
                        if (vector1["j"] == "") {
                            vector1["j"] = "1"
                        }
                        if (vector1["k"] == "") {
                            vector1["k"] = "1"
                        }
                        if (vector2["i"] == "") {
                            vector2["i"] = "1"
                        }
                        if (vector2["j"] == "") {
                            vector2["j"] = "1"
                        }
                        if (vector2["k"] == "") {
                            vector2["k"] = "1"
                        }

                        navHostController.navigate(
                            Screen.ReportScreen.withArgs(
                                vector1["i"]!!.toFloat(),
                                vector1["j"]!!.toFloat(),
                                vector1["k"]!!.toFloat(),
                                vector2["i"]!!.toFloat(),
                                vector2["j"]!!.toFloat(),
                                vector2["k"]!!.toFloat()
                            )
                        )

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Red
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 100.dp, bottom = 40.dp),
                ) {
                    Text(text = "Calculate", fontSize = 30.sp)
                }
            }
        }
    }
}


