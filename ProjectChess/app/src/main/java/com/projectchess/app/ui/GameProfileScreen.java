package com.projectchess.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.projectchess.app.R;
import com.projectchess.app.data.Game;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameProfileScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameProfileScreen#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class GameProfileScreen extends Fragment {
    private OnFragmentInteractionListener mListener;
    private static Game game;

    public static GameProfileScreen newInstance(Game linkedgame) {
        GameProfileScreen fragment = new GameProfileScreen();
        game = linkedgame;
        return fragment;
    }
    public GameProfileScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_game_profile_screen, container, false);
        final TextView white = (TextView) view.findViewById(R.id.fGPS_White_Put_TextView);
        final TextView black = (TextView) view.findViewById(R.id.fGPS_Black_Put_TextView);
        final TextView result = (TextView) view.findViewById(R.id.fGPS_Result_Put_TextView);
        final Button compare = (Button) view.findViewById(R.id.fGPS_Compare_Button);
        WebView webview = (WebView) view.findViewById(R.id.fGPS_WebView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://treegarden.nl/chess/index.html?moves="+game.getMoves());
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.fromGameProfileScreenTo(OnFragmentInteractionListener.GameProfileScreenOptions.COMPARESCREEN, game);
            }
        });

        white.setText(game.getWhite());
        black.setText(game.getBlack());
        result.setText(game.getResult());

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
        public enum GameProfileScreenOptions{
            COMPARESCREEN
        }

        public void fromGameProfileScreenTo(GameProfileScreenOptions options, Game game);
    }

}
