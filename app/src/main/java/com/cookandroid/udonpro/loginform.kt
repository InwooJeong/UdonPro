package com.cookandroid.udonpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginform extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPw, mEtNumber ; //로그인 입력필

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UdonProject");

        mEtEmail = findViewById(R.id.et_email1);
        mEtPw = findViewById(R.id.et_pwd);



        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 요청
                String strEmail = mEtEmail.getText().toString(); //값을 가저와서, 문자열로 변환
                String strpw = mEtPw.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strpw).addOnCompleteListener
                        (loginform.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    //로그인 성공
                                    Intent intent = new Intent(loginform.this, MainActivity.class);
                                    startActivity(intent);
                                    finish(); // 현재 로그인 엑티비티 제거
                                }else {
                                    Toast.makeText(loginform.this,"로그인 실패 ..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        Button btn_regi = findViewById(R.id.btn_regi);

        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //회원가입 화면이동한다
                Intent intent = new Intent(loginform.this, register.class);
                startActivity(intent);
            }
        });
    }
}
