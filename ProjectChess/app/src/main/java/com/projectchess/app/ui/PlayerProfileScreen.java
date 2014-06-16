package com.projectchess.app.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectchess.app.R;
import com.projectchess.app.data.Player;

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
        View view = inflater.inflate(R.layout.fragment_player_profile_screen, container, false);
        TextView name = (TextView) view.findViewById(R.id.FPPS_player_profile_name_textView);
        TextView rating = (TextView) view.findViewById(R.id.FPPS_player_profile_rating_textView);
        TextView numberofgames = (TextView) view.findViewById(R.id.FPPS_player_profile_number_of_games_textView);
        TextView prefw1 = (TextView) view.findViewById(R.id.FPPS_player_profile_pref_w1);
        TextView prefw2 = (TextView) view.findViewById(R.id.FPPS_player_profile_pref_w2);
        TextView prefw3 = (TextView) view.findViewById(R.id.FPPS_player_profile_pref_w3);

        name.setText(player.getFirstname()+" "+player.getLastname());
        rating.setText(player.getRating());
        numberofgames.setText(player.getNumberofgames());
        prefw1.setText(player.getW1());
        prefw2.setText(player.getW2());
        prefw3.setText(player.getW3());
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
            GAMESEARCHRESULTSCREEN;
        }
        public void fromPlayerProfileScreenTo(PlayerProfileScreenOptions option); //TODO
    }

}
