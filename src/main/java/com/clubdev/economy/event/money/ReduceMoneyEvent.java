package com.clubdev.economy.event.money;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.Cancellable;
import lombok.Getter;

import com.clubdev.economy.Account;

@Getter
public class ReduceMoneyEvent extends Event implements Cancellable {
    
    private final Double money;
    private final Account account;

    private static final HandlerList handlers = new HandlerList();
    
    public ReduceMoneyEvent(Double money, Account account) {
        this.money = money;
        this.account = account;
    }
    
    public static HandlerList getHandlers() {
        return handlers;
    }
}
