package com.example.chatapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.chatapp.DATA.Message
import com.example.chatapp.DATA.MessageRepository

class MessageViewModel(application: Application): AndroidViewModel(application) {
    private var repository:MessageRepository= MessageRepository(application)
    fun getMessages()=repository.getMessages()
    fun setMessage(message: Message) {repository.setMessage(message)}
}