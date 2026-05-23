package com.example.larosesociohub.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.larosesociohub.R
import com.example.larosesociohub.data.SharedPreferencesManager
import com.example.larosesociohub.ui.theme.Lavender
import com.example.larosesociohub.ui.theme.RosePink

@Composable
fun HomeScreen(
    onNavigateToMenu: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val context = LocalContext.current
    val sharedPrefs = remember { SharedPreferencesManager(context) }
    val restaurantName = sharedPrefs.restaurantName

    // Animation state for the entrance
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    // Background Gradient (Fixed to Light Theme style)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(RosePink.copy(alpha = 0.2f), Lavender.copy(alpha = 0.2f))
                )
            )
    ) {
        // Decorative Flowers (Floating effect)
        FloatingFlowers()

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1500)) + slideInVertically(
                initialOffsetY = { it / 2 },
                animationSpec = tween(1500)
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Actual Café Logo with Floral Border
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .border(4.dp, RosePink, CircleShape)
                        .padding(8.dp)
                        .border(2.dp, Lavender, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_larose),
                        contentDescription = "Logo Larose Sociohub",
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Restaurant Name
                Text(
                    text = restaurantName,
                    style = MaterialTheme.typography.displayLarge,
                    color = RosePink,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )

                // Tagline
                Text(
                    text = "Where every sip blooms 🌸",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Spacer(modifier = Modifier.height(60.dp))

                // Action Buttons
                Button(
                    onClick = onNavigateToMenu,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RosePink),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text("Lihat Menu", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedButton(
                    onClick = onNavigateToProfile,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, Lavender),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Lavender)
                ) {
                    Text("Profil Cafe", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun FloatingFlowers() {
    val infiniteTransition = rememberInfiniteTransition(label = "flowers")
    
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Text("🌸", modifier = Modifier.align(Alignment.TopStart).padding(40.dp).graphicsLayer { rotationZ = angle }, fontSize = 35.sp)
        Text("🌺", modifier = Modifier.align(Alignment.TopEnd).padding(60.dp).graphicsLayer { rotationZ = -angle }, fontSize = 30.sp)
        Text("🌷", modifier = Modifier.align(Alignment.BottomStart).padding(80.dp).graphicsLayer { rotationZ = angle * 0.5f }, fontSize = 40.sp)
        Text("🌼", modifier = Modifier.align(Alignment.BottomEnd).padding(50.dp).graphicsLayer { rotationZ = -angle * 0.7f }, fontSize = 25.sp)
    }
}
