package com.mzimu.rpg.command;

import com.mzimu.rpg.event.gui.ItemLvUpGui;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMain implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("zmwl.use")){
                switch (strings[0]){
                    case "openLv":
                        if(p.hasPermission("zmwl.openLv.admin") && strings.length>1){
                            new ItemLvUpGui().open(Bukkit.getPlayer(strings[1]));
                            return true;
                        }
                        new ItemLvUpGui().open(p);
                        return true;
                }
            }
        }else{
            switch (strings[0]){
                case "openLv":
                    if(!(strings.length>1)){
                        Help_Msg_C();
                        return false;
                    }
                    new ItemLvUpGui().open(Bukkit.getPlayer(strings[1]));
                    return true;
                case "help":
                case "?":
                default:
                    Help_Msg_C();
                    return true;

            }
        }

        return false;
    }

    public void Help_Msg_C(){
        System.out.println("zmwl openlv [玩家] 给玩家打开界面");
    }
}
