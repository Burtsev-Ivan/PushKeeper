package ru.burtsev.push_keeper.presentation.screens.filter.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.burtsev.push_keeper.domain.model.app.AppInfo
import ru.burtsev.push_keeper.presentation.common.AppImage
import ru.burtsev.push_keeper.presentation.di.koinViewModel


@Composable
fun FilterAppScreen(
    navController: NavHostController,
    viewModel: FilterAppViewModel = koinViewModel(),
) {
    val viewState = viewModel.appInfoLiveData.observeAsState().value ?: return

    LaunchedEffect(viewState.event) {
        when (viewState.event) {
            Event.GoBack -> navController.popBackStack()
            null -> {}
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text(text = "Фильтрация") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                Checkbox(
                    checked = viewState.isCheckedAll,
                    onCheckedChange = { viewModel.onCheckedAll() }
                )
            },
            backgroundColor = Color.Transparent,
            contentColor = Color.Gray,
            elevation = 2.dp
        )

        if (viewState.isLoading) {
            LoadingView()
        } else {
            AppsView(
                viewState = viewState,
                onCheckedChange = { appInfo -> viewModel.onCheckedChange(appInfo) },
                onSaveClick = { viewModel.onSaveClick() }
            )
        }
    }

}

@Composable
private fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ColumnScope.AppsView(
    viewState: FilterAppViewState,
    onCheckedChange: (AppInfo) -> Unit,
    onSaveClick: () -> Unit,
) {
    LazyColumn(modifier = Modifier.Companion.weight(1f)) {
        viewState.apps.forEach {
            item {
                ApplicationItem(it, onCheckedChange)
            }
        }
    }

    Button(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .defaultMinSize(minHeight = 48.dp)
            .fillMaxWidth(),
        onClick = { onSaveClick() }
    ) {
        Text(text = "Сохранить")
    }
}

@Composable
fun ApplicationItem(
    appInfo: AppInfo,
    onCheckedChange: (AppInfo) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            AppImage(appInfo.packages)
            Text(
                text = appInfo.appName,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(CenterVertically)
                    .weight(1f)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.body1,
            )
            Checkbox(
                checked = appInfo.isEnabled,
                onCheckedChange = { onCheckedChange(appInfo) }
            )
        }

    }
}


@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
fun AppInfoItem_Preview() {
    ApplicationItem(
        AppInfo(
            id = 0,
            packages = "dasadasdasd",
            appName = "Viber",
            isEnabled = true
        ),
        onCheckedChange = { },
    )
}
