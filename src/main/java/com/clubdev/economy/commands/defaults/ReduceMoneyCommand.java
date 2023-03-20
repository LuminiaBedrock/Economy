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
            economyManager.reduceMoney(target, parse(args[1]));
            sender.sendMessage("§aВы забрали " + parse(args[1]) + economyManager.getMonetaryUnit() + " у игрока " + target.getName() + ".");
        } else {
            sender.sendMessage("§cВы ввели неправильную сумму.");
        }

        return true;
    }
}
