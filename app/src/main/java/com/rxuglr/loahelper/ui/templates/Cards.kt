package com.rxuglr.loahelper.ui.templates

import android.content.Intent
import android.net.Uri
import android.util.DisplayMetrics
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rxuglr.loahelper.R
import com.rxuglr.loahelper.util.Commands.displaytype
import com.rxuglr.loahelper.util.Variables.codename
import com.rxuglr.loahelper.util.Variables.codenames
import com.rxuglr.loahelper.util.Variables.fontSize
import com.rxuglr.loahelper.util.Variables.lineHeight
import com.rxuglr.loahelper.util.Variables.name
import com.rxuglr.loahelper.util.Variables.ram
import com.rxuglr.loahelper.util.Variables.slot
import com.rxuglr.loahelper.loahApp

object Cards {

    fun pxtodp(px: Float): Float {
        return px / (loahApp.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    @Composable
    fun InfoCard(modifier: Modifier) {
        Card(
            modifier =
            if (codename == "nabu") {
                modifier
            } else {
                Modifier
                    .height(200.dp)
            },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    12.dp
                )
            )
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    text = "Ubuntu on ARM",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = fontSize,
                    lineHeight = lineHeight
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    text = name,
                    fontSize = fontSize,
                    lineHeight = lineHeight
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    text = LocalContext.current.getString(R.string.ramvalue, ram),
                    fontSize = fontSize,
                    lineHeight = lineHeight
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    text = LocalContext.current.getString(R.string.paneltype, displaytype()),
                    fontSize = fontSize,
                    lineHeight = lineHeight
                )
                if (slot.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        text = LocalContext.current.getString(R.string.slot, slot),
                        fontSize = fontSize,
                        lineHeight = lineHeight
                    )
                }
            }
        }
    }
}
