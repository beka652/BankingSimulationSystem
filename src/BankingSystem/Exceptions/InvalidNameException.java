package BankingSystem.Exceptions;

public class InvalidNameException extends Exception{
    @Override
    public String getMessage(){
        return "Invalid name";
    }
}
