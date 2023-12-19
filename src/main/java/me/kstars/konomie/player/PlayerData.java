package me.kstars.konomie.player;

import java.util.UUID;

public class PlayerData {
    private final UUID playerUuid;
    private final String playerName;
    private double pay;

    public PlayerData(UUID playerUuid, String playerName, double pay) {
        this.playerUuid = playerUuid;
        this.playerName = playerName;
        this.pay = pay;
    }

    public UUID getUuid() {
        return this.playerUuid;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public double getPay() {
        return this.pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
}
