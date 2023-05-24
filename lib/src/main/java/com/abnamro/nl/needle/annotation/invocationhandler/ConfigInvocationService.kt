package com.abnamro.nl.needle.annotation.invocationhandler

interface ConfigInvocationService {
    fun getBoolean(key: String): Boolean
    fun getInt(firebaseKey: String): Int
    fun getDouble(firebaseKey: String): Double
    fun getLong(firebaseKey: String): Long
    fun getString(firebaseKey: String): String
}