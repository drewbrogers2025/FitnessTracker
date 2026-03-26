package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnesstracker.data.Images
import com.example.fitnesstracker.ui.theme.*

private val weeklyData = listOf(
    Pair("MON", 0.9f), Pair("TUE", 0.6f), Pair("WED", 1.0f),
    Pair("THU", 0.4f), Pair("FRI", 0.85f), Pair("SAT", 0.7f), Pair("SUN", 0.0f)
)

@Composable
fun ProgressScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
    ) {
        // Header
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Text(
                "PROGRESS INSIGHTS",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
                color = OnSurface
            )
            Text(
                "October 2024",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant
            )
        }

        // Profile banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = Images.progressUser,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Background.copy(alpha = 0.85f), Color.Transparent)
                        )
                    )
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "ALEX RIVERA",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                        color = OnSurface
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Primary))
                        Text("ELITE STATUS", style = MaterialTheme.typography.labelLarge, color = Primary)
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Key stats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf(
                Triple("428", "Workouts", Primary),
                Triple("18.2K", "Minutes", Secondary),
                Triple("12", "PRs", Tertiary),
            ).forEach { (value, label, color) ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(SurfaceContainerHigh, RoundedCornerShape(14.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        value,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
                        color = color
                    )
                    Text(label, style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Weekly activity chart
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(SurfaceContainerHigh, RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("WEEKLY ACTIVITY", style = MaterialTheme.typography.labelLarge, color = OnSurface)
                Text("This Week", style = MaterialTheme.typography.bodySmall, color = OnSurfaceVariant)
            }
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                weeklyData.forEachIndexed { index, (day, fill) ->
                    val isToday = index == 4
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .width(28.dp)
                                .fillMaxHeight(fill.coerceAtLeast(0.05f))
                                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                .background(
                                    brush = when {
                                        fill == 0f -> Brush.verticalGradient(listOf(SurfaceContainerHighest, SurfaceContainerHighest))
                                        isToday -> Brush.verticalGradient(listOf(NeonGreen, PrimaryContainer))
                                        fill > 0.7f -> Brush.verticalGradient(listOf(Primary.copy(alpha = 0.8f), PrimaryContainer.copy(alpha = 0.5f)))
                                        else -> Brush.verticalGradient(listOf(SurfaceContainerHighest, SurfaceContainerHighest))
                                    }
                                )
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            day,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp),
                            color = if (isToday) NeonGreen else OnSurfaceVariant
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Monthly metrics
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text("MONTHLY TRENDS", style = MaterialTheme.typography.labelLarge, color = OnSurfaceVariant)
            Spacer(Modifier.height(12.dp))
            val trends = listOf(
                Triple("Running", "124km", "+8%"),
                Triple("Strength Sessions", "28 sessions", "+12%"),
                Triple("Calories Burned", "45,230 kcal", "+5%"),
                Triple("Avg Workout Length", "47 min", "+3%"),
            )
            trends.forEach { (label, value, change) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(label, style = MaterialTheme.typography.bodyMedium, color = OnSurface)
                        Text(value, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = OnSurface)
                    }
                    Box(
                        modifier = Modifier
                            .background(Primary.copy(alpha = 0.1f), RoundedCornerShape(100.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            change,
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                    }
                }
                HorizontalDivider(color = OutlineVariant.copy(alpha = 0.3f), thickness = 0.5.dp)
            }
        }

        Spacer(Modifier.height(20.dp))

        // Personal Records
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(SurfaceContainerHigh, RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Text("PERSONAL BESTS", style = MaterialTheme.typography.labelLarge, color = OnSurface)
            Spacer(Modifier.height(16.dp))
            val prs = listOf(
                Triple("5K Run", "24:12", "Oct 18"),
                Triple("Bench Press", "120 KG", "Oct 22"),
                Triple("Deadlift", "180 KG", "Oct 15"),
                Triple("Pull-Ups", "18 reps", "Oct 10"),
            )
            prs.forEach { (lift, value, date) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            Icons.Filled.EmojiEvents,
                            contentDescription = null,
                            tint = TertiaryContainer,
                            modifier = Modifier.size(20.dp)
                        )
                        Column {
                            Text(lift, style = MaterialTheme.typography.titleSmall, color = OnSurface)
                            Text(date, style = MaterialTheme.typography.bodySmall, color = OnSurfaceVariant)
                        }
                    }
                    Text(
                        value,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Black),
                        color = PrimaryFixed
                    )
                }
            }
        }
    }
}
