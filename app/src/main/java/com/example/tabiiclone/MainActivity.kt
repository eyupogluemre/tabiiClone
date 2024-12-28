package com.example.tabiiclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.tabiiclone.ui.theme.TabiiCloneTheme
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setContent {
            TabiiCloneTheme {
                Anasayfa()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier.padding(0.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    CenterAlignedTopAppBar(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top)),
                        navigationIcon = {
                            IconButton(onClick = {}, modifier = Modifier.size(60.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.tabii),
                                    contentDescription = "Tabii Logo",

                                    )
                            }
                        },
                        title = {
                            Text(
                                text = "",
                                color = Color.White
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Gray.copy(alpha = 0.0f),
                            scrolledContainerColor = Color.Gray.copy(alpha = 0.0f),
                        ),
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Filled.Search,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                        }
                    )
                    Box(modifier = Modifier.offset(y = (-30).dp)) {
                        KategorilerListesi()
                    }
                }
            },
            containerColor = Color.Gray.copy(alpha = 0.0f),
            contentColor = Color.White,
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Black.copy(alpha = 0.9f),
                    contentColor = Color.White
                ) {
                    BottomBar()
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 0.dp)
                    .background(Color.Black),
            ) {
                Afisler()

                Spacer(modifier = Modifier.size(30.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Haftanın Öne Çıkanları >",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                HaftaninOneCikanlari()

                Row(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Sinemanın Büyüsü >",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                SinemaninBuyusu()

                Row(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Belgesel ve Program >",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                BelgeselVeProgram()

                Spacer(modifier = Modifier.size(20.dp))

                CanliYayinlarText()

                CanliYayinlar()

                Spacer(modifier = Modifier.size(20.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Nostalji Köşesi >",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                NostaljiKosesi()

                Row(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Türler",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Turler()




                Spacer(modifier = Modifier.size(70.dp))

            }
        }
    }
}


@Composable
fun KategorilerListesi() {

    val kategoriler = listOf("Tümü", "Dizi", "Film", "Çocuk", "Belgesel", "Tekrar İzle")

    val secilenKategori = remember { mutableStateOf("Tümü") }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray.copy(0.0f))
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.End
    ) {
        items(kategoriler.size) { index ->
            Metin(
                kategori = kategoriler[index],
                secildiMi = kategoriler[index] == secilenKategori.value,
                onclick = {
                    secilenKategori.value = kategoriler[index]
                })
        }
    }
}

@Composable
fun Metin(kategori: String, secildiMi: Boolean, onclick: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(16.dp)
            .clickable { onclick() }
    ) {
        Text(
            text = kategori,
            color = Color.White,
            fontSize = 18.sp,
            style = TextStyle(textDecoration = if (secildiMi) TextDecoration.Underline else TextDecoration.None)
        )
    }
}

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarNesne(icon = Icons.Rounded.Home, label = "Ana Sayfa", onClick = { })
        BottomBarNesne(icon = painterResource(R.drawable.listem2), label = "Listem", onClick = { })
        BottomBarNesne(icon = painterResource(R.drawable.indir3), label = "İndirilenler", onClick = { })
        BottomBarNesne(icon = Icons.Rounded.Person, label = "Profil", onClick = { })
    }
}

@Composable
fun BottomBarNesne(icon: Any, label: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (icon is ImageVector) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        } else if (icon is Painter) {
            Icon(
                painter = icon,
                contentDescription = label,
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = Color.White,
            style = TextStyle(fontSize = 12.sp)
        )
    }
}

