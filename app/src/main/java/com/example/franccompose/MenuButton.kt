package com.example.franccompose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuButton(iconId: Int, text: String, textColor: Color, onClick: () -> Unit = {}) {
    val isPressed = remember { mutableStateOf(false) }

    val scale = animateFloatAsState(
        targetValue = if (isPressed.value) 0.30f else 1f
    ).value

    val rotationY = animateFloatAsState(
        targetValue = if (isPressed.value) 5f else 0f
    ).value

    val rotationX = animateFloatAsState(
        targetValue = if (isPressed.value) 2f else 0f
    ).value

    val highlightColor = if (isPressed.value) textColor.copy(alpha = 0.3f) else Color.Transparent

    Box(
        modifier = Modifier
            .width(180.dp)
            .background(highlightColor, shape = RoundedCornerShape(16.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(160.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isPressed.value = true
                            tryAwaitRelease()
                            isPressed.value = false
                        },
                        onTap = { onClick() }
                    )
                }
                .graphicsLayer {
                    this.rotationY = rotationY
                    this.rotationX = rotationX
                    cameraDistance = 5 * density
                    scaleX = scale
                    scaleY = scale
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = text,
                    modifier = Modifier.size(130.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    fontSize = 20.sp,
                    text = text,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}




