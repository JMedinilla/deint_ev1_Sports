package com.jmedinilla.medinilladeportes_2;

import java.util.ArrayList;

class Repository extends ArrayList<Sport> {
    private static Repository instance;

    private Repository() {
        this.add(new Sport(R.mipmap.sport_one, "Fútbol"));
        this.add(new Sport(R.mipmap.sport_two, "Baloncesto"));
        this.add(new Sport(R.mipmap.sport_three, "Bádminton"));
        this.add(new Sport(R.mipmap.sport_four, "Tenis"));
        this.add(new Sport(R.mipmap.sport_five, "Volleyball"));
        this.add(new Sport(R.mipmap.sport_six, "Senderismo"));
        this.add(new Sport(R.mipmap.sport_seven, "Pádel"));
    }

    static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }
}
