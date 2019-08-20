package com.keshar.mockitotestingexample.Chat;

import android.widget.EditText;
import android.widget.ImageView;

import com.keshar.mockitotestingexample.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ChatActivityTest {
    public static final String SOME_TEXT = "Some Text";
    public static final String MESSAGE = "Hello keshar";
    private EditText inputMessage;
    private ImageView sendBtn;
    private ChatActivity activity;
    @Mock
    private MessageAdapter messageAdapter;
    @Mock
    private List<String> listOfMessage;
    @Mock
    ChatPresenter chatPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.setupActivity(ChatActivity.class);
        inputMessage = activity.findViewById(R.id.input_message);
        sendBtn = activity.findViewById(R.id.send_btn);
        activity.setMessageAdapter(messageAdapter);
        activity.setListOfMessages(listOfMessage);
        activity.setPresenter(chatPresenter);
        when(listOfMessage.size()).thenReturn(0);
    }

    @Test
    public void clearMessageInput_WasInput_ClearedMessageInput() {
        activity.clearMessageInput();
        assertThat(inputMessage.getText().toString(), is(""));
    }

    @Test
    public void clearMessageInput_WasntInput_ClearedMessageInput() {
        inputMessage.setText(SOME_TEXT);
        activity.clearMessageInput();
        assertThat(inputMessage.getText().toString(), is(""));
    }

    @Test
    public void enabledSendButton_WasDisabled_EnabeleButton() {
        presetSendButtonAsDisable();
        activity.enableSendButton();
        assertThatSendBtnIsEnabled();
    }

    @Test
    public void enabledSendButton_WasEnabled_EnabledButton() {
        activity.enableSendButton();
        assertThatSendBtnIsEnabled();
    }

    @Test
    public void DisableSendButton_WasEnabled_DisableButton() {
        activity.disabaleSendButton();
        assertThatSendBtnIsDisable();
    }

    @Test
    public void DisableSendButton_WasDisable_DisableButton() {
        presetSendButtonAsDisable();
        activity.disabaleSendButton();
        assertThatSendBtnIsDisable();
    }

    @Test
    public void addMessage_NullString_NothingAdded() {
        activity.addMessage(null);
        verify(listOfMessage, never()).add(anyString());
        verify(messageAdapter, never()).notifyDataSetChanged();
        verify(messageAdapter, never()).notifyItemInserted(anyInt());

    }

    @Test
    public void addMessage_EmptyString_NothingAdded() {
        activity.addMessage("");
        verify(listOfMessage, never()).add(anyString());
        verify(messageAdapter, never()).notifyDataSetChanged();
        verify(messageAdapter, never()).notifyItemInserted(anyInt());

    }

    @Test
    public void addMesssage_NormalStringToEmptyList_MessageAddedToList() {
        activity.addMessage(MESSAGE);
        verify(listOfMessage).add(MESSAGE);
        verify(messageAdapter).notifyItemInserted(0);
    }

    @Test
    public void addMesssage_NormalStringToNotEmptyList_MessageAddedToList() {
        when(listOfMessage.size()).thenReturn(5);
        activity.addMessage(MESSAGE);
        verify(listOfMessage).add(MESSAGE);
        verify(messageAdapter).notifyItemInserted(5);
    }

    private void assertThatSendBtnIsDisable() {
        assertThat(sendBtn.getAlpha(), is(0.5f));
        assertThat(sendBtn.isEnabled(),is(false));
    }

    private void assertThatSendBtnIsEnabled() {
        assertThat(sendBtn.getAlpha(), is(1.0f));
        assertThat(sendBtn.isEnabled(), is(true));
    }

    private void presetSendButtonAsDisable() {
        sendBtn.setEnabled(false);
        sendBtn.setAlpha(0.5f);
    }

    //Test Listerner

    @Test
    public void typeSomeTest_PassedToPresenter(){
        inputMessage.setText(MESSAGE);
        verify(chatPresenter).messageInputChange(MESSAGE);
    }
    @Test
    public void pressedSendButton_EmptyInput_PassedEmptyString(){
        sendBtn.performClick();
        verify(chatPresenter).sendMessage("");
    }
    @Test
    public void pressedSendButton_NormalInputString_PassedNormalString(){
        inputMessage.setText(MESSAGE);
        sendBtn.performClick();
        verify(chatPresenter).sendMessage(MESSAGE);
    }
}
