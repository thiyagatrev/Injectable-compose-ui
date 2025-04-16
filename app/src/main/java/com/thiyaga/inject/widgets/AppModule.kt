package com.thiyaga.inject.widgets

import com.thiyaga.inject.widgets.features.ButtonWidgetUI
import com.thiyaga.inject.widgets.features.ImageWidgetUI
import com.thiyaga.inject.widgets.features.TextWidgetUI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    @IntoMap
    @WidgetKey("text")
    fun provideTextWidgetUI(textWidgetUI: TextWidgetUI): FeatureWidgetUI {
        return textWidgetUI
    }

    @Provides
    @IntoMap
    @WidgetKey("image")
    fun provideImageWidgetUI(imageWidgetUI: ImageWidgetUI): FeatureWidgetUI {
        return imageWidgetUI
    }

    @Provides
    @IntoMap
    @WidgetKey("button")
    fun provideButtonWidgetUI(buttonWidgetUI: ButtonWidgetUI): FeatureWidgetUI {
        return buttonWidgetUI
    }

}