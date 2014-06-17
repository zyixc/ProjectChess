package com.projectchess.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projectchess.app.R;
import com.projectchess.app.data.DataProvider;
import com.projectchess.app.data.Player;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerSearchScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerSearchScreen#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PlayerSearchScreen extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static PlayerSearchScreen newInstance() {
        PlayerSearchScreen fragment = new PlayerSearchScreen();
        return fragment;
    }
    public PlayerSearchScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_player_search_screen,container,false);
        final EditText lastName = (EditText) view.findViewById(R.id.FPSS_lastname_editText);
        Button buttonPlayerSearch = (Button) view.findViewById(R.id.FPSS_search_button);

        buttonPlayerSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (DataProvider.INSTANCE.testConnection()) {
                    if (!lastName.getText().toString().equals("")) {
                        List<Player> playerList = DataProvider.INSTANCE.requestPlayerList(lastName.getText().toString());
                        if(playerList.isEmpty()){
                            Toast.makeText(view.getContext(), "No Results Found", Toast.LENGTH_SHORT).show();
                        }else {
                            mListener.fromPlayerSearchScreenTo(OnFragmentInteractionListener
                                    .PlayerSearchScreenOptions.PLAYERSEARCHRESULTLISTSCREEN, playerList);
                        }
                    } else {
                        Toast.makeText(view.getContext(), "Enter a Name", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Connection failed", Toast.LENGTH_SHORT).show();
                }
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
        enum PlayerSearchScreenOptions{
            PLAYERSEARCHRESULTLISTSCREEN
        }

        public void fromPlayerSearchScreenTo(PlayerSearchScreenOptions option, List<Player> playerList);
    }

}
