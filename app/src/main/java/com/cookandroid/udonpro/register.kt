package com.cookandroid.udonpro;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPw, mEtNumber ; //회원가입 입력 필드 , (주소 나중에처리할것)
    private Button mBtnRegister, mBtnMain; //회원가입 입력버튼




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerform);

        //초기회
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("UdonProject");

        mEtEmail = findViewById(R.id.et_email);
        mEtPw = findViewById(R.id.et_pw);
        mEtNumber = findViewById(R.id.et_num);

        mBtnRegister = findViewById(R.id.btn_register);
        mBtnMain = findViewById(R.id.btn_main);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString(); //값을 가저와서, 문자열로 변환
                String strpw = mEtPw.getText().toString();
                //String inNum = mEtNumber.getText().toString();

                //firebaseAutho 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strpw).addOnCompleteListener
                        (register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    //가입이 이루어진뒤 처리
                    if (task.isSuccessful()){
                        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                        UserAccount account = new UserAccount();
                        account.setIdToken(firebaseUser.getUid());//파이어베이스에서 가저옴 (고유)

                        account.seteMail(firebaseUser.getEmail());
                        account.setPassword(strpw);//사용자가 입력한곳에서 가저옴
                        //account.setNumber(firebaseUser.getPhoneNumber());

                        // setValue : database에서 insert 해준
                        mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                        Toast.makeText(register.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(register.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();

                    }

                    }
                });
            }
        });
    }

}
