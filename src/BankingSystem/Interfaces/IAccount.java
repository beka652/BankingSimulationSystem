package BankingSystem.Interfaces;

public interface IAccount {
     void withdraw(double withdrawAmount) throws Exception;
     void deposit(double depositAmount) throws Exception;

}
