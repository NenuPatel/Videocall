package com.example.phoneauthentication;

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
 Button logout,btnProceed;
 EditText edtUsername;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();
        logout=findViewById(R.id.logout);
        edtUsername=findViewById(R.id.userid);
        btnProceed=findViewById(R.id.Process);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtUsername.getText().toString().trim();
                if(!username.isEmpty()){
                    proceedService(username);
                    Intent intent=new Intent(HomeActivity.this, explorepage.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }else{
                    return;
                }
            }
        });
    }
    void proceedService(String userID){
        Application application = getApplication();
        long appID = 2016343166;
        String appSign ="ab115b2c28bf6505e2e13c905e30830ec48c2409c8c19a75022ccbe73ee3c642";
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