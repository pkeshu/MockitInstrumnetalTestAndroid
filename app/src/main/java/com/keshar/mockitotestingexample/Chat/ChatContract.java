package com.keshar.mockitotestingexample.Chat;

public interface ChatContract {
    interface View {
        void addMessage(String message);

        void clearMessageInput();

        void enableSendButton();

        void disabaleSendButton();

    }

    interface Presenter {
        void sendMessage(String message);

        void messageInputChange(String messageInput);
    }
}
