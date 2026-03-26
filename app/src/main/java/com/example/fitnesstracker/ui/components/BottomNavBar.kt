package com.example.fitnesstracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.ui.theme.*

sealed class NavTab(
    val route: String,
    val label: String,
    val iconFilled: ImageVector,
    val iconOutlined: ImageVector
) {
    object Dashboard : NavTab("dashboard", "DASHBOARD", Icons.Filled.Home, Icons.Outlined.Home)
    object Train : NavTab("train", "TRAIN", Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter)
    object Stats : NavTab("progress", "STATS", Icons.Filled.BarChart, Icons.Outlined.BarChart)
    object Profile : NavTab("profile", "PROFILE", Icons.Filled.Person, Icons.Outlined.Person)
}

val bottomNavTabs = listOf(NavTab.Dashboard, NavTab.Train, NavTab.Stats, NavTab.Profile)

@Composable
fun BottomNavBar(currentRoute: String, onNavigate: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceContainerLow.copy(alpha = 0.95f))
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .navigationBarsPadding()
            .imePadding(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomNavTabs.forEach { tab ->
            val isActive = currentRoute.startsWith(tab.route)
            if (isActive) {
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(NeonGreen)
                        .clickable { onNavigate(tab.route) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = tab.iconFilled,
                        contentDescription = tab.label,
                        tint = Color(0xFF0D0F0C),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF0D0F0C)
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .clickable { onNavigate(tab.route) }
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = tab.iconOutlined,
                        contentDescription = tab.label,
                        tint = OnSurface.copy(alpha = 0.4f),
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurface.copy(alpha = 0.4f)
                    )
                }
            }
        }
    }
}
