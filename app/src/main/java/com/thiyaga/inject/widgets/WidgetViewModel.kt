package com.thiyaga.inject.widgets

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WidgetViewModel @Inject constructor(
    val provider: FeatureWidgetsProvider
): ViewModel() {


}