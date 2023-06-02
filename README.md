# Needle
A small library for (remote) configuration, such as Firebase.

## Getting started

Add needle dependency by adding needle.jar to your project.

## Usage
Annotations on the interface methods indicates how a function will be handled.

For example:
```
interface RemoteConfigDataSource {

    @get:RemoteConfig("int_config")
    val testConfigInt: Int

    @RemoteConfig("string_config")
    fun getTestConfigString(): String
}
```

Create `RemoteConfigDataSource` implementation by using `ConfigDataSourceCreator`

```
val handler = ConfigInvocationHandler(object : ConfigInvocationService{
    override fun getBoolean(key: String): Boolean = firebaseRemoteConfig.getBoolean(key)
    override fun getDouble(firebaseKey: String): Double = firebaseRemoteConfig.getDouble(firebaseKey)
    override fun getInt(firebaseKey: String): Int = firebaseRemoteConfig.getLong(firebaseKey)
    override fun getLong(firebaseKey: String): Long = firebaseRemoteConfig.getLong(firebaseKey)
    override fun getString(firebaseKey: String): String = firebaseRemoteConfig.getString(firebaseKey)

})
val remoteConfigDataSource: RemoteConfigDataSource = ConfigDataSourceCreator(handler).create(RemoteConfigDataSource::class.java)

```
Now you will be able to use `remoteConfigDataSource` to get the values
```
val testConfigInt = remoteConfigDataSource.testConfigInt
```

