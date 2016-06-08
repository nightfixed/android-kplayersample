package smt.dev.kplayersampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kaltura.playersdk.KPPlayerConfig;
import com.kaltura.playersdk.PlayerViewController;
import com.kaltura.playersdk.events.KPEventListener;
import com.kaltura.playersdk.events.KPlayerState;
import com.kaltura.playersdk.types.KPError;

import java.lang.ref.WeakReference;

/**
 * Created by Andrada Anca on 6/8/2016.
 */
public class PlayerActivity extends AppCompatActivity implements KPEventListener {

  private PlayerViewController mPlayerViewController;

  public static String AUTO_PLAY = "auto_play_key";

  private boolean isAutoPlay;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    isAutoPlay = getIntent().getBooleanExtra(AUTO_PLAY, false);

    setContentView(R.layout.activity_player);

    initKPlayer("0_3ab8j6le");
  }

  private void initKPlayer(String firstEntryId) {
    if (mPlayerViewController == null) {
      mPlayerViewController = (PlayerViewController) findViewById(R.id.kplayer_controller);
      mPlayerViewController.setActivity(new WeakReference<>(this).get());

      KPPlayerConfig kplayerConfig = new KPPlayerConfig("http://kgit.html5video.org/tags/v2.43.rc11/mwEmbedFrame.php",
              "34580102",
              "2093031");
      kplayerConfig.addConfig("fullScreenBtn.plugin", "false");
      kplayerConfig.addConfig("chromecast.plugin", "false");
      kplayerConfig.setHideControlsOnPlay(true);
      kplayerConfig.setAutoPlay(isAutoPlay);
      kplayerConfig.setEntryId(firstEntryId);
      mPlayerViewController.initWithConfiguration(kplayerConfig);

      mPlayerViewController.addEventListener(this);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPlayerViewController.removePlayer();
  }

  @Override public void onKPlayerStateChanged(final PlayerViewController playerViewController, KPlayerState state) {
    System.out.println("playerViewController = [" + playerViewController + "], state = [" + state + "]");

    if (state == KPlayerState.READY) {
      if (!isAutoPlay) {
        playerViewController.getMediaControl().start();

        //------------->frame freeze workaround
//        new Handler().postDelayed(new Runnable() {
//          @Override public void run() {
//            playerViewController.getMediaControl().start();
//          }
//        }, 100);
        // <-------------------------- frame freeze workaround
      }
    }
  }

  @Override public void onKPlayerError(PlayerViewController playerViewController, KPError error) {

  }

  @Override public void onKPlayerPlayheadUpdate(PlayerViewController playerViewController, float currentTime) {

  }

  @Override public void onKPlayerFullScreenToggeled(PlayerViewController playerViewController, boolean isFullscrenn) {

  }
}
