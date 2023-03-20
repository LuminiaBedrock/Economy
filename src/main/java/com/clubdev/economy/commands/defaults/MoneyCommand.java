package com.clubdev.economy.commands.defaults;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

import com.clubdev.economy.Economy;
import com.clubdev.economy.commands.CommandBase;

public class MoneyCommand extends CommandBase {
    
    public MoneyCommand(Economy main) {
        super("money", "Узнать баланс", main);

        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET)
        });
    }
    
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if(!testPermission(sender)) {
            sender.sendMessage("§cУ вас нет разрешения на использование этой команды.");
            return false;
        }

        IPlayer target;
        if (args.length == 1) {
            target = getOfflinePlayer(args[0]);
        } else {
            if (!(sender instanceof Player)) {
                return false;
            }
            target = (Player) sender;
        }

        if (!hasAccount(target)) {
            sender.sendMessage("§cДанный игрок не зарегистрирован.");
            return false;
        }

        if (args.length == 0 || args.length == 1 && testIngame(sender) && !testForDiffPlayers((Player) sender, target)) {
            sender.sendMessage("У вас на балансе: " + economyManager.getMoney(target) + economyManager.getMonetaryUnit());
        } else {
            sender.sendMessage("Баланс игрока " + target.getName() + ": " + economyManager.getMoney(target) + economyManager.getMonetaryUnit());
        }

        return false;
    }
}
