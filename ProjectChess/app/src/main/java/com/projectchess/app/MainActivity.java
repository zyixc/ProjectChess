package com.projectchess.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.projectchess.app.data.Player;
import com.projectchess.app.ui.GameSearchScreen;
import com.projectchess.app.ui.PlayerProfileScreen;
import com.projectchess.app.ui.PlayerSearchResultListScreen;
import com.projectchess.app.ui.PlayerSearchScreen;
import com.projectchess.app.ui.StartScreen;


public class MainActivity extends FragmentActivity
        implements StartScreen.OnFragmentInteractionListener,
        PlayerSearchScreen.OnFragmentInteractionListener,
        PlayerSearchResultListScreen.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, StartScreen.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fromStartScreenTo(StartScreenOptions options){
        if(options == StartScreenOptions.PLAYERSEARCHSCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, PlayerSearchScreen.newInstance())
                .addToBackStack(null)
                .commit();
        else if(options == StartScreenOptions.GAMESEARCHSCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, GameSearchScreen.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void fromPlayerSearchScreenTo(PlayerSearchScreenOptions options, String playerlastname){
        if(options == PlayerSearchScreenOptions.PLAYERSEARCHRESULTLIST) getFragmentManager()
                .beginTransaction().replace(R.id.container, PlayerSearchResultListScreen.newInstance(playerlastname))
                .addToBackStack(null)
                .commit();
    }

    public void fromPlayerSearchResultListTo(PlayerSearchResultListScreenOptions options, Player player){
        if(options == PlayerSearchResultListScreenOptions.PLAYERPROFILESCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, PlayerProfileScreen.newInstance("1","1"))
                .addToBackStack(null)
                .commit();
    }
}
