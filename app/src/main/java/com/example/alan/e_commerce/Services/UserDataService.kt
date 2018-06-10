package com.example.alan.e_commerce.Services

object UserDataService {
    var id = ""
    var name = ""
    var email = ""

    fun logout() {
        id = ""
        name = ""
        email = ""
        AuthService.authToken = ""
        AuthService.userEmail = ""
        AuthService.isLoggedIn = false
    }

}