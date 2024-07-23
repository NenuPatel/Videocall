package com.example.facetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.InCallService;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collection;
import java.util.Collections;

public class explorepage extends AppCompatActivity {
    EditText  edtUsername;
    ZegoSendCallInvitationButton btnVoiceCall,btnVideoCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorepage);
        edtUsername=findViewById(R.id.callinguser);
        btnVoiceCall=findViewById(R.id.btnvoicecall);
        btnVideoCall=findViewById(R.id.btnvideocall);
        String username=getIntent().getStringExtra("username");

        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               String callingUser=edtUsername.getText().toString().trim();
               VoiceCall(callingUser);
               VideoCall(callingUser);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    void VoiceCall(String callingUser){
        btnVoiceCall.setIsVideoCall(false);
        btnVoiceCall.setResourceID("zego_uikit_call");
        btnVoiceCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(callingUser,callingUser)));
    }
    void VideoCall(String callingUser){
        btnVideoCall.setIsVideoCall(true);
        btnVideoCall.setResourceID("zego_uikit_call");
        btnVideoCall.setInvitees(Collections.singletonList(new ZegoUIKitUser(callingUser,callingUser)));
    }
}