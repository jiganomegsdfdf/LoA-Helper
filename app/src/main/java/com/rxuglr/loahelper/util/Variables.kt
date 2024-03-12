package com.rxuglr.loahelper.util

import android.os.Build
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rxuglr.loahelper.loahApp
import com.topjohnwu.superuser.ShellUtils

object Variables {

    val codenames: Array<String> =
        arrayOf("nabu")
    val nomodem: Array<String> = arrayOf("nabu")
    val sensors: Array<String> = arrayOf("")
    var uefilist: Array<Int> = arrayOf(0)

    val ram: String = RAM().getMemory(loahApp)
    var name: String = ""
    val slot: String = String.format("%S", ShellUtils.fastCmd("getprop ro.boot.slot_suffix")).drop(1)
    val codename: String = Build.DEVICE
    var fontSize: TextUnit = 15.sp
    var paddingValue: Dp = 10.dp
    var lineHeight: TextUnit = 15.sp
    val unsupported: MutableState<Boolean> = mutableStateOf(false)
    fun vars() {
        name = when (codename) {
            codenames[0] -> "Xiaomi Pad 5"
            else -> "Unknown"
        }.toString()
        if ((name == "Unknown")) {
            unsupported.value = true
        }
        if (codename == "nabu") {
            fontSize = 20.sp
            paddingValue = 20.dp
            lineHeight = 20.sp
        }
        if ((ShellUtils.fastCmd("find /mnt/sdcard/UEFI/ -type f  | grep .img").isEmpty())) {
            uefilist += 99
        } else {
            if (ShellUtils.fastCmd("find /mnt/sdcard/UEFI/ -type f  | grep .img | grep 120")
                    .isNotEmpty()
            ) {
                uefilist += 120
            }
            if (ShellUtils.fastCmd("find /mnt/sdcard/UEFI/ -type f  | grep .img | grep 60")
                    .isNotEmpty()
            ) {
                uefilist += 60
            }
            if ((ShellUtils.fastCmd("find /mnt/sdcard/UEFI/ -type f  | grep .img")
                    .isNotEmpty()) and (!uefilist.contains(60)) and !uefilist.contains(120)
            ) {
                uefilist += 1
            }
        }
    }
}
