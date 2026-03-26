package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Stop
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnesstracker.data.Images
import com.example.fitnesstracker.ui.theme.*

private data class ZoneData(val label: String, val pct: Float, val color: Color)

private val heartRateZones = listOf(
    ZoneData("Zone 5 Peak", 0.12f, ErrorColor),
    ZoneData("Zone 4 Threshold", 0.64f, Secondary),
    ZoneData("Zone 3 Aerobic", 0.20f, Tertiary),
    ZoneData("Zone 2 Easy", 0.04f, PrimaryDim),
)

@Composable
fun ActivityTrackingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 120.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    model = Images.userAvatarWoman,
                    contentDescription = "User",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(1.dp, OutlineVariant.copy(alpha = 0.3f), CircleShape)
                )
                Text(
                    "KINETIC",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-0.5).sp
                    ),
                    color = NeonGreen
                )
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.NotificationsNone, contentDescription = null, tint = OnSurface)
            }
        }

        // Live indicator + timer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Secondary)
                )
                Text(
                    "LIVE TRACKING  •  AFTERNOON RUN",
                    style = MaterialTheme.typography.labelMedium,
                    color = OnSurfaceVariant
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "42:18",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-3).sp
                ),
                color = OnSurface
            )
            Text(
                "TIME ELAPSED",
                style = MaterialTheme.typography.labelLarge,
                color = Primary
            )
        }

        Spacer(Modifier.height(16.dp))

        // Primary stats grid
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LiveStatCard(modifier = Modifier.weight(1f), label = "DISTANCE (KM)", value = "8.42", color = Primary)
            LiveStatCard(modifier = Modifier.weight(1f), label = "AVG PACE", value = "5'02\"", color = OnSurface)
        }
        Spacer(Modifier.height(12.dp))
        Box(modifier = Modifier
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
                Column {
                    Text("HEART RATE", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
                    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            "164",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black),
                            color = Secondary
                        )
                        Text("BPM", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant, modifier = Modifier.padding(bottom = 6.dp))
                    }
                }
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = Secondary, modifier = Modifier.size(28.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // Map + Zones row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(240.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Map
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = Images.mapRunning,
                    contentDescription = "Route Map",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Background.copy(alpha = 0.6f))
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                        .background(SurfaceContainerHighest.copy(alpha = 0.8f), RoundedCornerShape(100.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Filled.LocationOn, contentDescription = null, tint = Primary, modifier = Modifier.size(12.dp))
                    Text("Central Park North Loop", style = MaterialTheme.typography.labelSmall, color = OnSurface)
                }
            }

            // HR Zones
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(SurfaceContainerLow, RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                Text(
                    "INTENSITY ZONES",
                    style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                    color = OnSurface
                )
                Spacer(Modifier.height(12.dp))
                heartRateZones.forEach { zone ->
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(zone.label.take(6), style = MaterialTheme.typography.labelSmall, color = zone.color, fontSize = 7.sp)
                            Text("${(zone.pct * 100).toInt()}%", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant, fontSize = 7.sp)
                        }
                        Spacer(Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(SurfaceVariantColor, RoundedCornerShape(100.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(zone.pct)
                                    .fillMaxHeight()
                                    .background(zone.color, RoundedCornerShape(100.dp))
                            )
                        }
                    }
                }
                Spacer(Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(Icons.Filled.Bolt, contentDescription = null, tint = Secondary, modifier = Modifier.size(14.dp))
                    Column {
                        Text("CALORIES", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant, fontSize = 7.sp)
                        Text("642 kcal", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = OnSurface, fontSize = 10.sp)
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Secondary metrics
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf(
                Triple("CADENCE", "178", "SPM"),
                Triple("ELEVATION", "+124", "M"),
                Triple("HUMIDITY", "42", "%"),
                Triple("EFFICIENCY", "High", ""),
            ).forEach { (label, value, unit) ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(SurfaceContainerHighest, RoundedCornerShape(12.dp))
                        .padding(10.dp)
                ) {
                    Text(label, style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant, fontSize = 7.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        buildString {
                            append(value)
                            if (unit.isNotEmpty()) append(" ")
                        },
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = OnSurface
                    )
                    if (unit.isNotEmpty()) Text(unit, style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant, fontSize = 7.sp)
                }
            }
        }
    }

    // Floating controls
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 24.dp)
                .background(SurfaceContainerHigh.copy(alpha = 0.9f), CircleShape)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Pause
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(SurfaceBright),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Pause, contentDescription = "Pause", tint = OnSurface, modifier = Modifier.size(28.dp))
            }
            // GPS indicator
            Column(
                modifier = Modifier.width(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                    repeat(3) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Primary))
                    }
                }
                Spacer(Modifier.height(2.dp))
                Text("GPS FIX", style = MaterialTheme.typography.labelSmall, color = Primary, fontSize = 7.sp)
            }
            // Stop
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(ErrorContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Stop, contentDescription = "Stop", tint = Color(0xFFFFD2C8), modifier = Modifier.size(28.dp))
            }
        }
    }
}

@Composable
private fun LiveStatCard(modifier: Modifier, label: String, value: String, color: Color) {
    Column(
        modifier = modifier
            .background(SurfaceContainerHigh, RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
        Spacer(Modifier.height(8.dp))
        Text(
            value,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black),
            color = color
        )
    }
}
