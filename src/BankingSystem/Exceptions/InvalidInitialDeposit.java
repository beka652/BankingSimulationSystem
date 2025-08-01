package BankingSystem.Exceptions;

public class InvalidInitialDeposit extends Exception {
    @Override
    public String getMessage(){
        return "Initial deposit should be greater than 0";
    }
}
