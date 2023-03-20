package com.clubdev.economy.commands.defaults;

import cn.nukkit.IPlayer;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import com.clubdev.economy.Economy;
import com.clubdev.economy.commands.CommandBase;

public class ReduceMoneyCommand extends CommandBase {

    public ReduceMoneyCommand(Economy main) {
        super("reducemoney", "Забрать деньги у игрока", main);

        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET),
                CommandParameter.newType("money", CommandParamType.INT)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        
        if(!testPermission(sender)) {
            sender.sendMessage("§cУ вас нет разрешения на использование этой команды.");
            return false;
        }

        if(args.length != 2) {
            sender.sendMessage("Используете: /" + label + " <игрок> <колличество>");
            return false;
        }

        IPlayer target = getOfflinePlayer(args[0]);
        
        if (!hasAccount(target)) {
            sender.sendMessage("§cДанный игрок не зарегистрирован.");
            return false;
        }

        if (isCorrect(args[1])) {
            double money = parse(args[1]);
            if (economyManager.getMoney(target) > 0) {
                sender.sendMessage("§aВы забрали " + (economyManager.getMoney(target) > money ? money : economyManager.getMoney(target))  + economyManager.getMonetaryUnit() + " у игрока " + target.getName() + ".");
                economyManager.reduceMoney(target, money);
            } else {
                sender.sendMessage("§cУ игрока " + target.getName() + " нет денег на балансе.");
            }
        } else {
            sender.sendMessage("§cВы ввели неправильную сумму.");
        }

        return true;
    }
}
