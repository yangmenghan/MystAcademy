package com.mystacademy.android.flowController

import android.content.Context
import android.content.Intent
import com.mystacademy.android.MainActivity

class FlowControllerImpl : FlowController {
    override fun routeToMain(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}