package ru.burtsev.push_keeper.presentation.screens.notification

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import java.text.SimpleDateFormat
import java.util.*
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.presentation.MainViewModel
import ru.burtsev.push_keeper.presentation.di.koinViewModel

private val formatter: SimpleDateFormat = SimpleDateFormat("dd.MM HH:mm:ss", Locale.getDefault())

@Composable
fun NotificationScreen(
    navController: NavHostController,
    viewModel: MainViewModel = koinViewModel(),
) {

    val viewState = viewModel.notificationLiveData.observeAsState()
    LazyColumn {
        viewState.value?.forEach {
            item {
                NotificationCardItem(it)
            }
        }

    }

}


@Composable
fun NotificationCardItem(model: NotificationEntity) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Column(Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            Row {
                AppImage(model.packages)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 0.dp, 0.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = model.appName,
                            Modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                                .weight(1f)
                                .wrapContentSize(),
                            style = MaterialTheme.typography.subtitle2,
                        )
                        val time = formatter.format(model.time)
                        Text(text = time)
                    }

                    Text(text = model.title, Modifier.padding(8.dp, 8.dp, 0.dp, 0.dp))
                }
            }
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(text = model.text, Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))

        }
    }
}

@Composable
private fun AppImage(packageName: String) {
    val appIcon = getAppIcon(LocalContext.current, packageName)
    Image(
        painter = rememberDrawablePainter(drawable = appIcon),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

private fun getAppIcon(context: Context, packageName: String): Drawable? {
    return try {
        context.packageManager.getApplicationIcon(packageName)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
}


@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
fun NotificationCardItem_Preview() {
    NotificationCardItem(
        NotificationEntity(
            id = 1,
            packages = "dasadasdasd",
            appName = "Viber",
            title = "Илья Ковалев",
            text = "Поделился с вами записью",
            time = 61165L,
        )
    )
}
