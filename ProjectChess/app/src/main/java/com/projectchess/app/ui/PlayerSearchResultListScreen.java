package com.projectchess.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.projectchess.app.R;
import com.projectchess.app.data.Player;

import java.util.List;

public class PlayerSearchResultListScreen extends Fragment {
    private OnFragmentInteractionListener mListener;
    public static List<Player> listOfPlayers;

    public static PlayerSearchResultListScreen newInstance(List<Player> playerList) {
        PlayerSearchResultListScreen fragment = new PlayerSearchResultListScreen();
        listOfPlayers = playerList;
        return fragment;
    }

    public PlayerSearchResultListScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Player player = (Player) listView.getAdapter().getItem(position);
                mListener.fromPlayerSearchResultListTo(OnFragmentInteractionListener.PlayerSearchResultListScreenOptions.PLAYERPROFILESCREEN, player);
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
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_player_search_result_list_row,parent,false);
                ViewHolderItem viewHolder = new ViewHolderItem();
                viewHolder.name = (TextView) convertView.findViewById(R.id.fPSSLR_Name_TextView);
                viewHolder.rating = (TextView) convertView.findViewById(R.id.fPSSLR_Rating_TextView);
                viewHolder.numberofgames = (TextView) convertView.findViewById(R.id.fPSSLR_NumberOfGames_TextView);

                convertView.setTag(viewHolder);
            }

            ViewHolderItem viewHolder = (ViewHolderItem) convertView.getTag();
            Player player = playerList.get(position);
            viewHolder.name.setText(player.getFirstname()+" "+player.getLastname());
            viewHolder.rating.setText(player.getRating());
            viewHolder.numberofgames.setText(player.getNumberofgames());
            return convertView;
        }
    }

    static class ViewHolderItem{
        TextView name;
        TextView rating;
        TextView numberofgames;
    }
}
