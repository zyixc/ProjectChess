package com.projectchess.app.ui;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.projectchess.app.R;
import com.projectchess.app.data.DataProvider;
import com.projectchess.app.data.Player;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerSearchResultListScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerSearchResultListScreen#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PlayerSearchResultListScreen extends Fragment {
    private OnFragmentInteractionListener mListener;
    public static List<Player> listOfPlayers = new ArrayList<Player>();

    public static PlayerSearchResultListScreen newInstance(String playerLastName) {
        PlayerSearchResultListScreen fragment = new PlayerSearchResultListScreen();
        //TODO not working yet
        //listOfPlayers = DataProvider.INSTANCE.requestPlayerList(playerLastName);
        return fragment;
    }

    public PlayerSearchResultListScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listOfPlayers.add(new Player("1","testcase1","lastName1"));
        listOfPlayers.add(new Player("2","testcase2","lastName2"));
        listOfPlayers.add(new Player("3","testcase3","lastName3"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_player_search_result_list_screen,container,false);
        final ListView listView = (ListView) view.findViewById(R.id.FPSR_player_search_result_listView);
        final PlayerArrayAdapter adapter = new PlayerArrayAdapter(view.getContext(), listOfPlayers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        enum PlayerSearchResultListScreenOptions{
            PLAYERPROFILESCREEN
        }

        public void fromPlayerSearchResultListTo(PlayerSearchResultListScreenOptions option, Player player);
    }

    private class PlayerArrayAdapter extends ArrayAdapter<Player> {
        private final Context context;
        private final List<Player> playerList;

        public PlayerArrayAdapter(Context context, List<Player> players) {
            super(context, R.layout.fragment_player_search_result_list_row, players);
            this.playerList = players;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.fragment_player_search_result_list_row, parent, false);
            TextView name = (TextView) rowView.findViewById(R.id.fPSSLR_Name_TextView);
            TextView rating = (TextView) rowView.findViewById(R.id.fPSSLR_Rating_TextView);
            TextView numberofgames = (TextView) rowView.findViewById(R.id.fPSSLR_NumberOfGames_TextView);

            Player temp = playerList.get(position);
            name.setText(temp.getFirstname()+" "+temp.getLastname());
            rating.setText(temp.getRating());
            numberofgames.setText(temp.getNumberofgames());
            return rowView;
        }
    }
}
