package com.example.larosesociohub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.larosesociohub.R
import com.example.larosesociohub.model.MenuItem
import com.example.larosesociohub.ui.theme.RosePink
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    onNavigateToDetail: (Int) -> Unit,
    onBack: () -> Unit
) {
    val menuItems = listOf(
        MenuItem(1, "Rose Latte", 38000, "Perpaduan sempurna espresso premium dengan sirup mawar segar dan susu steam yang lembut. Disajikan dalam cangkir cantik bermotif bunga dengan taburan kelopak mawar kering di atasnya. Setiap tegukan menghadirkan aroma bunga yang menenangkan dengan rasa kopi yang kaya dan creamy.", R.drawable.rose_latte),
        MenuItem(2, "Lavender Cake", 45000, "Kue chiffon lembut berlapis krim lavender Prancis yang harum dan tidak terlalu manis. Dengan warna ungu cantik yang instagrammable, setiap lapisan kuenya ringan dan meleleh di mulut. Disajikan dengan taburan bunga lavender kering di atasnya. Cocok untuk menemani sore hari yang santai bersama orang tersayang.", R.drawable.lavender_cake),
        MenuItem(3, "Strawberry Matchafloat", 42000, "Minuman unik perpaduan matcha Jepang premium dengan foam stroberi segar yang creamy di atasnya. Dilengkapi mutiara boba kenyal yang menambah sensasi setiap tegukan. Perpaduan rasa pahit matcha dan manis stroberi yang perfectly balanced, cocok untuk kamu yang suka minuman unik dan kekinian.", R.drawable.strawberry_matchafloat),
        MenuItem(4, "Floral Toast", 35000, "Roti brioche tebal panggang dengan selai butterfly pea berwarna biru keunguan yang cantik. Dihias dengan bunga-bunga edible segar dan blackberry di atasnya. Rasanya manis dengan tekstur roti yang crispy di luar namun lembut di dalam. Sajian breakfast aesthetic yang sayang untuk tidak difoto!", R.drawable.floral_toast),
        MenuItem(5, "Peony Punch", 28000, "Minuman sparkling segar berbasis hibiscus dengan warna merah muda yang cantik. Berisi jelly leci kenyal yang manis dan menyegarkan. Cocok diminum dingin di siang hari, menghadirkan sensasi seperti minum bunga yang mekar. Tanpa pewarna buatan, warna cantiknya 100% dari bunga hibiscus asli.", R.drawable.peony_punch),
        MenuItem(6, "Sakura Cheesecake", 55000, "Cheesecake lembut ala Jepang dengan tekstur yang super fluffy dan creamy. Disajikan dengan selai sakura khas Jepang yang harum dan sedikit asam manis. Dihias dengan kelopak sakura kering yang cantik di atasnya. Setiap gigitan terasa seperti sedang menikmati musim semi di Jepang.", R.drawable.sakura_cheesecake)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Katalog Menu", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = RosePink,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
        ) {
            items(menuItems) { item ->
                MenuCard(item = item, onClick = { onNavigateToDetail(item.id) })
            }
        }
    }
}

@Composable
fun MenuCard(item: MenuItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(RosePink.copy(alpha = 0.05f)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                if (item.id == 1 || item.id == 6) {
                    Surface(
                        color = Color(0xFFFFD700),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = "🌟 Best Seller",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF6B4E00),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )
                
                Text(
                    text = formatRupiah(item.price),
                    style = MaterialTheme.typography.bodyLarge,
                    color = RosePink,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

private fun formatRupiah(amount: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    return format.format(amount.toLong()).replace("Rp", "Rp ")
}
