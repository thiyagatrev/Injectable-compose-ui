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


@JsonClass(generateAdapter = true)
data class ImageWidgetData(val url: String) {
    companion object {
        fun fromJSON(json: String): ImageWidgetData {
            val widgetData: ImageWidgetData? = try {
                val adapter: JsonAdapter<ImageWidgetData> =
                    Moshi.Builder().build().adapter(ImageWidgetData::class.java)
                adapter.fromJson(json)
            } catch (e: Exception) {
                Log.e("ImageWidgetData", "Unable to parse WidgetData : $e")
                null
            }
            return widgetData ?: ImageWidgetData("")
        }
    }
}

@WidgetKey("image")
class ImageWidgetUI @Inject constructor() : FeatureWidgetUI {
    @Composable
    override fun Content(featureWidget: FeatureWidget, modifier: Modifier) {

        val imageData = remember(featureWidget.data) {
            ImageWidgetData.fromJSON(featureWidget.data)
        }

        ImageWidgetScreen(modifier = modifier, featureWidget = featureWidget, imageData = imageData)

    }

}

@Composable
private fun ImageWidgetScreen(
    modifier: Modifier,
    featureWidget: FeatureWidget,
    imageData: ImageWidgetData
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Green)
            .padding(16.dp)
    ) {
        // Displaying the widget information
        Text(
            text = "Image Widget: ${featureWidget.identifier}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        imageData?.let { data ->
            Text(text = "Url: ${data.url}")
        }
    }
}

@Preview
@Composable
private fun PreviewImageWidgetScreen() {

    val featureWidget = FeatureWidget(
        id = "2",
        identifier = "image",
        sortSequence = 2,
        data = """{"url": "https://example.com/image.jpg"}"""
    )
    val imageData = remember(featureWidget.data) {
        ImageWidgetData.fromJSON(featureWidget.data)
    }
    ImageWidgetScreen(modifier = Modifier, featureWidget = featureWidget, imageData = imageData)
}
