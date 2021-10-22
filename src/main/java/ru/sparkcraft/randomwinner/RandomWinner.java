package ru.sparkcraft.randomwinner;

import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalTime;
import java.util.List;

public final class RandomWinner extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        int winnersAmount = Randomizer.getRandomNumber(getConfig().getInt("winnersAmount.min"), getConfig().getInt("winnersAmount.max"));
        List<LocalTime> timeList = Randomizer.getRandomTimes(winnersAmount);

        new Task(timeList, this).runTaskTimer(this, 0L, 1200L);

        if (getConfig().getBoolean("log.enabled")) {
            getLogger().info(() -> getConfig().getString("log.howMuchWinners") + winnersAmount);
            getLogger().info(() -> getConfig().getString("log.timeToLottery") + timeList);
        }
    }
}
