package ru.burtsev.push_keeper.presentation.common

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun AppImage(packageName: String) {
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
