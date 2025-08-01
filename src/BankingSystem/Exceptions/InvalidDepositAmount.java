package BankingSystem.Exceptions;

public class InvalidDepositAmount extends Exception{
    @Override
    public String getMessage(){return "Invalid deposit amount";}
}
