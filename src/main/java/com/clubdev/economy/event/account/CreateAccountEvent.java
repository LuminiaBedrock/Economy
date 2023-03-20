package com.clubdev.economy.event.account;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.Cancellable;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateAccountEvent extends Event implements Cancellable {
    
    private final UUID uuid;

    private static final HandlerList handlers = new HandlerList();

    public CreateAccountEvent(UUID uuid) {
        this.uuid = uuid;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }
}
