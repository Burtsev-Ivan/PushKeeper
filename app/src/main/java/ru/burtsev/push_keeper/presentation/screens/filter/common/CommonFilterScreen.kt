package ru.burtsev.push_keeper.presentation.screens.filter.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import ru.burtsev.push_keeper.presentation.di.koinViewModel


@Composable
fun CommonFilterScreen(
    navController: NavHostController,
    viewModel: CommonFilterViewModel = koinViewModel(),
) {
    val viewState = viewModel.filterStateLiveData.observeAsState().value ?: return

    Column(modifier = Modifier.fillMaxHeight()) {
        AutoCompleteBox(
            state = viewState.autocompleteState,
            onTextChanged = { viewModel.onTextChanged(it) },
            onFocusChanged = { viewModel.onFocusChanged(it.isFocused) },
            onItemClick = { viewModel.onItemClick(it) },
        )
        Divider(modifier = Modifier.height(10.dp))

        TextField(value = "sdf", onValueChange = {})

    }


}

@Composable
fun <T> AutoCompleteBox(
    state: AutocompleteState<T>,
    onTextChanged: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    onItemClick: (AutocompleteItem<T>) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        OutlinedTextField(
            modifier = Modifier
                .onFocusChanged { onFocusChanged(it) }
                .onGloballyPositioned {
                    textFieldSize = it.size.toSize()
                },
            value = state.text,
            onValueChange = onTextChanged
        )
        AnimatedVisibility(visible = state.isSearching) {
            LazyColumn(
                modifier = Modifier.autoComplete(textFieldSize),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(state.filteredItems) { item ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(item) }
                    ) {
                        Text(textAlign = TextAlign.Center, text = item.text)
                    }
                }

            }
        }
    }
}

private fun Modifier.autoComplete(textFieldSize: Size): Modifier = composed {
    val width = with(LocalDensity.current) { textFieldSize.width.toDp() }
    val baseModifier = heightIn(0.dp, (56 * 3).dp)
    baseModifier
        .width(width)
        .border(
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)
        )
}

