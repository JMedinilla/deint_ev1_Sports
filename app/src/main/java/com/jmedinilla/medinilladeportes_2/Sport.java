package com.jmedinilla.medinilladeportes_2;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.UUID;

class Sport implements Comparable<Sport>, Parcelable {
    private String sport_id;
    private int sport_image;
    private String sport_name;
    private boolean sport_checked;

    Sport(int sport_image, String sport_name) {
        this.sport_id = UUID.randomUUID().toString();
        this.sport_image = sport_image;
        this.sport_name = sport_name;
        this.sport_checked = false;
    }

    private Sport(Parcel in) {
        sport_id = in.readString();
        sport_image = in.readInt();
        sport_name = in.readString();
        sport_checked = in.readByte() != 0;
    }

    public static final Creator<Sport> CREATOR = new Creator<Sport>() {
        @Override
        public Sport createFromParcel(Parcel in) {
            return new Sport(in);
        }

        @Override
        public Sport[] newArray(int size) {
            return new Sport[size];
        }
    };

    String getSport_id() {
        return sport_id;
    }

    void setSport_id(String sport_id) {
        this.sport_id = sport_id;
    }

    int getSport_image() {
        return sport_image;
    }

    void setSport_image(int sport_image) {
        this.sport_image = sport_image;
    }

    String getSport_name() {
        return sport_name;
    }

    void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    boolean isSport_checked() {
        return sport_checked;
    }

    void setSport_checked(boolean sport_checked) {
        this.sport_checked = sport_checked;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Sport) {
                if (this.sport_name.equalsIgnoreCase(((Sport) obj).sport_name)) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.sport_name;
    }

    @Override
    public int compareTo(@NonNull Sport sport) {
        return this.sport_name.compareToIgnoreCase(sport.sport_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sport_id);
        parcel.writeInt(sport_image);
        parcel.writeString(sport_name);
        parcel.writeByte((byte) (sport_checked ? 1 : 0));
    }
}
