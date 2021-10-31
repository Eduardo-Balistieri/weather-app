package com.example.weather.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R
import com.example.weather.ui.theme.searchBoxBackground
import com.example.weather.ui.theme.searchBoxContent

@Composable
fun SearchBox(
    text: String,
    onTextChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(id = R.string.search_icon),
                tint = MaterialTheme.colors.searchBoxContent,
                modifier = Modifier.alpha(ContentAlpha.disabled)
            )
        },
        shape = RoundedCornerShape(5),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_box_placeholder),
                color = MaterialTheme.colors.searchBoxContent,
                modifier = Modifier.alpha(ContentAlpha.disabled)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.searchBoxBackground,
            textColor = MaterialTheme.colors.searchBoxContent,
            cursorColor = MaterialTheme.colors.searchBoxContent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colors.searchBoxContent,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}

@Composable
@Preview
fun SearchBoxPreview() {
    SearchBox(
        text = "",
        onTextChange = {}
    )
}
