package com.preet.kotlintemplate.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

object Navigator {

    inline fun <reified T : Activity> goTo(
        context: Context,
        finishCurrent: Boolean = false
    ) {
        val intent = Intent(context, T::class.java)
        context.startActivity(intent)

        if (finishCurrent && context is Activity) {
            context.finish()
        }
    }

    inline fun <reified T : Activity> goToAndClearStack(context: Context) {
        val intent = Intent(context, T::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }



    // 🔹 Basic Navigation
    inline fun <reified T : Activity> goTo(
        context: Context,
        bundle: Bundle? = null,
        finishCurrent: Boolean = false,
        clearStack: Boolean = false
    ) {
        val intent = Intent(context, T::class.java)

        bundle?.let {
            intent.putExtras(it)
        }

        if (clearStack) {
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        context.startActivity(intent)

        if (finishCurrent && context is Activity) {
            context.finish()
        }
    }

    // 🔹 Pass Single String (like userId)
    inline fun <reified T : Activity> goToWithString(
        context: Context,
        key: String,
        value: String,
        finishCurrent: Boolean = false
    ) {
        val bundle = Bundle().apply {
            putString(key, value)
        }

        goTo<T>(context, bundle, finishCurrent)
    }

    inline fun <reified T : Activity> goToWithData(
        context: Context,
        finishCurrent: Boolean = false,
        clearStack: Boolean = false,
        vararg data: Pair<String, Any?>
    ) {

        val intent = Intent(context, T::class.java)

        data.forEach { pair ->

            when (pair.second) {
                is String -> intent.putExtra(pair.first, pair.second as String)
                is Int -> intent.putExtra(pair.first, pair.second as Int)
                is Boolean -> intent.putExtra(pair.first, pair.second as Boolean)
                is Parcelable -> intent.putExtra(pair.first, pair.second as Parcelable)
                is Long -> intent.putExtra(pair.first, pair.second as Long)
            }
        }

        if (clearStack) {
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        context.startActivity(intent)

        if (finishCurrent && context is Activity) {
            context.finish()
        }
    }

    // 🔹 Pass Parcelable Object
    inline fun <reified T : Activity> goToWithParcelable(
        context: Context,
        key: String,
        value: Parcelable,
        finishCurrent: Boolean = false
    ) {
        val bundle = Bundle().apply {
            putParcelable(key, value)
        }

        goTo<T>(context, bundle, finishCurrent)
    }
}