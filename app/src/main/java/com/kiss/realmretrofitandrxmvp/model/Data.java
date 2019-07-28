package com.kiss.realmretrofitandrxmvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Data extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    private String details;

    public Data() {
    }

    protected Data(Parcel in) {
        id = in.readInt();
        details = in.readString();
    }

    public Data(int id, String details) {
        this.id = id;
        this.details = details;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(details);
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", details='" + details + '\'' +
                '}';
    }
}
