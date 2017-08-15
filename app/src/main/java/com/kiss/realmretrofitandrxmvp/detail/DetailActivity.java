package com.kiss.realmretrofitandrxmvp.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kiss.realmretrofitandrxmvp.R;
import com.kiss.realmretrofitandrxmvp.model.GitHubUser;
import com.kiss.realmretrofitandrxmvp.utils.Constans;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView tv_contnent = (TextView) findViewById(R.id.tv_content);
        GitHubUser user = Parcels.unwrap(getIntent().getParcelableExtra(Constans.USER));
        tv_contnent.setText(user.toString());
    }
}
