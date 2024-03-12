package com.rxuglr.loahelper.qstiles

import android.service.quicksettings.Tile.STATE_ACTIVE
import android.service.quicksettings.Tile.STATE_UNAVAILABLE
import android.service.quicksettings.TileService
import com.rxuglr.loahelper.R
import com.rxuglr.loahelper.util.Commands.quickboot
import com.rxuglr.loahelper.util.Variables.uefilist
import com.rxuglr.loahelper.loahApp

class QuickBootTile : TileService() {

    override fun onTileAdded() {
        super.onTileAdded()
        if (uefilist.contains(99)) {
            qsTile.state = STATE_UNAVAILABLE
            qsTile.subtitle = loahApp.getString(
                R.string.uefi_not_found_qs
            )
            qsTile.updateTile()
        } else {
            qsTile.state = STATE_ACTIVE; qsTile.subtitle = ""
        }
    }

    override fun onStartListening() {
        super.onStartListening()
        if (uefilist.contains(99)) {
            qsTile.state = STATE_UNAVAILABLE
            qsTile.subtitle = loahApp.getString(
                R.string.uefi_not_found_qs
            )
            qsTile.updateTile()
        } else {
            qsTile.state = STATE_ACTIVE; qsTile.subtitle = ""
        }
    }

    override fun onStopListening() {
        super.onStopListening()
        if (uefilist.contains(99)) {
            qsTile.state = STATE_UNAVAILABLE
            qsTile.subtitle = loahApp.getString(
                R.string.uefi_not_found_qs
            )
            qsTile.updateTile()
        } else {
            qsTile.state = STATE_ACTIVE; qsTile.subtitle = ""
        }
    }

    override fun onClick() {
        super.onClick()
        if (!uefilist.contains(99)) {
            if (uefilist.contains(120)) {
                quickboot(0)
            } else if (uefilist.contains(60)) {
                quickboot(1)
            } else if (uefilist.contains(1)) {
                quickboot(2)
            }
        } else {
            qsTile.state = STATE_UNAVAILABLE
            qsTile.subtitle = loahApp.getString(
                R.string.uefi_not_found_qs
            )
            qsTile.updateTile()
        }
    }

}
