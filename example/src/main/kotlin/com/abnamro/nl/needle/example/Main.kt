import com.abnamro.nl.needle.annotation.creator.ConfigDataSourceCreator
import com.abnamro.nl.needle.annotation.invocationhandler.ConfigInvocationHandler
import com.abnamro.nl.needle.annotation.invocationhandler.ConfigInvocationService
import com.abnamro.nl.needle.example.datasource.RemoteConfigDataSource

fun main() {
    val handler = ConfigInvocationHandler(object : ConfigInvocationService{
        override fun getBoolean(key: String): Boolean {
            //Use firebase firebaseRemoteConfig.getBoolean(key)
            return true
        }

        override fun getDouble(firebaseKey: String): Double {
            //use firebaseRemoteConfig.getDouble(firebaseKey)
            return 10.0
        }

        override fun getInt(firebaseKey: String): Int {
            //firebaseRemoteConfig.getLong(firebaseKey)
            return 99
        }

        override fun getLong(firebaseKey: String): Long {
            //firebaseRemoteConfig.getLong(firebaseKey)
            return 99L
        }

        override fun getString(firebaseKey: String): String {
            //firebaseRemoteConfig.getString(firebaseKey)
            return "value"
        }

    })
    val remoteConfigDataSource: RemoteConfigDataSource = ConfigDataSourceCreator(handler).create(RemoteConfigDataSource::class.java)
    println("Hello World")

    println("int config: ${remoteConfigDataSource.testConfigInt}")
    println("string config: ${remoteConfigDataSource.getTestConfigString()}")
}