package com.thiyaga.inject.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dagger.MapKey


@MapKey
annotation class WidgetKey(val value: String)

@Composable
fun FeatureWidgetRender(
    featureWidget: FeatureWidget,
    modifier: Modifier = Modifier,
    viewModel: WidgetViewModel = viewModel()

) {
    val featureComposable = remember(featureWidget.identifier) {
        viewModel.provider.composable(featureWidget)
    }
    featureComposable?.invoke(featureWidget, modifier)
}



interface FeatureWidgetsProvider {
    fun composable(featureWidget: FeatureWidget): (@Composable (FeatureWidget, Modifier) -> Unit)?
}

interface FeatureWidgetUI {
    @Composable
    fun Content(featureWidget: FeatureWidget, modifier: Modifier)
}

sealed class BaseWidget {
    open val id: String = ""
    open val identifier: String = ""
    abstract val sortSequence: Int
}


@JsonClass(generateAdapter = true)
data class FeatureWidget(

    @Json(name = "id")
    override val id: String = "",

    @Json(name = "identifier")
    override val identifier: String,

    @Json(name = "sort_sequence")
    override val sortSequence: Int,

    @Json(name = "data")
    val data: String = "",

) : BaseWidget()