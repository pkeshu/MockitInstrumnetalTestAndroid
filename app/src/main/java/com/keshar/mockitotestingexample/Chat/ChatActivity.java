package com.keshar.mockitotestingexample.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.keshar.mockitotestingexample.R;

import java.util.List;

import static com.keshar.mockitotestingexample.Chat.ChatContract.*;

public class ChatActivity extends AppCompatActivity implements View {


    private Presenter presenter;
    private MessageAdapter messageAdapter;
    private List<String> listOfMessages;
    private EditText messageInput;
    private ImageView sendBtn;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageInput = findViewById(R.id.input_message);
        sendBtn = findViewById(R.id.send_btn);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.messageInputChange(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sendBtn.setOnClickListener(v -> presenter.sendMessage(messageInput.getText().toString()));
    }

    @Override
    public void addMessage(String message) {
        if (message != null && !message.isEmpty()) {
            listOfMessages.add(message);
            messageAdapter.notifyItemInserted(listOfMessages.size());
        }
    }

    @Override
    public void clearMessageInput() {
        messageInput.setText("");

    }

    @Override
    public void enableSendButton() {
        if (!sendBtn.isEnabled()) {
            sendBtn.setEnabled(true);
            sendBtn.setAlpha(1.0f);

        }

    }

    @Override
    public void disabaleSendButton() {

        if(sendBtn.isEnabled()){
            sendBtn.setEnabled(false);
            sendBtn.setAlpha(0.5f);
        }
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public MessageAdapter getMessageAdapter() {
        return messageAdapter;
    }

    public void setMessageAdapter(MessageAdapter messageAdapter) {
        this.messageAdapter = messageAdapter;
    }

    public List<String> getListOfMessages() {
        return listOfMessages;
    }

    public void setListOfMessages(List<String> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }
}
