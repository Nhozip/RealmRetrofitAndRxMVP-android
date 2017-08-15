package com.kiss.realmretrofitandrxmvp.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kiss.realmretrofitandrxmvp.R;
import com.kiss.realmretrofitandrxmvp.adapter.MainAdapter;
import com.kiss.realmretrofitandrxmvp.detail.DetailActivity;
import com.kiss.realmretrofitandrxmvp.model.GitHubUser;
import com.kiss.realmretrofitandrxmvp.utils.Constans;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements MainView, MainAdapter.OnItemClickListener {
    @BindView(R.id.mainRecyclerView)
    RecyclerView mainRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.fMain)
    FrameLayout fMain;
    private MainAdapter mainAdapter;
    private MainPresenter mainPresenter;
    private RealmUserUtils realmUserUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
        ButterKnife.bind(this);
        realmUserUtils = new RealmUserUtils();
        mainPresenter = new MainPrerenterImlp(this, realmUserUtils);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.displayData();
        Snackbar.make(fMain, "Muốn làm việc với realm tắt mạng đê :)", Snackbar.LENGTH_INDEFINITE).show();

    }

    @Override
    public void displayData(List<GitHubUser> gitHubUserList) {
        mainAdapter = new MainAdapter(MainActivity.this, gitHubUserList, this);
        mainRecyclerView.setAdapter(mainAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmUserUtils.closeReaalm();
    }


    @Override
    public void showMes(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProscee() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProcess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(View v, GitHubUser gitHubUser) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constans.USER, Parcels.wrap(gitHubUser));
        startActivity(intent);
    }

    @Override
    public void onLongClick(View v, GitHubUser gitHubUser) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        List<String> listItems = new ArrayList<String>();
        alert.setTitle("Xóa or update");
        listItems.add("Del");
        listItems.add("Update");
        final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);
        alert.setItems(charSequenceItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    realmUserUtils.dellUserId(gitHubUser.getId());
                } else {
                    GitHubUser user = new GitHubUser();
                    user.setAvatar_url("https://avatars0.githubusercontent.com/u/1?v=4");
                    user.setLogin("Nhozip");
                    user.setId(gitHubUser.getId());
                    realmUserUtils.udapteOrInsertUser(user);
                    Toast.makeText(MainActivity.this, realmUserUtils.getUser(user.getId()).toString(), Toast.LENGTH_SHORT).show();

                }
                mainAdapter.notifyDataSetChanged();

            }
        });
        alert.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                try {
                    int id = new Random().nextInt(20);
                    realmUserUtils.addUser(new GitHubUser(id, "Nhozip " + id, "https://avatars0.githubusercontent.com/u/1?v=4"));
                } catch (Exception e) {
                    Toast.makeText(this, "ID tồn tại", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_item_dell_all:
                realmUserUtils.dellAllUser();
                break;
            case R.id.menu_item_sort:
                displayData(realmUserUtils.getListUserSortD());
                break;
        }
        mainAdapter.notifyDataSetChanged();

        return super.onOptionsItemSelected(item);
    }
}
