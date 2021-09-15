package com.byansanur.awesomeapp.common

import android.content.Context
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.byansanur.awesomeapp.R
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
class SetAppBar (private val context: Context) {


    fun setCollapseAppBar(appBar: AppBarLayout, toolbar: Toolbar, menuList: List<MenuItem>?) {
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                abs(verticalOffset)-appBarLayout.totalScrollRange == 0 -> {
                    // collapse
                    toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.color_secondary))
                }
                else -> {
                    // expand
                    if (menuList != null) {
                        for (i in menuList.indices) {
                            menuList[i].icon.setTint(ContextCompat.getColor(context, R.color.color_secondary))
                        }
                    }
                    toolbar.setTitleTextColor(ContextCompat.getColor(context, android.R.color.transparent))
                }
            }
        })
    }

}