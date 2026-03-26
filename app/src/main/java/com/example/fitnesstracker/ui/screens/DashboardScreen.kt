package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnesstracker.data.Images
import com.example.fitnesstracker.ui.theme.*

private val weekData = listOf(
    Pair("M", 0.8f), Pair("T", 0.6f), Pair("W", 1.0f),
    Pair("T", 0.4f), Pair("F", 0.9f), Pair("S", 0.7f), Pair("S", 0.3f)
)

@Composable
fun DashboardScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { DashboardTopBar() }
            item { Spacer(Modifier.height(8.dp)) }
            item { GreetingSection() }
            item { Spacer(Modifier.height(20.dp)) }
            item { TodayStatsRow() }
            item { Spacer(Modifier.height(20.dp)) }
            item { TodayWorkoutCard(navController) }
            item { Spacer(Modifier.height(20.dp)) }
            item { WeeklyActivityChart() }
            item { Spacer(Modifier.height(20.dp)) }
            item { RecentActivitySection() }
        }
    }
}

@Composable
private fun DashboardTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background.copy(alpha = 0.9f))
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            AsyncImage(
                model = Images.userAvatarDashboard,
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, PrimaryContainer, CircleShape)
            )
            Text(
                text = "KINETIC",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-1).sp
                ),
                color = NeonGreen
            )
        }
        IconButton(onClick = {}) {
            Icon(Icons.Filled.NotificationsNone, contentDescription = "Notifications", tint = OnSurface)
        }
    }
}

@Composable
private fun GreetingSection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            text = "GOOD MORNING",
            style = MaterialTheme.typography.labelLarge,
            color = Primary.copy(alpha = 0.6f)
        )
        Text(
            text = "Alex Rivera",
            style = MaterialTheme.typography.headlineMedium,
            color = OnSurface
        )
        Spacer(Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Primary))
            Text(
                text = "14-day streak • You're crushing it",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant
            )
        }
    }
}

@Composable
private fun TodayStatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(modifier = Modifier.weight(1f), label = "CALORIES", value = "2,450", unit = "kcal", color = Secondary)
        StatCard(modifier = Modifier.weight(1f), label = "ACTIVE", value = "127", unit = "min", color = Primary)
        StatCard(modifier = Modifier.weight(1f), label = "STREAK", value = "14", unit = "days", color = Tertiary)
    }
}

@Composable
private fun StatCard(modifier: Modifier, label: String, value: String, unit: String, color: Color) {
    Column(
        modifier = modifier
            .background(SurfaceContainerHigh, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
        Spacer(Modifier.height(6.dp))
        Text(
            value,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
            color = color
        )
        Text(unit, style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
    }
}

@Composable
private fun TodayWorkoutCard(navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            "TODAY'S WORKOUT",
            style = MaterialTheme.typography.labelLarge,
            color = OnSurfaceVariant
        )
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { navController.navigate("workout_detail/1") }
        ) {
            AsyncImage(
                model = Images.workoutHero,
                contentDescription = "Today's Workout",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Background.copy(alpha = 0.9f)),
                            startY = 0f, endY = Float.POSITIVE_INFINITY
                        )
                    )
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart).padding(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(PrimaryContainer, RoundedCornerShape(100.dp))
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Text("POWER PHASE", style = MaterialTheme.typography.labelSmall, color = OnPrimaryContainer)
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    "High-Intensity Power",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                    color = OnSurface
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("45 MIN", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                    Text("•", color = OutlineVariant)
                    Text("8 EXERCISES", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                    Text("•", color = OutlineVariant)
                    Text("HIGH", style = MaterialTheme.typography.labelMedium, color = Secondary)
                }
            }
            // Start button
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(NeonGreen)
                    .clickable { navController.navigate("workout_detail/1") },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "Start", tint = Color(0xFF0D0F0C), modifier = Modifier.size(28.dp))
            }
        }
    }
}

@Composable
private fun WeeklyActivityChart() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("WEEKLY ACTIVITY", style = MaterialTheme.typography.labelLarge, color = OnSurfaceVariant)
            Text("5 of 7 days", style = MaterialTheme.typography.bodySmall, color = Primary)
        }
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainerHigh, RoundedCornerShape(16.dp))
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            weekData.forEachIndexed { index, (day, fill) ->
                val isToday = index == 4
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .width(28.dp)
                            .height((60 * fill).dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (isToday) NeonGreen
                                else if (fill > 0.5f) PrimaryContainer.copy(alpha = 0.6f)
                                else SurfaceContainerHighest
                            )
                    )
                    Text(
                        day,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isToday) NeonGreen else OnSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentActivitySection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("RECENT ACTIVITY", style = MaterialTheme.typography.labelLarge, color = OnSurfaceVariant)
            Text("View All", style = MaterialTheme.typography.bodySmall, color = Primary)
        }
        Spacer(Modifier.height(12.dp))
        val activities = listOf(
            Triple("Morning Trail Run", "Today • 5.2km", "24:12"),
            Triple("Full Body Burn", "Yesterday • HIIT", "45:00"),
            Triple("Lower Body Strength", "Oct 22 • Weights", "52:15"),
        )
        activities.forEach { (title, subtitle, duration) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(SurfaceContainerHigh, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⚡", style = MaterialTheme.typography.titleSmall)
                    }
                    Column {
                        Text(title, style = MaterialTheme.typography.titleSmall, color = OnSurface)
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = OnSurfaceVariant)
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(duration, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = OnSurface)
                }
            }
            HorizontalDivider(color = OutlineVariant.copy(alpha = 0.3f), thickness = 0.5.dp)
        }
    }
}
