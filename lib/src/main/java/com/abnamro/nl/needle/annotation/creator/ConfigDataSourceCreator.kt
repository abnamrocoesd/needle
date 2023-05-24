package com.abnamro.nl.needle.annotation.creator

import com.abnamro.nl.needle.annotation.invocationhandler.ConfigInvocationHandler
import java.lang.reflect.Proxy

/**
 * Creates a proxy for ConfigDataSource
 */
@Suppress("UNCHECKED_CAST")
class ConfigDataSourceCreator(private val configInvocationHandler: ConfigInvocationHandler) {
    fun <T> create(service: Class<T>): T {
        validateServiceInterface(service)
        return Proxy.newProxyInstance(service.classLoader, arrayOf<Class<*>>(service), configInvocationHandler) as T
    }

    private fun <T> validateServiceInterface(service: Class<T>) {
        require(service.isInterface) { "API declarations must be interfaces." }
        require(service.interfaces.isEmpty()) { "API interfaces must not extend other interfaces." }
    }
}