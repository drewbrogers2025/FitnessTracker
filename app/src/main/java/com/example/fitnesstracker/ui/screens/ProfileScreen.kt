package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnesstracker.data.Images
import com.example.fitnesstracker.ui.theme.*

@Composable
fun ProfileScreen(navController: NavController) {
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
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.background(SurfaceContainerHigh, CircleShape)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = NeonGreen)
            }
            Text(
                "PROFILE",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = OnSurface
            )
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = OnSurface)
            }
        }

        // Avatar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(140.dp)) {
                // Glow effect
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(colors = listOf(PrimaryContainer.copy(alpha = 0.3f), Secondary.copy(alpha = 0.1f), Color.Transparent))
                        )
                        .blur(16.dp)
                )
                AsyncImage(
                    model = Images.userProfileMan,
                    contentDescription = "Alex Rivera",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .border(4.dp, SurfaceContainerHigh, CircleShape)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(PrimaryContainer)
                        .border(2.dp, Background, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Verified, contentDescription = null, tint = OnPrimaryContainer, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(Modifier.height(12.dp))
            Text(
                "ALEX RIVERA",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.5).sp
                ),
                color = OnSurface
            )
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .background(SurfaceContainerHigh, RoundedCornerShape(100.dp))
                    .border(1.dp, OutlineVariant.copy(alpha = 0.2f), RoundedCornerShape(100.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Primary))
                Text(
                    "ELITE STATUS",
                    style = MaterialTheme.typography.labelLarge,
                    color = Primary
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // Stats row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf(
                Triple("428", "Workouts", Primary),
                Triple("18.2K", "Mins", Secondary),
                Triple("12", "Bests", Tertiary),
            ).forEach { (value, label, color) ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(SurfaceContainerLow, RoundedCornerShape(14.dp))
                        .border(bottomBorder = true)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        value,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                        color = OnSurface
                    )
                    Text(label.uppercase(), style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Recent Activity
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
                ActivityItem(Icons.Filled.DirectionsRun, Primary, "Morning Trail Run", "Today • 5.2km", "24:12", "-2% Pace", Primary),
                ActivityItem(Icons.Filled.Bolt, Secondary, "Full Body Burn", "Yesterday • HIIT", "45:00", "High Intensity", Secondary),
                ActivityItem(Icons.Filled.FitnessCenter, Tertiary, "Lower Body Strength", "Oct 22 • Weights", "52:15", "New PB", Tertiary),
            )
            activities.forEach { activity ->
                ActivityRow(activity)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Account settings
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text("ACCOUNT SETTINGS", style = MaterialTheme.typography.labelLarge, color = OnSurfaceVariant)
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SurfaceContainerLow)
            ) {
                SettingsRow(Icons.Outlined.CreditCard, "Subscription", "PRO PLAN", Primary)
                HorizontalDivider(color = OutlineVariant.copy(alpha = 0.3f), thickness = 0.5.dp, modifier = Modifier.padding(start = 56.dp))
                SettingsRow(Icons.Outlined.Watch, "Connected Devices", "●", PrimaryDim)
                HorizontalDivider(color = OutlineVariant.copy(alpha = 0.3f), thickness = 0.5.dp, modifier = Modifier.padding(start = 56.dp))
                SettingsRow(Icons.Outlined.Lock, "Privacy & Security", "", null)
                HorizontalDivider(color = OutlineVariant.copy(alpha = 0.3f), thickness = 0.5.dp, modifier = Modifier.padding(start = 56.dp))
                SettingsRow(Icons.Outlined.Help, "Help Center", "", null)
            }
        }

        Spacer(Modifier.height(16.dp))

        // Sign out
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = Brush.horizontalGradient(listOf(ErrorColor.copy(alpha = 0.3f), ErrorColor.copy(alpha = 0.3f)))
            )
        ) {
            Text(
                "SIGN OUT ENGINE",
                style = MaterialTheme.typography.labelLarge.copy(letterSpacing = 2.sp),
                color = ErrorColor
            )
        }
    }
}

private data class ActivityItem(
    val icon: ImageVector,
    val iconTint: Color,
    val title: String,
    val subtitle: String,
    val duration: String,
    val badge: String,
    val badgeColor: Color
)

@Composable
private fun ActivityRow(item: ActivityItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerHigh, RoundedCornerShape(12.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(SurfaceContainerHighest, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(item.icon, contentDescription = null, tint = item.iconTint, modifier = Modifier.size(22.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(item.title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = OnSurface)
            Text(item.subtitle, style = MaterialTheme.typography.bodySmall, color = OnSurfaceVariant)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(item.duration, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = OnSurface)
            Text(
                item.badge,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp),
                color = item.badgeColor
            )
        }
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
private fun SettingsRow(icon: ImageVector, title: String, badge: String, badgeColor: Color?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(icon, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(20.dp))
        Text(title, style = MaterialTheme.typography.titleSmall, color = OnSurface, modifier = Modifier.weight(1f))
        if (badge.isNotEmpty() && badgeColor != null) {
            Text(badge, style = MaterialTheme.typography.labelLarge, color = badgeColor)
        }
        Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = Outline, modifier = Modifier.size(16.dp))
    }
}

// Extension to add a bottom border accent
private fun Modifier.border(bottomBorder: Boolean): Modifier =
    if (bottomBorder) this.border(
        width = 1.dp,
        brush = Brush.verticalGradient(listOf(Color.Transparent, Primary.copy(alpha = 0.1f))),
        shape = RoundedCornerShape(14.dp)
    ) else this
