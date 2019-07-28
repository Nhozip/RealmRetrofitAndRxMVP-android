package com.kiss.realmretrofitandrxmvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Admins on 7/30/2017.
 */

public class GitHubUser extends RealmObject implements Parcelable {

    private String login;
    @PrimaryKey
    private int id;
    private String avatar_url;
    private Data data;
    RealmList<UserDetails> detailsRealmList;

    public GitHubUser() {
    }


    protected GitHubUser(Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatar_url = in.readString();
        data = in.readParcelable(Data.class.getClassLoader());
        gitHubUser = in.readParcelable(GitHubUser.class.getClassLoader());
    }

    public static final Creator<GitHubUser> CREATOR = new Creator<GitHubUser>() {
        @Override
        public GitHubUser createFromParcel(Parcel in) {
            return new GitHubUser(in);
        }

        @Override
        public GitHubUser[] newArray(int size) {
            return new GitHubUser[size];
        }
    };

    public GitHubUser getGitHubUser() {
        return gitHubUser;
    }

    public void setGitHubUser(GitHubUser gitHubUser) {
        this.gitHubUser = gitHubUser;
    }

    GitHubUser gitHubUser;

    public GitHubUser(int id, String login, String avatar_url) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public RealmList<UserDetails> getDetailsRealmList() {
        return detailsRealmList;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setDetailsRealmList(RealmList<UserDetails> detailsRealmList) {
        this.detailsRealmList = detailsRealmList;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", detailsRealmList=" + detailsRealmList +

                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeInt(id);
        parcel.writeString(avatar_url);
        parcel.writeParcelable(data, i);
        parcel.writeParcelable(gitHubUser, i);
    }
}