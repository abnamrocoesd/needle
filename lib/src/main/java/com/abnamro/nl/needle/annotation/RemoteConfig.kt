package com.abnamro.nl.needle.annotation

/**
 * A method annotated with this represents a value from Firebase RemoteConfig
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class RemoteConfig(val key: String)