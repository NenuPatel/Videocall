
package com.example.facetime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText edtUsername;
    Button logout,btnProceed;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        logout=findViewById(R.id.btn);
        edtUsername=findViewById(R.id.userid);
        btnProceed=findViewById(R.id.Proceed);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                finish();
            }
        });
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String username=edtUsername.getText().toString().trim();
                 if(!username.isEmpty()){
                     proceedService(username);
                     Intent in=new Intent(HomeActivity.this, explorepage.class);
                     in.putExtra("username",username);
                     startActivity(in);
                 }else{
                     return;
                 }
            }
        });

    }
    void proceedService(String userID){
        Application application =getApplication() ;
        long appID = 910981022;
        String appSign ="be7970af8b6f8833d07dc55f0bc5f5758c5e5c69b830bb4267590064d2dcc62a";
        String userName =userID;

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}