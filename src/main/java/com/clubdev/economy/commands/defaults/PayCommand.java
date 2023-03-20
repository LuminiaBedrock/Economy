package com.clubdev.economy.commands.defaults;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import com.clubdev.economy.Economy;
import com.clubdev.economy.commands.CommandBase;

public class PayCommand extends CommandBase {
    
    public PayCommand(Economy main) {
        super("pay", "Перевести деньги другому игроку", main);

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

        Player player = (Player) sender;
        IPlayer target = getOfflinePlayer(args[0]);

        if (!hasAccount(target)) {
            sender.sendMessage("§cДанный игрок не зарегистрирован.");
            return false;
        }

        if (!isCorrect(args[1])) {
            player.sendMessage("§cВы ввели неправильную сумму.");
            return false;
        }

        double money = parse(args[1]);
        
        if (economyManager.getMoney(player) < money) {  
            player.sendMessage("Не достаточно денег.");
            return false;
        } 

        economyManager.payMoney(player, target, money);;

        player.sendMessage("§aВы перевели " + money + economyManager.getMonetaryUnit() + " игроку " + target.getName() + ".");
        if (target.isOnline()) {
            target.getPlayer().sendMessage("§aИгрок " + player.getName() + " перевел вам " + money + economyManager.getMonetaryUnit() + ".");
        }

        return true;
    }
}
