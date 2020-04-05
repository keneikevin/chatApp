package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.DATA.Message
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Observer

class MainActivity : AppCompatActivity() {

    private val DEFAULT_MSG_LENGTH_LIMIT = 100

    private var messageViewModel: MessageViewModel? = null

    lateinit var adapter:MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageViewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)

        messageViewModel?.getMessages()?.observe(this, Observer<List<Message>> { this.renderMessges(it) })

        // Enable Send button when there's text to send
        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                sendButton.isEnabled = charSequence.toString().trim { it <= ' ' }.isNotEmpty()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        messageEditText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT))

        // Send button sends a message and clears the EditText
        sendButton.setOnClickListener {
            val message = Message(0,messageEditText.getText().toString())

            messageViewModel?.setMessage(message)
            // Clear input box
            messageEditText.setText("")
        }

    }

    private fun renderMessges(messages: List<Message>?){
        adapter = MessageAdapter(this,messages)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        messageRecyclerView.layoutManager = layoutManager
        messageRecyclerView.adapter = adapter
    }

}
