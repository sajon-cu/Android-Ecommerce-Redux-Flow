package com.inweapp.mavericksfundamentals.extensions

import android.content.res.Resources
import androidx.annotation.Dimension

/**
 * Created by sajon on 12/3/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
@Dimension(unit = Dimension.DP)
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

@Dimension(unit = Dimension.PX)
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()