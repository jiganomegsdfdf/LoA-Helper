package com.rxuglr.loahelper.util

import com.rxuglr.loahelper.util.Variables.codename
import com.rxuglr.loahelper.util.Variables.codenames
import com.rxuglr.loahelper.util.Variables.nomodem
import com.rxuglr.loahelper.util.Variables.sensors
import com.rxuglr.loahelper.util.Variables.uefilist
import com.topjohnwu.superuser.ShellUtils

object Commands {
    fun backup(where: Int) {
        val slot = ShellUtils.fastCmd("getprop ro.boot.slot_suffix")
        if (where == 1) {
            mountlinux()
            ShellUtils.fastCmd("dd if=/dev/block/bootdevice/by-name/boot$slot of=/sdcard/linux/boot.img bs=32MB")
            umountlinux()
        } else if (where == 2) {
            ShellUtils.fastCmd("mkdir /sdcard/helper || true ")
            ShellUtils.fastCmd("dd if=/dev/block/bootdevice/by-name/boot$slot of=/sdcard/helper/boot.img")
        }
    }

    fun displaytype(): Any {
        val panel = ShellUtils.fastCmd("cat /proc/cmdline")
        return when (codename) {
            codenames[0] -> if (panel.contains("k82_42")) {
                "Huaxing"
            } else if (panel.contains("k82_36")) {
                "Tianma"
            } else {
                "Unknown"
            }
            else -> "Unknown"
        }
    }

    fun mountstatus(): Boolean {
        return ShellUtils.fastCmd("su -mm -c mount | grep " + ShellUtils.fastCmd("su -mm -c readlink -fn /dev/block/bootdevice/by-name/linux"))
            .isEmpty()
    }

    fun mountlinux() {
        ShellUtils.fastCmd("su -mm -c mkdir /mnt/sdcard/linux/ || true")
        ShellUtils.fastCmd("su -mm -c mount /dev/block/by-name/linux /sdcard/linux/")
    }

    fun umountlinux() {
        ShellUtils.fastCmd("su -mm -c umount /mnt/sdcard/linux/")
        ShellUtils.fastCmd("su -mm -c rmdir /mnt/sdcard/linux")
    }


    private fun getuefipath(type: Int): String {
        val uefipath = arrayOf("", "", "")
        if (uefilist.contains(120)) {
            uefipath[0] =
                ShellUtils.fastCmd("su -mm -c find /mnt/sdcard/UEFI/ -type f  | grep .img | grep 120")
        }
        if (uefilist.contains(60)) {
            uefipath[1] += ShellUtils.fastCmd("su -mm -c find /mnt/sdcard/UEFI/ -type f  | grep .img | grep 60")
        }
        if (uefilist.contains(1)) {
            uefipath[2] += ShellUtils.fastCmd("su -mm -c find /mnt/sdcard/UEFI/ -type f  | grep .img")
        }
        return uefipath[type]
    }

    fun flashuefi(type: Int) {
        val slot = ShellUtils.fastCmd("getprop ro.boot.slot_suffix")
        ShellUtils.fastCmd("su -mm -c dd if=" + getuefipath(type) + " of=/dev/block/bootdevice/by-name/boot$slot")
    }

    fun quickboot(type: Int) {
        flashuefi(type)
        ShellUtils.fastCmd("svc power reboot")
    }
}