@Composable
fun Afisler() {

    data class CarouselNesnesi(
        val resim: Int,
        val etiket: String,
        val tur: String
    )

    val items = listOf(
        CarouselNesnesi(R.drawable.derin2, "Yeni Dizi", "Bilim Kurgu"),
        CarouselNesnesi(R.drawable.cirak4, "Yeni Dizi", "Aksiyon • Suç • Gerilim"),
        CarouselNesnesi(R.drawable.kuru1, "tabii Premium", "Dram"),
        CarouselNesnesi(R.drawable.kutsal5, "tabii Premium", "Dram • Gerilim"),
        CarouselNesnesi(R.drawable.cennet2, "tabii Premium", "Komedi"),
        CarouselNesnesi(R.drawable.thepost4, "tabii Premium", "Dram • Biyografi")
    )

    val pagerState = rememberPagerState(pageCount = { items.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            Image(
                painter = painterResource(id = items[index].resim),
                contentDescription = "Carousel Image $index",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = items[pagerState.currentPage].etiket,
                color = Color(0xFF00ff99),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Text(
                text = " • ${items[pagerState.currentPage].tur}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }


        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {},
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Gray,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.Gray
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Oynat", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color(0xFF00ff99) else Color.Gray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(6.dp)
                        .background(color = color, shape = RectangleShape)
                )
            }
        }
    }
}

@Composable
fun HaftaninOneCikanlari() {
    val images = listOf(
        R.drawable.gassal5,
        R.drawable.rafadan,
        R.drawable.hayatb,
        R.drawable.kutsal5,
        R.drawable.gocebe,
        R.drawable.thepost4
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images) { imageRes ->
            Card(
                modifier = Modifier
                    .size(width = 140.dp, height = 200.dp)
                    .clickable { },
                shape = RoundedCornerShape(2.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun SinemaninBuyusu() {
    val images = listOf(
        R.drawable.dimanc,
        R.drawable.ikisafak,
        R.drawable.ayda,
        R.drawable.farewell,
        R.drawable.cennnett,
        R.drawable.hayat
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images) { imageRes ->
            Card(
                modifier = Modifier
                    .size(width = 140.dp, height = 200.dp)
                    .clickable { },
                shape = RoundedCornerShape(2.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun BelgeselVeProgram() {
    val images = listOf(
        R.drawable.naim,
        R.drawable.mitoloji,
        R.drawable.ayasofya,
        R.drawable.satranc,
        R.drawable.gercek,
        R.drawable.gemi
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images) { imageRes ->
            Card(
                modifier = Modifier
                    .size(width = 140.dp, height = 200.dp)
                    .clickable { },
                shape = RoundedCornerShape(2.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun CanliYayinlar() {
    val images = listOf(
        R.drawable.trt1,
        R.drawable.trt2,
        R.drawable.trtspor,
        R.drawable.trthaber,
        R.drawable.trtcocuk,
        R.drawable.trtworld
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF232428))
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images) { imageRes ->
            Card(
                modifier = Modifier
                    .size(width = 200.dp, height = 120.dp)
                    .clickable { }
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(2.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun CanliYayinlarText() {
    Row(
        modifier = Modifier
            .background(color = Color(0xFF232428))
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .clickable { }
                .padding(horizontal = 22.dp, vertical = 11.dp),
            text = "Canlı Yayınlar >",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

    }
    Row(
        modifier = Modifier
            .background(color = Color(0xFF232428))
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 22.dp),
            text = "Tüm TRT Kanalları",
            fontSize = 16.sp,
        )

    }
}

@Composable
fun NostaljiKosesi() {
    val images = listOf(
        R.drawable.ferhunde,
        R.drawable.yaprak,
        R.drawable.kocum,
        R.drawable.cicektaksi,
        R.drawable.bizimkiler,
        R.drawable.yeditepe
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images) { imageRes ->
            Card(
                modifier = Modifier
                    .size(width = 140.dp, height = 200.dp)
                    .clickable { },
                shape = RoundedCornerShape(2.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun Turler() {
    val images = listOf(
        R.drawable.aksiyon,
        R.drawable.tarih,
        R.drawable.dram,
        R.drawable.komedi,
        R.drawable.suc,
        R.drawable.aile
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images) { imageRes ->
            Card(
                modifier = Modifier
                    .size(width = 140.dp, height = 90.dp)
                    .clickable { }
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(2.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

