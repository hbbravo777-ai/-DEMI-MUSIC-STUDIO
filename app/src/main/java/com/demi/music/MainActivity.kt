package com.demi.music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF111018)
private val Panel = Color(0xFF211D2B)
private val Violet = Color(0xFFB99AFF)
private val Mint = Color(0xFF83E6C2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Ink,
                    surface = Panel,
                    primary = Violet,
                    secondary = Mint,
                    onBackground = Color.White,
                    onSurface = Color.White
                )
            ) {
                DemiApp()
            }
        }
    }
}

@Composable
private fun DemiApp() {
    val tabs = listOf("Home", "Studio", "Splitter", "Voice Cover", "Discover", "Profile")
    var selected by remember { mutableStateOf("Home") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Ink
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 28.dp)
        ) {
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text("DEMI", color = Violet, fontSize = 30.sp, fontWeight = FontWeight.Black)
                Text(
                    "DECENTRALIZED INDEPENDENT MUSIC ICONS",
                    color = Mint,
                    fontSize = 9.sp,
                    letterSpacing = 1.2.sp
                )
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tabs.forEach { tab ->
                    Surface(
                        color = if (selected == tab) Violet else Panel,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.clickable { selected = tab }
                    ) {
                        Text(
                            tab,
                            color = if (selected == tab) Ink else Color.White,
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(Modifier.height(22.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp)
            ) {
                when (selected) {
                    "Home" -> HomeScreen(onOpenStudio = { selected = "Studio" })
                    "Studio" -> StudioScreen()
                    "Splitter" -> FeatureScreen(
                        "AI MUSIC SPLITTER",
                        "Separate a track into vocals, drums, bass and other instruments.",
                        "Stem separation engine is the next development milestone."
                    )
                    "Voice Cover" -> FeatureScreen(
                        "VOICE COVER",
                        "Record your own performance over music you have permission to use.",
                        "Recording, vocal alignment and rights checks will be added in a later milestone."
                    )
                    "Discover" -> FeatureScreen(
                        "DISCOVER MUSIC",
                        "Find independent artists, tracks, playlists and new releases.",
                        "Artist publishing and the community feed will connect in a later milestone."
                    )
                    else -> FeatureScreen(
                        "ARTIST PROFILE",
                        "Build your artist page, share releases and connect with listeners.",
                        "Profiles, follows, comments and analytics are planned."
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(onOpenStudio: () -> Unit) {
    Text("MAKE YOUR NEXT RECORD.", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Text("Your music. Your sound. Your stage.", color = Color.LightGray, fontSize = 14.sp)
    Spacer(Modifier.height(18.dp))
    Card(
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(20.dp)) {
            Text("YOUR STUDIO", color = Mint, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("Create without limits.", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Start a track, record vocals and build your sound.", color = Color.LightGray)
            Spacer(Modifier.height(16.dp))
            Button(onClick = onOpenStudio) { Text("OPEN STUDIO") }
        }
    }
    Spacer(Modifier.height(18.dp))
    Text("CREATE WITH DEMI", color = Violet, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp))
    FeatureTile("🎙  Multitrack Studio", "Record and arrange your tracks")
    FeatureTile("✂  AI Music Splitter", "Work with individual song stems")
    FeatureTile("♫  Voice Cover", "Make a cover with your own vocals")
    FeatureTile("✦  Artist Community", "Publish music and find listeners")
}

@Composable
private fun StudioScreen() {
    var playing by remember { mutableStateOf(false) }
    var trackCount by remember { mutableStateOf(2) }
    Text("RECORDING STUDIO", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Bold)
    Text("Project: New DEMI session", color = Color.LightGray)
    Spacer(Modifier.height(18.dp))
    Card(
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("SESSION TIMELINE", color = Mint, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(14.dp))
            repeat(trackCount) { index ->
                Text("TRACK ${index + 1}", color = Color.LightGray, fontSize = 11.sp)
                Spacer(Modifier.height(5.dp))
                Surface(
                    color = if (index % 2 == 0) Color(0xFF554477) else Color(0xFF285B50),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().height(34.dp)
                ) {}
                Spacer(Modifier.height(12.dp))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { trackCount = (trackCount + 1).coerceAtMost(8) }) {
                    Text("ADD TRACK")
                }
                Button(onClick = { playing = !playing }) {
                    Text(if (playing) "PAUSE PREVIEW" else "PLAY PREVIEW")
                }
            }
        }
    }
    Spacer(Modifier.height(12.dp))
    Text("Prototype controls only — live audio recording, playback and editing are not connected yet.", color = Color.LightGray, fontSize = 12.sp)
}

@Composable
private fun FeatureScreen(title: String, description: String, status: String) {
    Text(title, color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp))
    Text(description, color = Color.LightGray, fontSize = 15.sp)
    Spacer(Modifier.height(18.dp))
    Card(
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(status, color = Mint, modifier = Modifier.padding(18.dp))
    }
}

@Composable
private fun FeatureTile(title: String, subtitle: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp)
    ) {
        Column(Modifier.padding(14.dp)) {
            Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = Color.LightGray, fontSize = 12.sp)
        }
    }
}
