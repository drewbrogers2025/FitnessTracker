package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnesstracker.data.Images
import com.example.fitnesstracker.ui.theme.*

data class Macro(val label: String, val amount: String, val pct: String, val fill: Float, val color: Color)

private val macros = listOf(
    Macro("Protein", "214g", "30%", 0.85f, PrimaryDim),
    Macro("Carbs", "356g", "50%", 0.60f, Secondary),
    Macro("Fats", "63g", "20%", 0.45f, OnSurfaceVariant),
)

@Composable
fun NutritionScreen(navController: NavController) {
    var hydrationLiters by remember { mutableFloatStateOf(3.5f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "NUTRITION ENGINE",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Black
                    ),
                    color = OnSurface
                )
            }
            AsyncImage(
                model = Images.nutritionUser,
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, Primary.copy(alpha = 0.2f), CircleShape)
            )
        }

        // Hero: Performance phase + calorie target
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                "PERFORMANCE PHASE",
                style = MaterialTheme.typography.labelLarge,
                color = Primary.copy(alpha = 0.6f)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Fuel your Elite Status.",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = OnSurface
            )
            Spacer(Modifier.height(16.dp))

            // Calorie card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerLow, RoundedCornerShape(16.dp))
                    .border(1.dp, OnSurface.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            "DAILY CALORIE TARGET",
                            style = MaterialTheme.typography.labelLarge,
                            color = OnSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                "2,850",
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = (-2).sp
                                ),
                                color = Secondary
                            )
                            Text(
                                "kcal",
                                style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic),
                                color = OnSurfaceVariant,
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                        }
                    }
                    Button(
                        onClick = {},
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryContainer,
                            contentColor = OnPrimaryContainer
                        )
                    ) {
                        Icon(Icons.Filled.Settings, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("SET TARGET", style = MaterialTheme.typography.labelLarge, color = OnPrimaryContainer)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Macros + Hydration row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Macronutrients
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(SurfaceContainerHigh, RoundedCornerShape(16.dp))
                    .border(1.dp, OnSurface.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("MACROS", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Black), color = OnSurface)
                    Icon(Icons.Filled.Settings, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(16.dp))
                }
                Spacer(Modifier.height(20.dp))
                macros.forEach { macro ->
                    MacroRow(macro)
                    Spacer(Modifier.height(16.dp))
                }
            }

            // Hydration
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(SurfaceContainerHigh, RoundedCornerShape(16.dp))
                    .border(1.dp, OnSurface.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("HYDRATION", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Black), color = OnSurface)
                    Box(
                        modifier = Modifier
                            .background(Primary.copy(alpha = 0.1f), RoundedCornerShape(100.dp))
                            .border(1.dp, Primary.copy(alpha = 0.2f), RoundedCornerShape(100.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("OPTIMAL", style = MaterialTheme.typography.labelSmall, color = Primary)
                    }
                }
                Spacer(Modifier.height(16.dp))
                // Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(1.dp, Outline.copy(alpha = 0.3f), CircleShape)
                            .clip(CircleShape)
                            .clickable { if (hydrationLiters > 0.5f) hydrationLiters -= 0.5f },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Remove, contentDescription = "Decrease", tint = OnSurface, modifier = Modifier.size(18.dp))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "%.1f".format(hydrationLiters),
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black, letterSpacing = (-2).sp),
                            color = OnSurface
                        )
                        Text("LITERS/DAY", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .border(1.dp, Outline.copy(alpha = 0.3f), CircleShape)
                            .clip(CircleShape)
                            .clickable { hydrationLiters += 0.5f },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Increase", tint = OnSurface, modifier = Modifier.size(18.dp))
                    }
                }
                Spacer(Modifier.weight(1f))
                // Water drops
                val filledDrops = (hydrationLiters / 0.75f).toInt().coerceAtMost(5)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(5) { i ->
                        Icon(
                            Icons.Filled.WaterDrop,
                            contentDescription = null,
                            tint = if (i < filledDrops) Primary else Primary.copy(alpha = 0.2f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Text("750ml per glass", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant, modifier = Modifier.padding(top = 4.dp))
            }
        }

        Spacer(Modifier.height(20.dp))

        // Micronutrient card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = Images.nutritionFood,
                contentDescription = "Micronutrients",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, SurfaceContainerLowest)
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Text(
                    "MICRONUTRIENT SYNC",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Black
                    ),
                    color = OnSurface
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Your magnesium levels are 12% below baseline. Focus on leafy greens today.",
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun MacroRow(macro: Macro) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(macro.color))
                Text(macro.label.uppercase(), style = MaterialTheme.typography.labelMedium, color = OnSurface)
            }
            Text(
                "${macro.amount} / ${macro.pct}",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = OnSurface
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(SurfaceVariantColor, RoundedCornerShape(100.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(macro.fill)
                    .fillMaxHeight()
                    .background(
                        Brush.horizontalGradient(colors = listOf(macro.color.copy(alpha = 0.7f), macro.color)),
                        RoundedCornerShape(100.dp)
                    )
            )
        }
    }
}
