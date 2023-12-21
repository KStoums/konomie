package me.kstars.konomie.player;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class PlayerData {
    @SerializedName("playerUuid")
    private final UUID playerUuid;
    @SerializedName("pay")
    private double pay;

    public PlayerData(UUID playerUuid, double pay) {
        this.playerUuid = playerUuid;
        this.pay = pay;
    }

    public UUID getUuid() {
        return this.playerUuid;
    }

    public double getPay() {
        return this.pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
}
