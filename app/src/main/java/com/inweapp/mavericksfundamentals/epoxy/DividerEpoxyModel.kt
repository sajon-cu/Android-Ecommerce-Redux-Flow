package com.inweapp.mavericksfundamentals.epoxy

import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.Dimension
import androidx.annotation.Dimension.PX
import androidx.core.view.updateLayoutParams
import com.inweapp.mavericksfundamentals.R
import com.inweapp.mavericksfundamentals.databinding.EpoxyModelVerticalDividerBinding

/**
 * Created by sajon on 12/4/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class DividerEpoxyModel(
    @Dimension(unit = PX) private val horizontalMargin: Int = 0,
    @Dimension(unit = PX) private val verticalMargin: Int = 0,
): ViewBindingKotlinModel<EpoxyModelVerticalDividerBinding>(R.layout.epoxy_model_vertical_divider) {
    override fun EpoxyModelVerticalDividerBinding.bind() {
        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin)
        }
    }
}