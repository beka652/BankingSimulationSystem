package BankingSystem.Exceptions;

public class AccountNotFoundException extends Exception{
    @Override
    public String getMessage(){ return "Account not found"; }
}
