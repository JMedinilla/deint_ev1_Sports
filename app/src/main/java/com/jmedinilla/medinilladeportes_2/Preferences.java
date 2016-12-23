package com.jmedinilla.medinilladeportes_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class Preferences {
    private SharedPreferences sharedPreferences;
    private static Preferences instance;
    private Repository repository;

    static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    private Preferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        repository = Repository.getInstance();
    }

    void saveSports() {
        for (int i = 0; i < repository.size(); i++) {
            Sport sport = repository.get(i);
            getEditor().putBoolean(sport.getSport_name(), sport.isSport_checked()).commit();
        }
    }

    void loadSports() {
        for (int i = 0; i < repository.size(); i++) {
            Sport sport = repository.get(i);
            sport.setSport_checked(sharedPreferences.getBoolean(sport.getSport_name(), false));
        }
    }
}
