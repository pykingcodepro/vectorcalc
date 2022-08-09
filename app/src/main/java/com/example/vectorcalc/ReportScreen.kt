package com.example.vectorcalc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Float.NaN
import kotlin.math.acos
import kotlin.math.sqrt

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    ReportScreen(vector1x = 1f, vector1y = 1f,vector1z = 1f, vector2x = 1f, vector2y = 1f, vector2z = 1f)
}

fun magnitudeOf(x:Float, y:Float, z:Float):Float{
    return sqrt((x*x) + (y*y) + (z*z))
}

fun getSign(x:Float):String{
    return if(x<0){
        ""
    } else {
        "+"
    }
}

@Composable
fun ReportScreen(vector1x:Float, vector1y:Float, vector1z:Float, vector2x:Float, vector2y:Float, vector2z:Float) {

    // All used calculations

    val magnitudeOfVector1:Float = String.format("%.2f", magnitudeOf(vector1x, vector1y, vector1z)).toFloat()
    val magnitudeOfVector2:Float = String.format("%.2f", magnitudeOf(vector2x, vector2y, vector2z)).toFloat()

    val negativeOfVector1 = mapOf(
        "i" to -vector1x,
        "j" to -vector1y,
        "k" to -vector1z,
        "magnitude" to String.format("%.2f", magnitudeOf(-vector1x, -vector1y, -vector1z)).toFloat()
    )
    val negativeOfVector2 = mapOf(
        "i" to -vector2x,
        "j" to -vector2y,
        "k" to -vector2z,
        "magnitude" to String.format("%.2f", magnitudeOf(-vector2x, -vector2y, -vector2z)).toFloat()
    )
    val additionOfVector = mapOf(
        "i" to vector1x + vector2x,
        "j" to vector1y + vector2y,
        "k" to vector1z + vector2z,
        "magnitude" to String.format("%.2f", magnitudeOf(
            vector1x + vector2x,
            vector1y + vector2y,
            vector1z + vector2z
        )).toFloat()
    )
    val subtractionOfVector1to2 = mapOf(
        "i" to vector1x - vector2x,
        "j" to vector1y - vector2y,
        "k" to vector1z - vector2z,
        "magnitude" to String.format("%.2f", magnitudeOf(
            vector1x - vector2x,
            vector1y - vector2y,
            vector1z - vector2z
        )).toFloat()
    )
    val subtractionOfVector2to1 = mapOf(
        "i" to vector2x - vector1x,
        "j" to vector2y - vector1y,
        "k" to vector2z - vector1z,
        "magnitude" to String.format("%.2f", magnitudeOf(
            vector2x - vector1x,
            vector2y - vector1y,
            vector2z - vector1z
        )).toFloat()
    )

    val dotProduct = ( (vector1x*vector2x) + (vector1y*vector2y) + (vector1z*vector2z) )

    val crossOfVector1to2 = mapOf(
        "i" to ((vector1y*vector2z) - (vector2y*vector1z)),
        "j" to -1*((vector1x*vector2z) - (vector2x*vector1z)),
        "k" to ((vector1x*vector2y) - (vector2x*vector1y)),
        "magnitude" to String.format("%.2f", magnitudeOf(
            (vector1y*vector2z) - (vector2y*vector1z),
            -1*((vector1x*vector2z) - (vector2x*vector1z)),
            (vector1x*vector2y) - (vector2x*vector1y)
        )).toFloat()
    )
    val crossOfVector2to1 = mapOf(
        "i" to ((vector2y*vector1z) - (vector1y*vector2z)),
        "j" to -1*((vector2x*vector1z) - (vector1x*vector2z)),
        "k" to ((vector2x*vector1y) - (vector1x*vector2y)),
        "magnitude" to String.format("%.2f", magnitudeOf(
            (vector2y*vector1z) - (vector1y*vector2z),
            -1*((vector2x*vector1z) - (vector1x*vector2z)),
            (vector2x*vector1y) - (vector1x*vector2y)
        )).toFloat()
    )

    var angleInRad = NaN

    if ((magnitudeOfVector1 != 0f) and (magnitudeOfVector2 != 0f)){

        val tempAngle = dotProduct/sqrt(magnitudeOf(vector1x, vector1y, vector1z)* magnitudeOf(vector2x, vector2y, vector2z))

        angleInRad = String.format(
            "%.2f",
            acos(
                when {
                    tempAngle > 1 -> { 1f }
                    tempAngle < -1 -> { -1f }
                    else -> { tempAngle }
                }
            )
        ).toFloat()
    }

    var angleInDeg = NaN
    if (!angleInRad.isNaN()){
        angleInDeg = (angleInRad/3.14f)*180
    }

    // Designing

    Scaffold {
        LazyColumn {
            item {
                Text(
                    text = "Result",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Showing all Vectors
            item {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 60.dp, end = 20.dp)
                ){
                    // Vector 1
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.vector_1_logo),
                            contentDescription = "Vector 1",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "X:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${vector1x}i ${getSign(vector1y)} ${vector1y}j ${getSign(vector1z)} ${vector1z}k (${magnitudeOfVector1})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }

                // Negative of Vector 2

                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 10.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.vector_2_logo),
                            contentDescription = "Vector 2",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "Y:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${vector2x}i ${getSign(vector2y)} ${vector2y}j ${getSign(vector2z)} ${vector2z}k (${magnitudeOfVector2})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }
            }

            // Negative of Vector 1

            item {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 60.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.negative_1_logo),
                            contentDescription = "Negative of Vector 1",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "- X:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${negativeOfVector1["i"]}i ${getSign(negativeOfVector1["j"]!!)} ${negativeOfVector1["j"]}j ${getSign(negativeOfVector1["k"]!!)} ${negativeOfVector1["k"]}k (${negativeOfVector1["magnitude"]})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }

                // Negative of Vector 2

                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 10.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.negative_2_logo),
                            contentDescription = "Negative of Vector 2",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "- Y:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${negativeOfVector2["i"]}i ${getSign(negativeOfVector2["j"]!!)} ${negativeOfVector2["j"]}j ${getSign(negativeOfVector2["k"]!!)} ${negativeOfVector2["k"]}k (${negativeOfVector2["magnitude"]})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }
            }

            // Vector 1 + Vector 2

            item {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 60.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.add_logo),
                            contentDescription = "Vector 1 + Vector 2",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "X + Y:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${additionOfVector["i"]}i ${getSign(additionOfVector["j"]!!)} ${additionOfVector["j"]}j ${getSign(additionOfVector["k"]!!)} ${additionOfVector["k"]}k (${additionOfVector["magnitude"]})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }
            }

            // Subtraction

            item {
                // Vector 1 - Vector 2
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 60.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.sub_1_logo),
                            contentDescription = "Vector 1 - Vector 2",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "X - Y:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${subtractionOfVector1to2["i"]}i ${getSign(subtractionOfVector1to2["j"]!!)} ${subtractionOfVector1to2["j"]}j ${getSign(subtractionOfVector1to2["k"]!!)} ${subtractionOfVector1to2["k"]}k (${subtractionOfVector1to2["magnitude"]})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }

                // Vector 2 - Vector 1
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 10.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.sub_2_logo),
                            contentDescription = "Vector 2 - Vector 1",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "Y - X:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${subtractionOfVector2to1["i"]}i ${getSign(subtractionOfVector2to1["j"]!!)} ${subtractionOfVector2to1["j"]}j ${getSign(subtractionOfVector2to1["k"]!!)} ${subtractionOfVector2to1["k"]}k (${subtractionOfVector2to1["magnitude"]})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }
            }

            // Dot Product -> Vector 1 ∙ Vector 2

            item {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 60.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.dot_logo),
                            contentDescription = "Vector 1 ∙ Vector 2",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "X ∙ Y:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "$dotProduct",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }
            }

            // Cross Product

            item {
                // Vector 1 × Vector 2
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 60.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.cross_1_logo),
                            contentDescription = "Vector 1 × Vector 2",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "X × Y:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${crossOfVector1to2["i"]}i ${getSign(crossOfVector1to2["j"]!!)} ${crossOfVector1to2["j"]}j ${getSign(crossOfVector1to2["k"]!!)} ${crossOfVector1to2["k"]}k (${crossOfVector1to2["magnitude"]})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }

                // Vector 2 × Vector 1
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 10.dp, end = 20.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.cross_2_logo),
                            contentDescription = "Vector 2 × Vector 1",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "Y × X:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${crossOfVector2to1["i"]}i ${getSign(crossOfVector2to1["j"]!!)} ${crossOfVector2to1["j"]}j ${getSign(crossOfVector2to1["k"]!!)} ${crossOfVector2to1["k"]}k (${crossOfVector2to1["magnitude"]})",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }
            }

            // Angle

            item {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp)
                        .padding(top = 60.dp, end = 20.dp)
                        .padding(bottom = 30.dp)
                ){

                    item {
                        Image(
                            painter = painterResource(id = R.drawable.angle_logo),
                            contentDescription = "Angle",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                        )
                        Text(
                            text = "θ:",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }

                    item {
                        Text(
                            text = "${angleInRad}rad || ${angleInDeg}°",
                            fontSize = 15.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(end = 10.dp),
                            textAlign = TextAlign.Right,
                        )
                    }
                }
            }

        }
    }
}

