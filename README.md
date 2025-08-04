# Banking/ATM Simulation System

A Java-based banking simulation system built with OOP principles, featuring GUI interface and SQLite database.

## Features

- **Account Management**: Create, delete, and manage bank accounts
- **Transactions**: Deposit, withdraw, and transfer funds
- **Reporting**: Generate and export account/transaction reports
- **GUI Interface**: 6 interactive windows using Java Swing
- **Database**: SQLite with JDBC for data persistence

## Architecture

**MVC Pattern:**
- **Model**: `Bank`, `Account`, `Transaction`, `Database` classes
- **View**: Java Swing GUI components
- **Controller**: Manages Model-View communication

**Design Patterns:**
- DAO Pattern for database operations
- Event-driven architecture for user interactions

## Installation

### Prerequisites
- Java JDK 8 or higher
- Any Java IDE (IntelliJ, Eclipse, NetBeans)

### Setup
1. Clone the repository
2. Open in your IDE
3. Run `App.java`

## Usage

1. **Start Application**: Run `App.java`
2. **Navigate**: Use menu buttons on the left
3. **Operations**:
   - Create Account: Enter name and initial deposit
   - Deposit/Withdraw: Enter account number and amount
   - Transfer: Enter source, destination, and amount
   - Delete: Enter account number to remove
   - Reports: Select type and export to files

## Technical Implementation

### OOP Concepts
- **Classes**: 15+ classes across MVC layers
- **Interfaces**: `Bankable`, `IAccount`
- **Abstract Class**: `IBankDatabase`
- **Inheritance**: Multiple hierarchies
- **Polymorphism**: Interface implementations
- **Encapsulation**: Private fields with public accessors

### Java Features
- **Collections**: HashMap for accounts, List for reports
- **Exceptions**: 7 custom exception classes
- **File I/O**: Export reports to text files
- **JDBC**: SQLite database with PreparedStatement
- **Swing GUI**: 6 interactive windows

### Database Schema
```sql
-- Accounts Table
CREATE TABLE accounts (
    accountNumber TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    balance REAL NOT NULL
);

-- Transactions Table
CREATE TABLE transactions (
    transactionID TEXT PRIMARY KEY,
    accountNumber TEXT NOT NULL,
    transactionType TEXT NOT NULL,
    transactionAmount REAL NOT NULL,
    date TEXT NOT NULL
);
```

## Project Structure
```
src/BankingSystem/
├── App.java                    # Main entry point
├── Controller/Controller.java  # MVC Controller
├── Exceptions/                 # 7 custom exceptions
├── Interfaces/                 # Bankable, IAccount
├── Model/                      # Bank, Account, Transaction, Database
└── View/                       # GUI components and events
```

## Requirements Met

✅ 8+ classes (15+ implemented)  
✅ 2+ interfaces  
✅ 1+ abstract class  
✅ Inheritance chain  
✅ Method overloading/overriding  
✅ Custom exceptions  
✅ Collections (HashMap, List)  
✅ JDBC with 2 tables  
✅ File I/O operations  
✅ GUI with 4+ windows (6 implemented)  
✅ PreparedStatement usage  
✅ DAO pattern  

## Author

**Student Name**  
Object-Oriented Programming with Java - Final Project  
Bereket Nebiyu - 2025 