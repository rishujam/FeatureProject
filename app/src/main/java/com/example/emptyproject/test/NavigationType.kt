package com.example.emptyproject.test

import java.io.Serializable

/*
 * Created by Sudhanshu Kumar on 25/07/24.
 */

sealed class NavigationType {
    data object Refresh : NavigationType()
    data object Replace : NavigationType()
    data object AddToBackStackAndReplace : NavigationType()
    data object AddToBackStackAndAdd : NavigationType()
    data class DestroyBackStack(
        val data: Serializable? = null,
        val deeplink: String? = null
    ) : NavigationType()
    data class CreateNewStack(
        val deeplink: String
    ) : NavigationType()
    data class PopFromStack(
        val deepLink: String? = null
    ) : NavigationType()
}