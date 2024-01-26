package com.example.tasktracker.components.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktracker.components.components.ComponentsShape.getShapeDp
import com.example.tasktracker.data.ErrorStatus
import com.example.tasktracker.ui.theme.colorActiveButton
import com.example.tasktracker.ui.theme.colorActiveProgressBar
import com.example.tasktracker.ui.theme.colorBackground
import com.example.tasktracker.ui.theme.colorLink
import com.example.tasktracker.ui.theme.colorNotActive
import com.example.tasktracker.ui.theme.colorNotActiveButton
import com.example.tasktracker.ui.theme.colorNotActiveProgressBar
import com.example.tasktracker.ui.theme.colorOnError
import com.example.tasktracker.ui.theme.colorOnSecondary
import com.example.tasktracker.ui.theme.colorOnTertiary
import com.example.tasktracker.ui.theme.fontFamily

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .paddingFromBaseline(0.dp, 32.dp)
            .padding(start = 21.dp, end = 21.dp),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .heightIn(),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun LabelTextComponent(value: String, errorStatus: ErrorStatus) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(MaterialTheme.colorScheme.background),
        style = MaterialTheme.typography.labelSmall,
        color = when(errorStatus) {
            ErrorStatus.INITIAL -> colorOnSecondary
            ErrorStatus.ERROR -> colorOnError
            else -> colorOnTertiary
        }
    )
}

@Composable
fun CustomProgressBar(currentFragment: Int) {
    val circleColor1 = animateColorAsState(
        targetValue = if (currentFragment >= 0) colorActiveProgressBar else colorNotActiveProgressBar,
        animationSpec = tween(300), label = ""
    )
    val circleColor2 = animateColorAsState(
        targetValue = if (currentFragment >= 1) colorActiveProgressBar else colorNotActiveProgressBar,
        animationSpec = tween(300), label = ""
    )
    val lineColor = animateColorAsState(
        targetValue = if (currentFragment >= 1) colorActiveProgressBar else colorNotActiveProgressBar,
        animationSpec = tween(300), label = ""
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircleIndicator("1", circleColor1.value)
        Spacer(
            modifier = Modifier
                .background(lineColor.value)
                .width(95.dp)
                .height(4.dp)
        )
        CircleIndicator("2", circleColor2.value)
    }
}

@Composable
fun CircleIndicator(text: String, color: Color) {
    Box(
        modifier = Modifier
            .size(25.dp)
            .background(color, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = colorBackground,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}

// TODO: Сделать функцию перехода на активити авторизации.
@Composable
fun TextLink(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier
            .background(colorNotActive)
            .width(30.dp)
            .height(1.dp))
        Spacer(modifier = Modifier.width(7.dp))

        ClickableText(
            text = AnnotatedString(text),
            style = TextStyle(
                color = colorLink,
                fontSize = 18.sp,
                fontFamily = fontFamily
            ),
            onClick = {

            }
        )

        Spacer(modifier = Modifier.width(7.dp))
        Spacer(modifier = Modifier
            .background(colorNotActive)
            .width(30.dp)
            .height(1.dp))
    }
}

@Composable
fun TextButton(
    onClick: () -> Unit,
    text: String,
    shape: CornerBasedShape = RoundedCornerShape(getShapeDp("large")),
    enabled: Boolean = true,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = shape,
        contentPadding = PaddingValues(vertical = 13.5.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = colorNotActiveButton,
            disabledContentColor = colorNotActive,
            containerColor = colorActiveButton,
            contentColor = colorOnSecondary
        ),
    ) {
        NormalTextComponent(value = text)
    }
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    painterResource: Painter,
    shape: CornerBasedShape = RoundedCornerShape(getShapeDp("large")),
    enabled: Boolean = true,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = shape,
        contentPadding = PaddingValues(vertical = 16.6.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = colorNotActiveButton,
            disabledContentColor = colorNotActive,
            containerColor = colorActiveButton,
            contentColor = colorOnSecondary
        ),
    ) {
        Icon(painter = painterResource, contentDescription = "")
    }
}
