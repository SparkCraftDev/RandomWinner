package ru.sparkcraft.randomwinner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Randomizer {

    private Randomizer() {
    }

    public static int getRandomNumber(int max) {
        return new Random().nextInt(max);
    }

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static List<LocalTime> getRandomTimes(int amount) {
        List<LocalTime> randomTimes = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            randomTimes.add(LocalTime.of(getRandomNumber(24), getRandomNumber(60)));
        }
        return randomTimes;
    }

    public static Player getRandomOnlinePlayer() {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (!players.isEmpty()) {
            Collections.shuffle(players);
            return players.get(getRandomNumber(players.size()));
        }
        return null;
    }
}
