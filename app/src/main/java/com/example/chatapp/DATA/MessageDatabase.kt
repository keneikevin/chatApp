package com.example.chatapp.DATA

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Message::class],version = 1, exportSchema = false)
abstract class MessageDatabase :RoomDatabase(){
abstract fun messageDao(): MessageDao
    companion object {

        @Volatile
        private var INSTANCE: MessageDatabase? = null

        fun getDatabase(context: Context): MessageDatabase? {
            if (INSTANCE == null) {
                synchronized(MessageDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                MessageDatabase::class.java, "message_database"
                            )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
