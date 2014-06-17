package com.projectchess.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projectchess.app.R;

public class StartScreen extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static StartScreen newInstance() {
        StartScreen fragment = new StartScreen();
        return fragment;
    }

    public StartScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen,container,false);
        Button buttonToPlayerSearch = (Button) view.findViewById(R.id.FSS_player_search_button);
        buttonToPlayerSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.fromStartScreenTo(OnFragmentInteractionListener.StartScreenOptions.PLAYERSEARCHSCREEN);
            }
        });

        Button buttonToGameSearch = (Button) view.findViewById(R.id.FSS_game_search_button);
        buttonToGameSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.fromStartScreenTo(OnFragmentInteractionListener.StartScreenOptions.GAMESEARCHSCREEN);
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
        enum StartScreenOptions{
            PLAYERSEARCHSCREEN,
            GAMESEARCHSCREEN
        };

        public void fromStartScreenTo(StartScreenOptions option);
    }

}
