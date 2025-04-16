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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thiyaga.inject.mainscreen.MainViewModel
import com.thiyaga.inject.widgets.FeatureWidget
import com.thiyaga.inject.widgets.FeatureWidgetUI
import com.thiyaga.inject.widgets.WidgetKey
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import javax.inject.Inject


@JsonClass(generateAdapter = true)
data class ButtonWidgetData(val label: String, val action: String) {

    companion object {
        fun fromJSON(json: String): ButtonWidgetData {
            val widgetData: ButtonWidgetData? = try {
                val adapter: JsonAdapter<ButtonWidgetData> =
                    Moshi.Builder().build().adapter(ButtonWidgetData::class.java)
                adapter.fromJson(json)
            } catch (e: Exception) {
                Log.e("ButtonWidgetData", "Unable to parse WidgetData : $e")
                null
            }
            return widgetData ?: ButtonWidgetData("", "")
        }
    }

}

@WidgetKey("button")
class ButtonWidgetUI @Inject constructor() : FeatureWidgetUI {
    @Composable
    override fun Content(featureWidget: FeatureWidget, modifier: Modifier) {


        val buttonData = remember(featureWidget.data) {
            ButtonWidgetData.fromJSON(featureWidget.data)
        }
        ButtonWidgetScreen(
            modifier = modifier,
            featureWidget = featureWidget,
            buttonData = buttonData
        )

    }
}

@Composable
private fun ButtonWidgetScreen(
    modifier: Modifier = Modifier,
    featureWidget: FeatureWidget,
    buttonData: ButtonWidgetData?,
    viewModel: MainViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Blue)
            .padding(16.dp)
    ) {
        // Displaying the widget information
        Text(
            text = "Button Widget: ${featureWidget.identifier}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        buttonData?.let { data ->
            Text(text = "Label: ${data.label}", color = Color.White)
            Text(text = "Action: ${data.action}", color = Color.White)
        }
    }
}


@Preview
@Composable
private fun PreviewButtonWidgetScreen() {

    val featureWidget = FeatureWidget(
        identifier = "button",
        sortSequence = 1,
        data = """{"label": "Click Me", "action": "open_url"}"""
    )
    val buttonData = remember(featureWidget.data) {
        ButtonWidgetData.fromJSON(featureWidget.data)
    }
    ButtonWidgetScreen(Modifier, featureWidget, buttonData)
}

