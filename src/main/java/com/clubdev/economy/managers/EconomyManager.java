package com.clubdev.economy.managers;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import com.clubdev.economy.Account;
import com.clubdev.economy.Economy;
import com.clubdev.economy.event.money.*;

import java.util.UUID;

public class EconomyManager {
    
    private Economy main;

    public EconomyManager(Economy main) {
        this.main = main;
    }

    public Account getAccount(UUID uuid) {
        return main.getAccountManager().getAccount(uuid);
    }

    public Account getAccount(Player player) {
        return this.getAccount(player.getUniqueId());
    }

    public Account getAccount(IPlayer player) {
        return this.getAccount(player.getUniqueId());
    }

    /* Установка баланса без вызова ивента */

    private void set(UUID uuid, double money) {
        Account account = this.getAccount(uuid);
        main.getAccountManager().updateCachedAccount(new Account(account.getUUID(), money, account.getDonate()));
    }


    public void setMoney(UUID uuid, double money) {
        Account account = this.getAccount(uuid);
        SetMoneyEvent event = new SetMoneyEvent(money, account);
        main.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.set(uuid, money);
        }
    }

    public void setMoney(Player player, double money) {
        this.setMoney(player.getUniqueId(), money);
    }

    public void setMoney(IPlayer player, double money) {
        this.setMoney(player.getUniqueId(), money);
    }


    public void addMoney(UUID uuid, double money) {
        Account account = this.getAccount(uuid);
        AddMoneyEvent event = new AddMoneyEvent(money, account);
        main.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.set(uuid, this.getMoney(uuid) + money);
        }
    }

    public void addMoney(Player player, double money) {
        this.addMoney(player.getUniqueId(), money);
    }

    public void addMoney(IPlayer player, double money) {
        this.addMoney(player.getUniqueId(), money);
    }


    public void reduceMoney(UUID uuid, double money) {
        Account account = this.getAccount(uuid);
        ReduceMoneyEvent event = new ReduceMoneyEvent(money, account);
        main.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.set(uuid, this.getMoney(uuid) > money ? this.getMoney(uuid) - money : 0 );
        }
    }

    public void reduceMoney(Player player, double money) {
        this.reduceMoney(player.getUniqueId(), money);
    }

    public void reduceMoney(IPlayer player, double money) {
        this.reduceMoney(player.getUniqueId(), money);
    }


    public void payMoney(UUID from, UUID to, double money) {
        Account fromAccount = this.getAccount(from);
        Account toAccount = this.getAccount(to);
        PayMoneyEvent event = new PayMoneyEvent(money, fromAccount, toAccount);
        main.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.set(from, this.getMoney(from) - money);
            this.set(to, this.getMoney(to) + money);
        }
    }

    public void payMoney(Player from, Player to, double money) {
        this.payMoney(from.getUniqueId(), to.getUniqueId(), money);
    }

    public void payMoney(IPlayer from, IPlayer to, double money) {
        this.payMoney(from.getUniqueId(), to.getUniqueId(), money);
    }


    public double getMoney(UUID uuid) {
        return this.getAccount(uuid).getMoney();
    }

    public double getMoney(Player player) {
        return this.getMoney(player.getUniqueId());
    }

    public double getMoney(IPlayer player) {
        return this.getMoney(player.getUniqueId());
    }



    public String getMonetaryUnit() {
        return "$";
    }

    public double getDefaultMoney() {
        return 1000;
    }
}
