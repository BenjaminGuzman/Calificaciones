package io.calificaciones.gui;

public class Preferences {
    private java.util.prefs.Preferences userPreferences;
    public Preferences() {
        java.util.prefs.Preferences userPrefRoot = java.util.prefs.Preferences.userRoot();
        this.userPreferences = userPrefRoot.node("App");
    }
    public void updateDefaultScore(float defaultScore) {
        this.userPreferences.putFloat("defaultScore", defaultScore);
    }
    public void updateBase(int base) {
        this.userPreferences.putInt("base", base);
    }
    public int getBase() {
        return this.userPreferences.getInt("base", 10);
    }
    public float getDefaultScore() {
        return this.userPreferences.getFloat("defaultScore", 10F);
    }
}
