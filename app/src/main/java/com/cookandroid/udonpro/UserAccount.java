package com.cookandroid.udonpro;

// 사용자 계정 정보 모델 클래스
public class UserAccount {

    private String idToken; //Firebase Uid (고유 정보 키값)
    private String eMail;
    private String password;
    private String number;

    //빈생성자 ,파이어베이스 사용할때 필수
    public UserAccount() { }


    //get ,set


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
