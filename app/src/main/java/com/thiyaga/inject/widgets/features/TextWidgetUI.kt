package com.thiyaga.inject.widgets.features

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thiyaga.inject.widgets.FeatureWidget
import com.thiyaga.inject.widgets.FeatureWidgetUI
import com.thiyaga.inject.widgets.WidgetKey
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import javax.inject.Inject


// Data classes for different widget types
@JsonClass(generateAdapter = true)
data class TextWidgetData(val text: String) {
    companion object {
        fun fromJSON(json: String): TextWidgetData {
            val widgetData: TextWidgetData? = try {
                val adapter: JsonAdapter<TextWidgetData> =
                    Moshi.Builder().build().adapter(TextWidgetData::class.java)
                adapter.fromJson(json)
            } catch (e: Exception) {
                Log.e("ImageWidgetData", "Unable to parse WidgetData : $e")
                null
            }
            return widgetData ?: TextWidgetData("")
        }
    }
}

@WidgetKey("text")
class TextWidgetUI @Inject constructor() : FeatureWidgetUI {
    @Composable
    override fun Content(featureWidget: FeatureWidget, modifier: Modifier) {

        val textData = remember(featureWidget.data) {
            TextWidgetData.fromJSON(featureWidget.data)
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            // Displaying the widget information
            Text(
                text = "Text Widget: ${featureWidget.identifier}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            textData?.let { data ->
                Text(text = "Text: ${data.text}")
            }
        }
    }


}

@Composable
private fun TextWidgetScreen(
    modifier: Modifier,
    featureWidget: FeatureWidget,
    textData: TextWidgetData?
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        // Displaying the widget information
        Text(
            text = "Text Widget: ${featureWidget.identifier}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        textData?.let { data ->
            Text(text = "Text: ${data.text}")
        }
    }
}

@Preview
@Composable
private fun PreviewTextWidgetScreen() {


    val featureWidget = FeatureWidget(
        id = "1",
        identifier = "text",
        sortSequence = 1,
        data = """{"text": "Hello from Text Widget"}"""
    )
    val textData = remember(featureWidget.data) {
        TextWidgetData.fromJSON(featureWidget.data)
    }
    TextWidgetScreen(
        modifier = Modifier,
        featureWidget = featureWidget,
        textData = textData
    )
}