package com.example.phoneauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collection;
import java.util.Collections;

public class explorepage extends AppCompatActivity {
    EditText edtUsername;
    TextView txtuser;
    ZegoSendCallInvitationButton btnVoicecall,btnVideocall;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorepage);
        edtUsername=findViewById(R.id.callinguser);
        btnVideocall=findViewById(R.id.btnvideocall);
        btnVoicecall=findViewById(R.id.btnvoicecall);
        txtuser=findViewById(R.id.txtuser);

        String username=getIntent().getStringExtra("username");

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            String callingUser=edtUsername.getText().toString().trim();
            Voicecall(callingUser);
            Videocall(callingUser);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    void Voicecall(String callingUser){
        btnVoicecall.setIsVideoCall(false);
        btnVoicecall.setResourceID("zego_uikit_call");
        btnVoicecall.setInvitees(Collections.singletonList(new ZegoUIKitUser(callingUser, callingUser)));
    }
    void Videocall(String callingUser){
        btnVideocall.setIsVideoCall(true);
        btnVideocall.setResourceID("zego_uikit_call");
        btnVideocall.setInvitees(Collections.singletonList(new ZegoUIKitUser(callingUser, callingUser)));
    }
}