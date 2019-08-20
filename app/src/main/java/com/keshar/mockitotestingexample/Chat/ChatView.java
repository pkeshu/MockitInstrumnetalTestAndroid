package com.keshar.mockitotestingexample.Chat;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import static com.keshar.mockitotestingexample.Chat.ChatContract.*;

public class ChatView implements View {

    private EditText messageInput;
    private ImageView sendBtn;

    public ChatView(EditText messageInput, ImageView sendBtn) {
        this.messageInput = messageInput;
        this.sendBtn = sendBtn;
    }

    @Override
    public void addMessage(String message) {

    }

    @Override
    public void clearMessageInput() {

    }

    @Override
    public void enableSendButton() {

    }

    @Override
    public void disabaleSendButton() {

    }
}
