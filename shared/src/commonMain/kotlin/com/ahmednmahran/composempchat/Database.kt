package com.ahmednmahran.composempchat

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.ahmednmahran.composempchat.chat.data.model.User
import okio.Path.Companion.toPath

class Database {
    companion object {
        var user: User? = null
    }

}

fun testDataStore() {

}

// shared/src/androidMain/kotlin/createDataStore.kt

/**
 * Gets the singleton DataStore instance, creating it if necessary.
 */
fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )



internal const val dataStoreFileName = "dice.preferences_pb"
