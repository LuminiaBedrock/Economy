package com.clubdev.economy;

import com.clubdev.economy.database.Database;
import com.clubdev.economy.listeners.EventListener;
import com.clubdev.economy.managers.AccountManager;
import com.clubdev.economy.managers.DonateEconomyManager;
import com.clubdev.economy.managers.EconomyManager;
import com.clubdev.economy.commands.defaults.*;

import cn.nukkit.plugin.PluginBase;
import lombok.Getter;

@Getter
public class Economy extends PluginBase {

    @Getter private static Economy instance;
    
    private Database database;
    private AccountManager accountManager;

    private EconomyManager economyManager;
    private DonateEconomyManager donateEconomyManager;

    @Override
    public void onLoad() {
        this.accountManager = new AccountManager(this);
    }
    
    @Override
    public void onEnable() {
        instance = this;
        
        this.database = new Database(this);
        this.economyManager = new EconomyManager(this);
        this.donateEconomyManager = new DonateEconomyManager(this);

        accountManager.loadAllAccounts();
        this.register();
    }

    @Override
    public void onDisable() {
        accountManager.saveAllCachedAccounts();
    }
    
    private void register() {
        this.getServer().getCommandMap().register("Economy", new AddMoneyCommand(this));
        this.getServer().getCommandMap().register("Economy", new SetMoneyCommand(this));
        this.getServer().getCommandMap().register("Economy", new ReduceMoneyCommand(this));
        this.getServer().getCommandMap().register("Economy", new PayCommand(this));
        this.getServer().getCommandMap().register("Economy", new MoneyCommand(this));
        this.getServer().getPluginManager().registerEvents(new EventListener(instance), this);
    }
}
