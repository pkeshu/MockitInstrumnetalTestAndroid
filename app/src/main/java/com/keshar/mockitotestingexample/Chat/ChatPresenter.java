package com.keshar.mockitotestingexample.Chat;

import static com.keshar.mockitotestingexample.Chat.ChatContract.*;

public class ChatPresenter implements ChatContract.Presenter {

    private View chatView;

    public ChatPresenter(ChatContract.View chatView) {
        this.chatView = chatView;
    }

    @Override
    public void sendMessage(String message) {
        if(message!=null && !message.isEmpty()){
            chatView.addMessage(message);
        }
    }

    @Override
    public void messageInputChange(String messageInput) {
        if (messageInput == null || messageInput.isEmpty()) {
            chatView.disabaleSendButton();
        } else {
            chatView.enableSendButton();
        }

    }
}
