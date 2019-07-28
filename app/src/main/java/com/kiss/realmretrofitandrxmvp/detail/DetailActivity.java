package com.kiss.realmretrofitandrxmvp.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kiss.realmretrofitandrxmvp.R;
import com.kiss.realmretrofitandrxmvp.main.RealmUserUtils;
import com.kiss.realmretrofitandrxmvp.model.GitHubUser;
import com.kiss.realmretrofitandrxmvp.utils.Constans;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    private RealmUserUtils realmUserUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        realmUserUtils = new RealmUserUtils();
        TextView tv_contnent = (TextView) findViewById(R.id.tv_content);
        Intent intent = getIntent();
        GitHubUser gitHubUser = intent.getParcelableExtra(Constans.USER);
        tv_contnent.setText(realmUserUtils.getUser(gitHubUser.getId()).getData().toString());
    }
}
