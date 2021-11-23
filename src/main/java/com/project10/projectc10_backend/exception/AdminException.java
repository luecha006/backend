package com.project10.projectc10_backend.exception;


public class AdminException extends BaseException { //จัดการเกี่ยวกับ error ต่างๆของ user

    public AdminException(String code) {
        super("Admin." + code);
    }

    public static AdminException notFound() { //หาไม่เจอ
        return new AdminException("not.fount");
    }

    public static AdminException requestNull() {
        return new AdminException("register.request.null");
    }

    public static AdminException emailNull() {
        return new AdminException("register.email.null");
    }

    //CREATE

    public static AdminException createDateNull() {
        return new AdminException("create.Date.null");
    }

    public static AdminException createTimeNull() {
        return new AdminException("create.time.null");
    }

    public static AdminException createUsernameNull() {
        return new AdminException("create.username.null");
    }

    public static AdminException createUsernameDuplicated() { //แจ้ง error Username ซ้ำ
        return new AdminException("create.username.duplicated");
    }

    public static AdminException createPasswordNull() {
        return new AdminException("create.password.null");
    }

    //login

    public static AdminException loginFailUsernameNotFound() {
        return new AdminException("login.fail");
    }

    //changepassword

    public static AdminException changePasswordFailUsernameNotFound() {
        return new AdminException("changepassword.fail");
    }

    public static AdminException loginFailPasswordIncorrect() {
        return new AdminException("login.fail");
    }

    public static AdminException fetchAllAdmin() {
        return new AdminException("fetch.null");
    }
}
