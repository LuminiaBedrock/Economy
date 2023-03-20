package com.clubdev.economy.listeners;

import java.util.UUID;

import com.clubdev.economy.Economy;
import com.clubdev.economy.event.account.CreateAccountEvent;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;

public class EventListener implements Listener {
    
    private Economy main;

    public EventListener(Economy main) {
        this.main = main;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (!main.getAccountManager().hasCachedAccount(uuid)) {
            CreateAccountEvent callEvent = new CreateAccountEvent(uuid);
            main.getServer().getPluginManager().callEvent(callEvent);
            
            if (!callEvent.isCancelled()) {
                main.getAccountManager().createAccount(uuid);
            }
        }
    }
}
