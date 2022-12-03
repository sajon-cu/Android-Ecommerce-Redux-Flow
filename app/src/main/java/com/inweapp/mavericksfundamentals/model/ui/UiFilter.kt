package com.inweapp.mavericksfundamentals.model.ui

import com.inweapp.mavericksfundamentals.model.domain.Filter

/**
 * Created by sajon on 12/3/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class UiFilter(val filter: Filter, val isSelected: Boolean = false)