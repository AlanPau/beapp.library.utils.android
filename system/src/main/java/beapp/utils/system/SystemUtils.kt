package beapp.utils.system

import android.content.Context
import android.os.Build

fun isSdk26OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isSdk27OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

fun isSdk28OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

fun isSdk29OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

fun isSdk30OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

fun isSdk31OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

fun isSdk33OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

fun isSdk34OrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE