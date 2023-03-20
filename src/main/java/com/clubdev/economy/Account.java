package com.clubdev.economy;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    
    private final UUID UUID;
    private Double money;
    private Integer donate;

    public Account(UUID uuid, double money, int donate) {
        this.UUID = uuid;
        this.money = money;
        this.donate = donate;
    }
}
