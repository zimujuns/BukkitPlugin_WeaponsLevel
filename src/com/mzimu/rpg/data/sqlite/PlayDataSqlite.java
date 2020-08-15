package com.mzimu.rpg.data.sqlite;

import org.bukkit.entity.Player;
import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayDataSqlite {
    public Statement stat = null;
    public PlayDataSqlite() {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:plugins/ZMWeaponsLevel/data.db");
            Statement stat = conn.createStatement();
            try{
                stat.executeQuery("select * from playData");
            }catch (SQLiteException sqle){
                stat.executeUpdate("create table playData(" +
                        "uuid intnteger primary key not null," +
                        "killExp integer not null)");
            }
            this.stat = stat;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Statement getStat() {
        return stat;
    }


    public void createPlayData(int uuid) throws SQLException {
        getStat().executeUpdate("insert into playData(uuid,killExp) values("+uuid +","+0+")");
    }

    public void setPlayKillExp(int uuid,int killExp) throws SQLException {
        getStat().executeUpdate("updata playData set killExp "+killExp + " where uuid == "+uuid);
    }

    public int getPlayKillExp(int uuid) throws SQLException {
        return getStat().executeQuery("select * from playData where uuid == "+uuid).getInt("killExp");
    }

    public static int getUUID(Player player){
        int uuid= player.getUniqueId().hashCode();
        return uuid>0?uuid:-uuid;
    }
}
