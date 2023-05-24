package com.abnamro.nl.needle.annotation.invocationhandler

import com.abnamro.nl.needle.annotation.RemoteConfig
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class ConfigInvocationHandler(
    private val configInvocationService: ConfigInvocationService
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
        if (method.declaringClass == Any::class.java) {
            // If the method is a method from Object then defer to normal invocation.
            return method.invoke(this, args)
        }
        val returnValue = when {
            isRemoteConfig(method) -> handleRemoteConfig(method)
            else -> method.invoke(this, args)
        }

        return returnValue
    }

    private fun getKey(method: Method) =
        (method.annotations.firstOrNull { it is RemoteConfig } as RemoteConfig?)?.key ?: throw IllegalArgumentException("RemoteConfig expected")

    private fun handleRemoteConfig(method: Method): Any {
        val firebaseKey = getKey(method)

        return when (method.returnType) {
            Boolean::class.java -> configInvocationService.getBoolean(firebaseKey)
            Int::class.java -> configInvocationService.getInt(firebaseKey)
            Double::class.java -> configInvocationService.getDouble(firebaseKey)
            Long::class.java -> configInvocationService.getLong(firebaseKey)
            else -> configInvocationService.getString(firebaseKey)
        }
    }

    private fun isRemoteConfig(method: Method) = method.isAnnotationPresent(RemoteConfig::class.java)

}