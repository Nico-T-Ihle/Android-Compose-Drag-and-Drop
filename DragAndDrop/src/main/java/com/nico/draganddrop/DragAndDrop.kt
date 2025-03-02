package com.nico.draganddrop

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.layout.boundsInWindow

class DragTargetInfo {
    var isDragging: Boolean by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dataToDrop by mutableStateOf<Any?>(null)
}

val LocalDragTargetInfo = compositionLocalOf { DragTargetInfo() }

@Composable
fun LongPressDraggable(
    modifier: Modifier,
    getDataType: Int,
    item: Int,
    zIndex: MutableState<Float>,
    content: @Composable BoxScope.() -> Unit,
) {
    val state = LocalDragTargetInfo.current
    Box(modifier = modifier) {
        content()
        if (getDataType == 1 && state.isDragging && state.dataToDrop == item) {
            zIndex.value = 80f
            var targetSize by remember {
                mutableStateOf(IntSize.Zero)
            }
            Box(modifier = Modifier
                .graphicsLayer {
                    val offsetX = ((targetSize.width / 2f) + state.dragOffset.x)
                    val offsetY = ((targetSize.height / 2f) + state.dragOffset.y)
                    scaleX = 1f
                    scaleY = 1f
                    alpha = if (targetSize == IntSize.Zero) 0f else .9f
                    translationX = offsetX - targetSize.width / 2f
                    translationY = offsetY - targetSize.height / 2f
                }
                .onGloballyPositioned {
                    targetSize = it.size
                }
            ) {
                state.draggableComposable?.invoke()
            }
        }
    }
}

@Composable
fun <T> DragTarget(
    isActive: Boolean,
    modifier: Modifier,
    dataToDrop: T,
    content: @Composable () -> Unit,
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    if(isActive) {
        Box(
            modifier = modifier
                .onGloballyPositioned {
                    currentPosition = it.localToWindow(Offset.Zero)
                }
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            currentState.dataToDrop = dataToDrop
                            currentState.isDragging = true
                            currentState.dragPosition = currentPosition + it
                            currentState.draggableComposable = content
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                        },
                        onDragEnd = {
                            currentState.isDragging = false
                            currentState.dragOffset = Offset.Zero
                        },
                        onDragCancel = {
                            currentState.dragOffset = Offset.Zero
                            currentState.isDragging = false
                        }
                    )
                }
        ) {
            content()
        }
    } else {
        content()
    }
}

/** is handling the drop logic to know which element is drop on which content element **/
@Composable
fun <T> DropTarget(
    getDataType: Int,
    item: T? = null,
    modifier: Modifier,
    content: @Composable (BoxScope.(isInBound: Boolean, data: T?) -> Unit),
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDropTarget by remember { mutableStateOf(false) }

    Box(modifier = modifier.onGloballyPositioned {
        it.boundsInWindow().let { rect ->
            if (item != null) {
                isCurrentDropTarget = if (getDataType == 1 && dragInfo.dataToDrop == item) {
                    false
                } else {
                    rect.contains(dragPosition + dragOffset) && dragInfo.isDragging
                }
            }
        }
    }) {
        val data = if (isCurrentDropTarget && !dragInfo.isDragging) dragInfo.dataToDrop as T? else null
        content(isCurrentDropTarget, data)
    }
}
