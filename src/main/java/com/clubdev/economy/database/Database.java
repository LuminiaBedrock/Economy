package com.clubdev.economy.database;

import com.clubdev.economy.Account;
import com.clubdev.economy.Economy;

import me.hteppl.data.database.SQLiteDatabase;
import org.sql2o.Connection;

import java.util.List;
import java.util.UUID;


public class Database extends SQLiteDatabase {

    public Database(Economy main) {
        super("plugins/" + main.getName(), "database");
        this.executeScheme("CREATE TABLE IF NOT EXISTS `Economy` ( `uuid` TEXT NOT NULL UNIQUE, `money` DOUBLE NOT NULL, `donate` INT NOT NULL );");
    }

    public void createAccount(Account account) {
        final String query = "INSERT INTO Economy (uuid, money, donate) VALUES (:uuid, :money, :donate);";
        try (Connection con = openConnection()) {
            con.createQuery(query)
                .addParameter("uuid", account.getUUID().toString())
                .addParameter("money", account.getMoney())
                .addParameter("donate", account.getDonate())
                .executeUpdate();                        
        }
    }

    public void updateAccount(Account account) {
        final String query = "UPDATE Economy SET uuid = :uuid, money = :money, donate = :donate;";
        try (Connection con = openConnection()) {
            con.createQuery(query)
                .addParameter("uuid", account.getUUID().toString())
                .addParameter("money", account.getMoney())
                .addParameter("donate", account.getDonate())
                .executeUpdate();                        
        }
    }

    public void removeAccount(UUID uuid) {
        final String query = "DELETE FROM Economy WHERE uuid = :uuid;";
        try (Connection con = openConnection()) {
            con.createQuery(query)
                .addParameter("uuid", uuid)
                .executeUpdate();                        
        }
    }

    public Account getMoney(UUID uuid) {
        final String query = 
            "SELECT * FROM Economy WHERE uuid = :uuid;";
        try (Connection con = openConnection()) {
            return con.createQuery(query)
                .addParameter("uuid", uuid)
                .executeAndFetch(Account.class)
                .get(0);          
        }
    }

    public List<Account> getAllAccounts() {
        final String query = "SELECT * FROM Economy;";
        try (Connection con = openConnection()) {
            return con.createQuery(query)
                .executeAndFetch(Account.class);
        }
    }

    public boolean hasAccount(UUID uuid) {
        String query = "SELECT COUNT(*) FROM `Money` WHERE uuid = :uuid;";
        try (Connection con = openConnection()) {
            int count = con.createQuery(query)
                .addParameter("uuid", uuid)
                .executeScalar(Integer.class);
            return count > 0;
        }
    }

    public boolean hasAccounts() {
        String query = "SELECT COUNT(*) FROM `Money`;";
        try (Connection con = openConnection()) {
            int count = con.createQuery(query)
                .executeScalar(Integer.class);
            return count > 0;
        }
    }
}
