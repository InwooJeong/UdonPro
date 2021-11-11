package com.cookandroid.udonpro

// 사용자 계정 정보 모델 클래스
class UserAccount  //빈생성자 ,파이어베이스 사용할때 필수
{
    //get ,set
    var idToken //Firebase Uid (고유 정보 키값)
            : String? = null
    private var eMail: String? = null
    var password: String? = null
    var number: String? = null
    fun geteMail(): String? {
        return eMail
    }

    fun seteMail(eMail: String?) {
        this.eMail = eMail
    }
}