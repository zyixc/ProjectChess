package com.projectchess.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectchess.app.R;
import com.projectchess.app.data.Game;
import com.projectchess.app.data.Player;

import java.util.List;
import java.util.ListIterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerProfileScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerProfileScreen#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PlayerProfileScreen extends Fragment {
    private OnFragmentInteractionListener mListener;
    private static Player player;

    public static PlayerProfileScreen newInstance(Player passedplayer) {
        PlayerProfileScreen fragment = new PlayerProfileScreen();
        player = passedplayer;
        return fragment;
    }
    public PlayerProfileScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_player_profile_screen, container, false);
        final TextView name = (TextView) view.findViewById(R.id.FPPS_player_profile_name_textView);
        final TextView rating = (TextView) view.findViewById(R.id.FPPS_player_profile_rating_textView);
        final TextView numberofgames = (TextView) view.findViewById(R.id.FPPS_player_profile_number_of_games_textView);
        final TextView prefw1 = (TextView) view.findViewById(R.id.FPPS_player_profile_pref_w1);
        final TextView prefw2 = (TextView) view.findViewById(R.id.FPPS_player_profile_pref_w2);
        final TextView prefw3 = (TextView) view.findViewById(R.id.FPPS_player_profile_pref_w3);

        name.setText(player.getFirstname()+" "+player.getLastname());
        rating.setText(player.getRating());
        if(player.getNumberofgames()!=null) numberofgames.setEnabled(true);
        numberofgames.setText(player.getNumberofgames());
        if(player.getW1()!=null) prefw1.setEnabled(true);
        prefw1.setText(player.getW1());
        if(player.getW2()!=null) prefw2.setEnabled(true);
        prefw2.setText(player.getW2());
        if(player.getW3()!=null) prefw3.setEnabled(true);
        prefw3.setText(player.getW3());

        numberofgames.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                List<Game> games = player.getWhite_games();
                games.addAll(player.getBlack_games());
                mListener.fromPlayerProfileScreenTo(OnFragmentInteractionListener.PlayerProfileScreenOptions.GAMESEARCHRESULTLISTSCREEN, games);
            }
        });
        prefw1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                List<Game> games = player.getWhite_games();
                ListIterator<Game> iter = games.listIterator();
                String w = player.getW1();
                while(iter.hasNext()){
                    if(iter.next().getW()[0].equals(w)){
                        iter.remove();
                    }
                }
                mListener.fromPlayerProfileScreenTo(OnFragmentInteractionListener.PlayerProfileScreenOptions.GAMESEARCHRESULTLISTSCREEN, games);
            }
        });
        prefw2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                List<Game> games = player.getWhite_games();
                ListIterator<Game> iter = games.listIterator();
                String w = player.getW2();
                while(iter.hasNext()){
                    if(iter.next().getW()[1].equals(w)){
                        iter.remove();
                    }
                }
                mListener.fromPlayerProfileScreenTo(OnFragmentInteractionListener.PlayerProfileScreenOptions.GAMESEARCHRESULTLISTSCREEN, games);
            }
        });
        prefw3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                List<Game> games = player.getWhite_games();
                ListIterator<Game> iter = games.listIterator();
                String w = player.getW3();
                while(iter.hasNext()){
                    if(iter.next().getW()[2].equals(w)){
                        iter.remove();
                    }
                }
                mListener.fromPlayerProfileScreenTo(OnFragmentInteractionListener.PlayerProfileScreenOptions.GAMESEARCHRESULTLISTSCREEN, games);
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
        enum PlayerProfileScreenOptions{
            GAMESEARCHRESULTLISTSCREEN
        }
        public void fromPlayerProfileScreenTo(PlayerProfileScreenOptions option, List<Game> games);
    }

}
