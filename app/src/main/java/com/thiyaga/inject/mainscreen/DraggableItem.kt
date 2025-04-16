package com.thiyaga.inject.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class DraggableItem(val id: Int, val text: String)

@Composable
fun DraggableLazyColumn() {
    val items = remember {
        mutableStateListOf(
            DraggableItem(1, "Item 1"),
            DraggableItem(2, "Item 2"),
            DraggableItem(3, "Item 3"),
            DraggableItem(4, "Item 4"),
            DraggableItem(5, "Item 5"),
            DraggableItem(6, "Item 6"),
            DraggableItem(7, "Item 7"),
            DraggableItem(8, "Item 8"),
            DraggableItem(9, "Item 9"),
            DraggableItem(10, "Item 10"),
        )
    }
    val listState = rememberLazyListState()
    var currentlyDragging by remember { mutableStateOf<DraggableItem?>(null) }
    var currentOffset by remember { mutableStateOf(0f) }

    LazyColumn(state = listState) {
        items(items = items, key = { it.id }) { item ->
            val isDragging = currentlyDragging == item
            val offset = if (isDragging) currentOffset.dp else 0.dp

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(if (isDragging) Color.LightGray.copy(alpha = 0.8f) else Color.White)
                    .offset(y = offset)
                    .pointerInput(item.id) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = { offset ->
                                currentlyDragging = item
                                currentOffset = offset.y
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                currentOffset += dragAmount.y

                                val draggedItemIndex = items.indexOf(currentlyDragging)
                                val currentItemCenter =
                                    (draggedItemIndex * 72.dp.toPx() + 36.dp.toPx() + currentOffset) // Approximate center

                                listState.layoutInfo.visibleItemsInfo.forEach { visibleItem ->
                                    if (visibleItem.index != draggedItemIndex) {
                                        val itemStart = visibleItem.offset
                                        val itemEnd = visibleItem.offset + visibleItem.size
                                        if (currentItemCenter in itemStart.toFloat()..itemEnd.toFloat()) {
                                            val targetIndex = visibleItem.index
                                            if (draggedItemIndex < targetIndex) {
                                                items.add(
                                                    targetIndex + 1,
                                                    items.removeAt(draggedItemIndex)
                                                )
                                            } else if (draggedItemIndex > targetIndex) {
                                                items.add(
                                                    targetIndex,
                                                    items.removeAt(draggedItemIndex)
                                                )
                                            }
                                            return@forEach
                                        }
                                    }
                                }
                            },
                            onDragEnd = {
                                currentlyDragging = null
                                currentOffset = 0f
                            },
                            onDragCancel = {
                                currentlyDragging = null
                                currentOffset = 0f
                            }
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.text)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Menu, contentDescription = "Drag Handle")
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PreviewDraggableLazyColumn() {
    DraggableLazyColumn()
}