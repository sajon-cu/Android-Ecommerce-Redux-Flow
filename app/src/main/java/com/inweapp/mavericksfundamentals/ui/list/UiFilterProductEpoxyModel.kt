package com.inweapp.mavericksfundamentals.ui.list

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import com.inweapp.mavericksfundamentals.R
import com.inweapp.mavericksfundamentals.databinding.EpoxyModelFilterProductItemBinding
import com.inweapp.mavericksfundamentals.epoxy.ViewBindingKotlinModel
import com.inweapp.mavericksfundamentals.model.domain.Filter
import com.inweapp.mavericksfundamentals.model.ui.UiFilter

/**
 * Created by sajon on 11/18/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class UiFilterProductEpoxyModel(
    val uiFilter: UiFilter,
    val onFilterSelected: (Filter) -> Unit
): ViewBindingKotlinModel<EpoxyModelFilterProductItemBinding>(R.layout.epoxy_model_filter_product_item) {
    @SuppressLint("SetTextI18n")
    override fun EpoxyModelFilterProductItemBinding.bind() {
        root.setOnClickListener { onFilterSelected(uiFilter.filter) }
        filterNameTextView.text = uiFilter.filter.displayText

        val cardBackgroundColorResId = if(uiFilter.isSelected) {
            R.color.purple_500
        } else {
            R.color.purple_200
        }

        cardView.setCardBackgroundColor(ContextCompat.getColor(root.context, cardBackgroundColorResId))
    }
}