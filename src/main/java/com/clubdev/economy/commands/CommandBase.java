package com.clubdev.economy.commands;

import cn.nukkit.Player;
import cn.nukkit.IPlayer;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import com.clubdev.economy.Economy;
import com.clubdev.economy.managers.DonateEconomyManager;
import com.clubdev.economy.managers.EconomyManager;
import com.clubdev.economy.utils.Utils;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommandBase extends Command {
    
    protected Economy economy;

    protected EconomyManager economyManager;
    protected DonateEconomyManager donateEconomyManager;

    public CommandBase(String name, Economy main) {
        super(name);
        this.setPermission("economy." + name);

        this.economy = main;
        this.economyManager = main.getEconomyManager();
        this.donateEconomyManager = main.getDonateEconomyManager();
    }

    public CommandBase(String name, String description, Economy main) {
        super(name, description);
        this.setPermission("economy." + name);

        this.economy = main;
        this.economyManager = main.getEconomyManager();
        this.donateEconomyManager = main.getDonateEconomyManager();
    }

    protected double parse(String string) {
        return Double.parseDouble(string);
    }

    /* 
     * Проверка корректности строки для дальнейшего 
     * использования в parse(String string) 
    */

    protected boolean isCorrect(String string) {
        return Utils.isValidNumber(string);
    }


    protected IPlayer getOfflinePlayer(String name) {
        return economy.getServer().getOfflinePlayer(name);
    }


    protected boolean hasAccount(UUID uuid) {
        return economy.getAccountManager().hasCachedAccount(uuid);
    }

    protected boolean hasAccount(IPlayer player) {
        return economy.getAccountManager().hasCachedAccount(player.getUniqueId());
    }

    /*
     * Проверка на разных игроков.
     * Например: это используется в классе PayCommand
     * чтобы игрок не мог сделать перевод себе.
     */

    protected boolean testForDiffPlayers(Player player1, Player player2) {
        return !player1.equals(player2);
    }

    protected boolean testForDiffPlayers(IPlayer player1, IPlayer player2) {
        return !player1.equals(player2);
    }


    protected boolean testIngame(CommandSender sender) {
        return sender instanceof Player;
    }
}
