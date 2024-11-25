package com.example.relojdevida

object DataManager {

    val birthdayList = mutableListOf<Birthday>()

    data class Birthday(val name: String, val date: String, val socialLink: String)
}