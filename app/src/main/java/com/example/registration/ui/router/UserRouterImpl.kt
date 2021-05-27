package com.example.registration.ui.router

import com.example.registration.presentation.UserRouter
import com.example.registration.ui.getMainPageScreen
import com.github.terrakok.cicerone.Router

class UserRouterImpl(
    private val router: Router
) : UserRouter {

    override fun openMainPage() {
        router.navigateTo(getMainPageScreen())
    }
}