package com.example.larosesociohub.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.larosesociohub.R
import com.example.larosesociohub.model.MenuItem
import com.example.larosesociohub.ui.theme.RosePink
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMenuScreen(
    menuId: Int,
    onBack: () -> Unit
) {
    // Data list menu dengan deskripsi lengkap terbaru sesuai permintaan Prompt 4
    val menuItems = listOf(
        MenuItem(1, "Rose Latte", 38000, "Perpaduan sempurna espresso premium dengan sirup mawar segar dan susu steam yang lembut. Disajikan dalam cangkir cantik bermotif bunga dengan taburan kelopak mawar kering di atasnya. Setiap tegukan menghadirkan aroma bunga yang menenangkan dengan rasa kopi yang kaya dan creamy.", R.drawable.rose_latte),
        MenuItem(2, "Lavender Cake", 45000, "Kue chiffon lembut berlapis krim lavender Prancis yang harum dan tidak terlalu manis. Dengan warna ungu cantik yang instagrammable, setiap lapisan kuenya ringan dan meleleh di mulut. Disajikan dengan taburan bunga lavender kering di atasnya. Cocok untuk menemani sore hari yang santai bersama orang tersayang.", R.drawable.lavender_cake),
        MenuItem(3, "Strawberry Matchafloat", 42000, "Minuman unik perpaduan matcha Jepang premium dengan foam stroberi segar yang creamy di atasnya. Dilengkapi mutiara boba kenyal yang menambah sensasi setiap tegukan. Perpaduan rasa pahit matcha dan manis stroberi yang perfectly balanced, cocok untuk kamu yang suka minuman unik dan kekinian.", R.drawable.strawberry_matchafloat),
        MenuItem(4, "Floral Toast", 35000, "Roti brioche tebal panggang dengan selai butterfly pea berwarna biru keunguan yang cantik. Dihias dengan bunga-bunga edible segar dan blackberry di atasnya. Rasanya manis dengan tekstur roti yang crispy di luar namun lembut di dalam. Sajian breakfast aesthetic yang sayang untuk tidak difoto!", R.drawable.floral_toast),
        MenuItem(5, "Peony Punch", 28000, "Minuman sparkling segar berbasis hibiscus dengan warna merah muda yang cantik. Berisi jelly leci kenyal yang manis dan menyegarkan. Cocok diminum dingin di siang hari, menghadirkan sensasi seperti minum bunga yang mekar. Tanpa pewarna buatan, warna cantiknya 100% dari bunga hibiscus asli.", R.drawable.peony_punch),
        MenuItem(6, "Sakura Cheesecake", 55000, "Cheesecake lembut ala Jepang dengan tekstur yang super fluffy dan creamy. Disajikan dengan selai sakura khas Jepang yang harum dan sedikit asam manis. Dihias dengan kelopak sakura kering yang cantik di atasnya. Setiap gigitan terasa seperti sedang menikmati musim semi di Jepang.", R.drawable.sakura_cheesecake)
    )

    val item = menuItems.find { it.id == menuId }
    var visible by remember { mutableStateOf(false) }
    var rating by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detail Menu", fontWeight = FontWeight.Bold, color = RosePink) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = RosePink
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White.copy(alpha = 0.9f)
                )
            )
        },
        containerColor = Color(0xFFFFF5F7)
    ) { innerPadding ->
        if (item != null) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(800)) + slideInVertically(
                    initialOffsetY = { it / 4 },
                    animationSpec = tween(800)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Gambar Besar
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(24.dp)) {
                        // Badge Best Seller
                        if (item.id == 1 || item.id == 6) {
                            Surface(
                                color = Color(0xFFFFD700),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.padding(bottom = 12.dp)
                            ) {
                                Text(
                                    text = "🌟 Best Seller",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF6B4E00),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray,
                                modifier = Modifier.weight(1f)
                            )
                            
                            // Badge Harga
                            Surface(
                                color = RosePink,
                                shape = CircleShape,
                                modifier = Modifier.padding(start = 12.dp)
                            ) {
                                Text(
                                    text = formatRupiah(item.price),
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Deskripsi Produk",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = RosePink
                        )
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 8.dp),
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Widget Rating Bintang [3]
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Berikan Rating Anda",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.SemiBold
                                )
                                
                                Row(
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    for (i in 1..5) {
                                        IconButton(
                                            onClick = { rating = i },
                                            modifier = Modifier.size(48.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Star,
                                                contentDescription = "Star $i",
                                                tint = if (i <= rating) Color(0xFFFFD700) else Color(0xFFE0E0E0),
                                                modifier = Modifier.size(36.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        Button(
                            onClick = onBack,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = RosePink),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Kembali ke Menu", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

private fun formatRupiah(amount: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    return format.format(amount.toLong()).replace("Rp", "Rp ")
}
