package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnesstracker.data.Workout
import com.example.fitnesstracker.data.WorkoutCategory
import com.example.fitnesstracker.data.WorkoutData
import com.example.fitnesstracker.data.Intensity
import com.example.fitnesstracker.ui.theme.*

@Composable
fun WorkoutLibraryScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf(WorkoutCategory.ALL) }
    val filtered = if (selectedCategory == WorkoutCategory.ALL)
        WorkoutData.workouts
    else
        WorkoutData.workouts.filter { it.category == selectedCategory }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
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
                    "TRAIN",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
                    color = OnSurface
                )
                Text(
                    "${WorkoutData.workouts.size} workouts",
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant
                )
            }
        }

        // Category filter chips
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WorkoutCategory.values().forEach { cat ->
                val isSelected = selectedCategory == cat
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(if (isSelected) NeonGreen else SurfaceContainerHigh)
                        .border(
                            width = if (isSelected) 0.dp else 1.dp,
                            color = OutlineVariant,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .clickable { selectedCategory = cat }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        cat.label,
                        style = MaterialTheme.typography.labelLarge,
                        color = if (isSelected) Color(0xFF0D0F0C) else OnSurface
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filtered, key = { it.id }) { workout ->
                WorkoutCard(workout = workout, onClick = { navController.navigate("workout_detail/${workout.id}") })
            }
            item { Spacer(Modifier.height(8.dp)) }
            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

@Composable
private fun WorkoutCard(workout: Workout, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = workout.imageUrl,
            contentDescription = workout.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xCC0D0F0C)),
                        startY = 60f
                    )
                )
        )
        // Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            // Intensity badge
            Box(
                modifier = Modifier
                    .background(
                        when (workout.intensity) {
                            Intensity.HIGH -> ErrorContainer
                            Intensity.MEDIUM -> SecondaryContainer
                            Intensity.LOW -> SurfaceContainerHighest
                        },
                        RoundedCornerShape(100.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    workout.intensity.label.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = when (workout.intensity) {
                        Intensity.HIGH -> ErrorColor
                        Intensity.MEDIUM -> Secondary
                        Intensity.LOW -> OnSurfaceVariant
                    }
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                workout.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = OnSurface,
                maxLines = 2
            )
            Text(
                "${workout.durationMinutes} min • ${workout.category.label}",
                style = MaterialTheme.typography.labelMedium,
                color = OnSurfaceVariant
            )
        }
    }
}
