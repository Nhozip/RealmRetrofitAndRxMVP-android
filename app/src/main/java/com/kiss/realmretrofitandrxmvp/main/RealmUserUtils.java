package com.kiss.realmretrofitandrxmvp.main;

import com.kiss.realmretrofitandrxmvp.model.GitHubUser;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Admins on 7/31/2017.
 */

public class RealmUserUtils {
    private Realm realm;

    public RealmUserUtils() {

        realm = Realm.getDefaultInstance();
    }

    public void closeReaalm() {
        realm.close();
    }

    public GitHubUser getUser(int id) {
        return realm.where(GitHubUser.class).equalTo("id", id).findFirst();
    }

    //list sort
    public RealmResults<GitHubUser> getListUser() {
//        return realm.where(GitHubUser.class).findAllSorted("id", Sort.ASCENDING);
        return realm.where(GitHubUser.class).findAll();
    }

    public void addUser(GitHubUser gitHubUser) {
        realm.executeTransaction(realm1 -> realm1.insert(gitHubUser));
    }

    public void udapteOrInsertListUser(List<GitHubUser> gitHubUsers) {
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(gitHubUsers));
    }

    public void udapteOrInsertUser(GitHubUser gitHubUsers) {
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(gitHubUsers));
    }

    public void dellAllUser() {
        realm.executeTransaction(realm1 -> realm1.delete(GitHubUser.class));
    }

    public void dellUserId(int id) {
        realm.executeTransaction(realm1 -> realm1.where(GitHubUser.class).equalTo("id", id).findAll().deleteAllFromRealm());
    }

    public void sortUser() {
        realm.executeTransaction(realm1 -> realm1.where(GitHubUser.class).findAll().sort("id", Sort.ASCENDING));

    }

    public RealmList<GitHubUser> getListUserSortD() {
//        return realm.where(GitHubUser.class).findAllSorted("id", Sort.DESCENDING);
        return null;
    }
}
