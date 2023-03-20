package com.clubdev.economy.managers;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import com.clubdev.economy.Account;
import com.clubdev.economy.Economy;
import java.util.UUID;

public class DonateEconomyManager {
    
    private Economy main;

    public DonateEconomyManager(Economy main) {
        this.main = main;
    }

    public Account getAccount(UUID uuid) {
        return main.getAccountManager().getAccount(uuid);
    }

    public Account getAccount(Player player) {
        return main.getAccountManager().getAccount(player.getUniqueId());
    }

    public Account getAccount(IPlayer player) {
        return main.getAccountManager().getAccount(player.getUniqueId());
    }


    public void setDonate(UUID uuid, int donate) {
        Account account = this.getAccount(uuid);
        main.getAccountManager().updateCachedAccount(new Account(account.getUUID(), account.getMoney(), donate));
    }

    public void setDonate(Player player, int donate) {
        this.setDonate(player.getUniqueId(), donate);
    }

    public void setDonate(IPlayer player, int donate) {
        this.setDonate(player.getUniqueId(), donate);
    }


    public void addDonate(UUID uuid, int donate) {
        this.addDonate(uuid, this.getDonate(uuid) + donate);
    }
    
    public void addDonate(Player player, int donate) {
        this.addDonate(player, this.getDonate(player) + donate);
    }

    public void addDonate(IPlayer player, int donate) {
        this.addDonate(player, this.getDonate(player) + donate);
    }


    public void reduceDonate(UUID uuid, int donate) {
        this.reduceDonate(uuid, this.getDonate(uuid) - donate);
    }

    public void reduceDonate(Player player, int donate) {
        this.reduceDonate(player, this.getDonate(player) - donate);
    }

    public void reduceDonate(IPlayer player, int donate) {
        this.reduceDonate(player, this.getDonate(player) - donate);
    }


    public int getDonate(UUID uuid) {
        return this.getAccount(uuid).getDonate();
    }

    public int getDonate(Player player) {
        return this.getDonate(player.getUniqueId());
    }

    public int getDonate(IPlayer player) {
        return this.getDonate(player.getUniqueId());
    }



    public String getMonetaryUnit() {
        return "D.";
    }

    public int getDefaultDonate() {
        return 0;
    }
}
