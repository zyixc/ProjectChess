package com.projectchess.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.projectchess.app.R;
import com.projectchess.app.data.DataProvider;
import com.projectchess.app.data.Game;

import java.util.List;

public class CompareScreen extends Fragment {
    private OnFragmentInteractionListener mListener;
    private static Game game;

    public static CompareScreen newInstance(Game linkedgame) {
        CompareScreen fragment = new CompareScreen();
        game = linkedgame;
        return fragment;
    }
    public CompareScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_compare_screen, container, false);
        final TextView min = (TextView) view.findViewById(R.id.FCS_rating_min_editText);
        final TextView max = (TextView) view.findViewById(R.id.FCS_rating_max_editText);
        final RadioGroup result = (RadioGroup) view.findViewById(R.id.FCS_Result_RadioGroup);
        final ProgressBar prbar = (ProgressBar) view.findViewById(R.id.FCS_progressBar);
        prbar.setVisibility(View.INVISIBLE);
        prbar.setMax(3);
        Button search = (Button) view.findViewById(R.id.FCS_Search_Button);
        search.setOnClickListener(new View.OnClickListener() {
            String resultfor = null;
            public void onClick(View v) {
                if(DataProvider.INSTANCE.testConnection()){
                    prbar.setVisibility(View.VISIBLE);
                    prbar.setProgress(1);
                    switch (result.getCheckedRadioButtonId()) {
                        case R.id.FCS_result_white_radioButton:
                            resultfor = "1-0";
                            break;
                        case R.id.FCS_result_black_radioButton:
                            resultfor = "0-1";
                            break;
                        case R.id.FCS_result_draw_radioButton:
                            resultfor = "1/2-1/2";
                            break;
                    }
                    prbar.setProgress(2);
                    List<Game> games = DataProvider.INSTANCE.requestGameList(resultfor, min.getText().toString(),
                            max.getText().toString(), game.getW()[0], game.getW()[1],
                            game.getW()[2], null, null, null, null);
                    prbar.setProgress(3);
                    if(games.isEmpty()){
                        Toast.makeText(view.getContext(), "No Results Found", Toast.LENGTH_SHORT).show();
                    }else {
                        mListener.fromCompareScreenTo(OnFragmentInteractionListener.CompareScreenOptions.GAMESEARCHRESULTLIST, games);
                    }
                }else{
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

    public interface OnFragmentInteractionListener {
        public enum CompareScreenOptions{
            GAMESEARCHRESULTLIST
        }
        public void fromCompareScreenTo(CompareScreenOptions options, List<Game> listOfGames);
    }

}
