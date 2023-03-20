package com.clubdev.economy.event.money;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.Cancellable;
import lombok.Getter;

import com.clubdev.economy.Account;

@Getter
public class PayMoneyEvent extends Event implements Cancellable {
    
    private final Double money;
    private final Account player;
    private final Account target;

    private static final HandlerList handlers = new HandlerList();
    
    public PayMoneyEvent(Double money, Account player, Account target) {
        this.money = money;
        this.player = player;
        this.target = target;
    }
    
    public static HandlerList getHandlers() {
        return handlers;
    }
}
