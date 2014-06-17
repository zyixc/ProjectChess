package com.projectchess.app.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompareScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompareScreen#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
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
                    mListener.fromCompareScreenTo(OnFragmentInteractionListener.CompareScreenOptions.GAMESEARCHRESULTLIST, games);
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
        public enum CompareScreenOptions{
            GAMESEARCHRESULTLIST
        }
        public void fromCompareScreenTo(CompareScreenOptions options, List<Game> listOfGames);
    }

}
