package com.joke.mock.mock;


public class d_MemberManager {
    private d_Member userName;
    private d_Member userId;

    public d_MemberManager(d_Member userName, d_Member userId) {
        this.userName = userName;
        this.userId = userId;
        System.out.println("Tow Members Mock userId" + this.userId.hashCode());

        System.out.println("Tow Members Mock userName" + this.userName.hashCode());

        System.out.println("Tow Members Constructor  " );
    }


    public d_MemberManager(d_Member userName) {
        this.userName = userName;
        System.out.println("One Members Mock userId" + this.userName.hashCode());

        System.out.println("One Members Constructor  " );
    }




    public void generate() {
        userName.haha();
    }
}
