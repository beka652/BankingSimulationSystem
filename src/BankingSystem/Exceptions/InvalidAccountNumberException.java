package BankingSystem.Exceptions;

public class InvalidAccountNumberException extends Exception {
    @Override
    public String getMessage(){return "Invalid Account Number";}

}
