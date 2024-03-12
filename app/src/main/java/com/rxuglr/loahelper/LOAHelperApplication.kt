package com.rxuglr.loahelper

import android.app.Application
import com.rxuglr.loahelper.util.Variables.vars
import com.topjohnwu.superuser.Shell

lateinit var loahApp: LOAHelperApplication


class LOAHelperApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        loahApp = this
        Shell.setDefaultBuilder(
            Shell.Builder.create().setFlags(Shell.FLAG_REDIRECT_STDERR).setTimeout(10)
        )
        vars()
    }
}