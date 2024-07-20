import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import petarljubic.composeapp.generated.resources.Res
import petarljubic.composeapp.generated.resources.compose_multiplatform
import petarljubic.composeapp.generated.resources.cover
import petarljubic.composeapp.generated.resources.instagram
import petarljubic.composeapp.generated.resources.linkedin
import petarljubic.composeapp.generated.resources.twitter


@OptIn(
    ExperimentalCoilApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3WindowSizeClassApi::class
)
@Composable
@Preview
fun App() {
    MaterialTheme() {
        setSingletonImageLoaderFactory { context ->
            getAsyncImageLoader(context)
        }
        var showContent by remember { mutableStateOf(false) }
        Column(
            Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Image(
                    painter = painterResource(Res.drawable.cover),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().aspectRatio(2f),
                )
                GreetingText(Modifier.padding(10.dp).align(Alignment.BottomStart))
                SocialNetworks(Modifier.padding(10.dp).align(Alignment.TopEnd))
            }
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Composable
fun SocialNetworks(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        Icon(
            painterResource(Res.drawable.linkedin),
            tint = Color.White,
            contentDescription = "linkedin",
            modifier = Modifier.size(36.dp).clickable {

            })
        Icon(
            painterResource(Res.drawable.twitter),
            tint = Color.White,
            contentDescription = "twitter",
            modifier = Modifier.size(36.dp).clickable {

            })
        Icon(
            painterResource(Res.drawable.instagram),
            tint = Color.White,
            contentDescription = "instagram",
            modifier = Modifier.size(36.dp).clickable {

            })
    }
}

@Composable
private fun GreetingText(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            "Hi all!",
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.h1,
            color = Color.White
        )
        Text(
            "My name is Petar Ljubic",
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.h2,
            color = Color.White
        )
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()