package com.clubdev.economy.managers;

import com.clubdev.economy.Account;
import com.clubdev.economy.Economy;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountManager {
    
    private Economy main;

    @Getter private Map<UUID, Account> cachedAccounts = new HashMap<>();

    public AccountManager(Economy main) {
        this.main = main;
    }

    public void createAccount(UUID uuid) {
        Account account = new Account(uuid, 
            main.getEconomyManager().getDefaultMoney(), 
            main.getDonateEconomyManager().getDefaultDonate()
        );
        main.getDatabase().createAccount(account);
        cachedAccounts.put(uuid, account);
    }

    public void updateCachedAccount(Account account) {
        Account cachedAccount = cachedAccounts.get(account.getUUID());
        cachedAccount.setMoney(account.getMoney());
        cachedAccount.setDonate(account.getDonate());
    }

    public void removeAccount(UUID uuid) {
        main.getDatabase().removeAccount(uuid);
        cachedAccounts.remove(uuid);
    }

    public Account getAccount(UUID uuid) {
        return cachedAccounts.get(uuid);
    }

    /* 
     * Получение аккаунта напрямую из БД
     * Для получения обычного (кешированого) аккаунта
     * используйте getAccount(UUID uuid) (рекомендуется)
     */

    public Account getRawAccount(UUID uuid) {
        return main.getDatabase().getAccount(uuid);
    }

    public boolean hasCachedAccount(UUID uuid) {
        return cachedAccounts.containsKey(uuid);
    }

    public boolean hasAccount(UUID uuid) {
        return main.getDatabase().hasAccount(uuid);
    }

    /* Загрузка всех аккаунтов в cachedAccounts из БД */

    public void loadAllAccounts() {
        main.getDatabase().getAllAccounts().forEach(account -> {
            cachedAccounts.put(account.getUUID(), account);
        });
    }

    /* Сохранение всех кешированых аккаунтов в БД */

    public void saveAllCachedAccounts() {
        cachedAccounts.forEach((uuid, account) -> {
            if (!main.getDatabase().hasAccount(uuid)) {
                main.getDatabase().createAccount(account);
            } else {
                main.getDatabase().updateAccount(account);
            }
        });
    }
}
