package BankingSystem.Exceptions;

public class InvalidAmountException extends Exception{
    @Override
    public String getMessage(){return "Invalid input amount";}
}
