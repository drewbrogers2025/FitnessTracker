package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fitnesstracker.data.Exercise
import com.example.fitnesstracker.data.WorkoutData
import com.example.fitnesstracker.ui.theme.*

@Composable
fun WorkoutDetailScreen(navController: NavController, workoutId: String) {
    val workout = WorkoutData.workouts.find { it.id == workoutId } ?: WorkoutData.workouts.first()

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                // Hero image
                Box(modifier = Modifier.fillMaxWidth().height(380.dp)) {
                    AsyncImage(
                        model = workout.imageUrl,
                        contentDescription = workout.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Background.copy(alpha = 0.3f),
                                        Background.copy(alpha = 0.8f),
                                        Background
                                    )
                                )
                            )
                    )
                    // Top bar overlay
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .background(SurfaceContainerHigh.copy(alpha = 0.8f), CircleShape)
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = OnSurface)
                        }
                        Text(
                            "KINETIC",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Black,
                                letterSpacing = (-0.5).sp
                            ),
                            color = OnSurface
                        )
                    }
                    // Title at bottom of hero
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(PrimaryContainer, RoundedCornerShape(100.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                "POWER PHASE",
                                style = MaterialTheme.typography.labelSmall,
                                color = OnPrimaryContainer
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            workout.title,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = (-1).sp
                            ),
                            color = OnSurface
                        )
                    }
                }
            }

            item {
                // Description
                Text(
                    workout.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                )
                Spacer(Modifier.height(16.dp))
            }

            item {
                // Stats row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SurfaceContainerHigh),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WorkoutStat("${workout.durationMinutes}", "MINUTES")
                    VerticalDivider(modifier = Modifier.height(60.dp).padding(vertical = 12.dp), color = OutlineVariant)
                    WorkoutStat("${workout.exercises.size}", "EXERCISES")
                    VerticalDivider(modifier = Modifier.height(60.dp).padding(vertical = 12.dp), color = OutlineVariant)
                    WorkoutStat(workout.intensity.label.uppercase(), "INTENSITY")
                }
                Spacer(Modifier.height(24.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "MOVEMENT BREAKDOWN",
                        style = MaterialTheme.typography.labelLarge,
                        color = OnSurface
                    )
                    Text(
                        "Target: Hypertrophy",
                        style = MaterialTheme.typography.labelMedium,
                        color = OnSurfaceVariant
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            items(workout.exercises) { exercise ->
                ExerciseRow(exercise)
            }

            item { Spacer(Modifier.height(120.dp)) }
        }

        // Floating start button
        Button(
            onClick = { navController.navigate("activity_tracking") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .navigationBarsPadding()
                .height(60.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryContainer,
                contentColor = OnPrimaryContainer
            )
        ) {
            Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(8.dp))
            Text(
                "START WORKOUT",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
            )
        }
    }
}

@Composable
private fun WorkoutStat(value: String, label: String) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            value,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
            color = PrimaryFixed
        )
        Text(label, style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
    }
}

@Composable
private fun ExerciseRow(exercise: Exercise) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .background(SurfaceBright, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.FitnessCenter,
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(24.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                exercise.name,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = OnSurface
            )
            Text(
                "${exercise.sets} Sets • ${exercise.repsRange} Reps",
                style = MaterialTheme.typography.labelMedium,
                color = OnSurfaceVariant
            )
        }
        Text(
            exercise.weight,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic
            ),
            color = if (exercise.weight.contains("MAX") || exercise.weight.contains("BW"))
                OnSurface else PrimaryFixed
        )
    }
    Spacer(Modifier.height(4.dp))
}
