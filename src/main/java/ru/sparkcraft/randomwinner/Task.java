package ru.sparkcraft.randomwinner;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalTime;
import java.util.List;

public class Task extends BukkitRunnable {

    List<LocalTime> timeList;
    Plugin plugin;

    public Task(List<LocalTime> timeList, Plugin plugin) {
        this.timeList = timeList;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (LocalTime time : timeList) {
            LocalTime now = LocalTime.now();

            if (time.getHour() == now.getHour() && time.getMinute() == now.getMinute()) {
                FileConfiguration config = plugin.getConfig();
                Player winner = Randomizer.getRandomOnlinePlayer();

                if (winner != null) {
                    int randomNumber = Randomizer.getRandomNumber
                            (config.getInt("randomNumber.min"), config.getInt("randomNumber.max"));

                    executeCommands(winner, randomNumber);
                }
            }
        }
    }

    private void executeCommands(Player winner, int randomNumber) {
        List<String> commands = plugin.getConfig().getStringList("commandsToExecute");
        for (String command : commands) {
            String resultCommand = command
                    .replace("%randomNumber%", String.valueOf(randomNumber))
                    .replace("%winnerName%", winner.getName());

            Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), resultCommand);
        }
    }
}
