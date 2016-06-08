package smt.dev.kplayersampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    final Intent in = new Intent(this, PlayerActivity.class);

    findViewById(R.id.btn_goto_autoplay).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        in.putExtra(PlayerActivity.AUTO_PLAY, true);
        startActivity(in);
      }
    });

    findViewById(R.id.btn_goto_player).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        in.putExtra(PlayerActivity.AUTO_PLAY, false);
        startActivity(in);
      }
    });
  }
}
