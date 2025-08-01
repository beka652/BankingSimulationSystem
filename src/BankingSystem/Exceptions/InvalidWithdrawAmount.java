package BankingSystem.Exceptions;

public class InvalidWithdrawAmount extends Exception{
    @Override
    public String getMessage(){
        return "Invalid withdraw amount";
    }
}
