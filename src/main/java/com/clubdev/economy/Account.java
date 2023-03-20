package com.clubdev.economy;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Account {
    
    private final UUID UUID;
    private final Double money;
    private final Integer donate;

    public Account(UUID uuid, double money, int donate) {
        this.UUID = uuid;
        this.money = money;
        this.donate = donate;
    }
}
