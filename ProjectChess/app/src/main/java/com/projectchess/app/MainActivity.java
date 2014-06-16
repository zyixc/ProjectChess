package com.projectchess.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.projectchess.app.data.Game;
import com.projectchess.app.data.Player;
import com.projectchess.app.ui.GameProfileScreen;
import com.projectchess.app.ui.GameSearchResultListScreen;
import com.projectchess.app.ui.GameSearchScreen;
import com.projectchess.app.ui.PlayerProfileScreen;
import com.projectchess.app.ui.PlayerSearchResultListScreen;
import com.projectchess.app.ui.PlayerSearchScreen;
import com.projectchess.app.ui.StartScreen;

import java.util.List;


public class MainActivity extends FragmentActivity
        implements StartScreen.OnFragmentInteractionListener,
        PlayerSearchScreen.OnFragmentInteractionListener,
        PlayerSearchResultListScreen.OnFragmentInteractionListener,
        PlayerProfileScreen.OnFragmentInteractionListener,
        GameSearchScreen.OnFragmentInteractionListener,
        GameSearchResultListScreen.OnFragmentInteractionListener{

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

    public void fromPlayerSearchScreenTo(PlayerSearchScreenOptions options, List<Player> players){
        if(options == PlayerSearchScreenOptions.PLAYERSEARCHRESULTLISTSCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, PlayerSearchResultListScreen.newInstance(players))
                .addToBackStack(null)
                .commit();
    }

    public void fromPlayerSearchResultListTo(PlayerSearchResultListScreenOptions options, Player player){
        if(options == PlayerSearchResultListScreenOptions.PLAYERPROFILESCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, PlayerProfileScreen.newInstance(player))
                .addToBackStack(null)
                .commit();
    }

    public void fromPlayerProfileScreenTo(PlayerProfileScreenOptions options, List<Game> games){
        if(options == PlayerProfileScreenOptions.GAMESEARCHRESULTLISTSCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, GameSearchResultListScreen.newInstance(games))
                .addToBackStack(null)
                .commit();
    }

    public void fromGameSearchScreenTo(GameSearchScreenOptions options, List<Game> games){
        if(options == GameSearchScreenOptions.GAMESEARCHRESULTLISTSCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, GameSearchResultListScreen.newInstance(games))
                .addToBackStack(null)
                .commit();
    }

    public void fromGameSearchResultListTo(GameSearchResultListScreenOptions options, Game game){
        if(options == GameSearchResultListScreenOptions.GAMEPROFILESCREEN) getFragmentManager()
                .beginTransaction().replace(R.id.container, GameProfileScreen.newInstance("1","2"))
                .addToBackStack(null)
                .commit();
    }
}
