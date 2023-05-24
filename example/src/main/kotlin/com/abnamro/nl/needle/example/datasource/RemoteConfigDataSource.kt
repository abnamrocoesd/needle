package com.abnamro.nl.needle.example.datasource

import com.abnamro.nl.needle.annotation.RemoteConfig


/**
 * Declare remoteConfigs in this interface.
 *
 * RemoteConfig params should be annotated @RemoteConfig and it should contain an unique key: [_a-zA-Z0-9]+
 * For example
 *      @get:RemoteConfig("int_config")
 *      val testConfigInt: Int
 */
interface RemoteConfigDataSource {

    @get:RemoteConfig("int_config")
    val testConfigInt: Int

    @RemoteConfig("string_config")
    fun getTestConfigString(): String
}

