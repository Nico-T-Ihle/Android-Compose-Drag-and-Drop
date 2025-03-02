package com.nico.draganddrop

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ExampleDragAndDropList(){

    /** Replace @useYourList with your list! but note it has to be a mutableStateOf! **/
    var useYourList by remember { mutableStateOf(mutableListOf(1, 2, 3)) }

    fun dragAndDropUpdateList(fromId: Int, toId: Int) {

        val fromIndex = useYourList.indexOf(fromId)
        val toIndex = useYourList.indexOf(toId)

        if (fromIndex != -1 && toIndex != -1) {
            val temp = useYourList[fromIndex]
            useYourList[fromIndex] = useYourList[toIndex]
            useYourList[toIndex] = temp
        }
    }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                itemsIndexed(useYourList) { index, item ->
                    val currentZIndex = remember { mutableFloatStateOf(2f) }
                    val hapticFeedback = LocalHapticFeedback.current

                    LongPressDraggable(
                        modifier = Modifier
                            .zIndex(currentZIndex.floatValue)
                            .pointerInput(item) {
                                detectTapGestures(
                                    onLongPress = {
                                        println("Item long-pressed: $item at index: $index")
                                    }
                                )
                            }
                            .fillMaxSize(),
                        getDataType = 1,
                        item = item,
                        zIndex = currentZIndex
                    ) {
                        DropTarget(
                            getDataType = 0,
                            item = item,
                            modifier = Modifier,
                        ) { isInBound, data ->

                            if (isInBound) {
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                            }

                            data?.let {
                                dragAndDropUpdateList(item, data)
                            }

                            DragTarget(
                                isActive = true,
                                modifier = Modifier,
                                dataToDrop = item
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                        .background(
                                            if (isInBound) {
                                                Color.Green
                                            } else {
                                                Color.Red
                                            }
                                        )
                                ) {
                                    Text(text = "$item", Modifier.padding(), fontSize = 16.sp)
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
@Preview
fun ExampleDragAndDropListPreview() {
    ExampleDragAndDropList()
}