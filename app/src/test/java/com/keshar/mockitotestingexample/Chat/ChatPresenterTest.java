package com.keshar.mockitotestingexample.Chat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.keshar.mockitotestingexample.Chat.ChatContract.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class ChatPresenterTest {
    public static final String MESSAGE = "hello keshar";
    private ChatContract.Presenter chatPresenter;

    @Mock
    private ChatContract.View chatView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        chatPresenter = new ChatPresenter(chatView);
    }

    @Test
    public void sendMessage_NullInput_NoMessageSend() {
        chatPresenter.sendMessage(null);
        verify(chatView, never()).addMessage(anyString());

    }

    @Test
    public void sendMessage_EmptyInput_NoMessageSend() {
        chatPresenter.sendMessage("");
        verify(chatView, never()).addMessage(anyString());
    }

    @Test
    public void sendMessage_NormalStringInput_MessageSend() {
        chatPresenter.sendMessage(MESSAGE);
        verify(chatView).addMessage(MESSAGE);
    }

    @Test
    public void messageInputChange_NullString_SendButtonDisable() {
        chatPresenter.messageInputChange(null);
        verify(chatView).disabaleSendButton();
    }

    @Test
    public void messageInputChange_EmptyString_SendButtonDisable() {
        chatPresenter.messageInputChange("");
        verify(chatView).disabaleSendButton();
    }

    @Test
    public void messageInputChange_NormalString_SendButtonEnable() {
        chatPresenter.messageInputChange(MESSAGE);
        verify(chatView).enableSendButton();

    }
}
