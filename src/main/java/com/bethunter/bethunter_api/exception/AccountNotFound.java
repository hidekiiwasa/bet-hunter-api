package com.bethunter.bethunter_api.exception;

public class AccountNotFound extends RuntimeException{

    public AccountNotFound() { super("Account not found"); }

    public AccountNotFound(String message) { super(message); }
}
