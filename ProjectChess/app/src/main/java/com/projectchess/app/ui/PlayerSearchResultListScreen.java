package com.projectchess.app.ui;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectchess.app.R;
import com.projectchess.app.data.DataProvider;
import com.projectchess.app.data.Player;

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
    public static List<Player> playerlist;

    public static PlayerSearchResultListScreen newInstance(String playerLastName) {
        PlayerSearchResultListScreen fragment = new PlayerSearchResultListScreen();
        playerlist = DataProvider.INSTANCE.requestPlayerList(playerLastName);
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
        View view = inflater.inflate(R.layout.fragment_player_search_screen,container,false);



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

}
