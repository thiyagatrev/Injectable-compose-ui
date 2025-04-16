package com.thiyaga.inject.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeatureWidgetsProviderModule {

    @Provides
    @Singleton
    fun bindsSubjectFeature(
        installedWidgetsMap: @JvmSuppressWildcards Map<String, @JvmSuppressWildcards FeatureWidgetUI>,


        ): FeatureWidgetsProvider {



        return object : FeatureWidgetsProvider {
            override fun composable(featureWidget: FeatureWidget): @Composable ((FeatureWidget, Modifier) -> Unit) {
                return { widgetConfig, modifier ->



                    ///"text" to textWidgetUI, "button" to ButtonWidgetUI
                    val widget = installedWidgetsMap[featureWidget.identifier]
                    widget?.Content(featureWidget = widgetConfig, modifier = modifier)





                }
            }
        }
    }

}
