package com.projectchess.app.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.projectchess.app.R;
import com.projectchess.app.data.DataProvider;
import com.projectchess.app.data.Game;

import java.util.List;

public class GameSearchScreen extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static GameSearchScreen newInstance() {
        GameSearchScreen fragment = new GameSearchScreen();
        return fragment;
    }
    public GameSearchScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_game_search_screen, container, false);
        final RadioGroup result = (RadioGroup) view.findViewById(R.id.fGSS_Result_RadioGroup);
        final EditText min = (EditText) view.findViewById(R.id.fGSS_Rating_EditText_Min);
        final EditText max = (EditText) view.findViewById(R.id.fGSS_Rating_EditText_Max);
        final EditText w1 = (EditText) view.findViewById(R.id.fGSS_WhiteOpening_EditText_1st);
        final EditText w2 = (EditText) view.findViewById(R.id.fGSS_WhiteOpening_EditText_2nd);
        final EditText w3 = (EditText) view.findViewById(R.id.fGSS_WhiteOpening_EditText_3rd);
        final EditText b1 = (EditText) view.findViewById(R.id.fGSS_BlackOpening_EditText_1st);
        final EditText b2 = (EditText) view.findViewById(R.id.fGSS_BlackOpening_EditText_2nd);
        final EditText b3 = (EditText) view.findViewById(R.id.fGSS_BlackOpening_EditText_3rd);
        final EditText eco = (EditText) view.findViewById(R.id.fGSS_Eco_EditText);
        final ProgressBar prbar = (ProgressBar) view.findViewById(R.id.fGSS_ProgressBar);
        prbar.setVisibility(View.INVISIBLE);
        prbar.setMax(3);
        Button search = (Button) view.findViewById(R.id.fGSS_Search_Button);
        search.setOnClickListener(new View.OnClickListener() {
            String resultfor = null;
            public void onClick(View v) {
                if(DataProvider.INSTANCE.testConnection()){
                    prbar.setVisibility(View.VISIBLE);
                    prbar.setProgress(1);
                    switch (result.getCheckedRadioButtonId()) {
                        case R.id.fGSS_Result_RadioButton_White:
                            resultfor = "1-0";
                            break;
                        case R.id.fGSS_Result_RadioButton_Black:
                            resultfor = "0-1";
                            break;
                        case R.id.fGSS_Result_RadioButton_Draw:
                            resultfor = "1/2-1/2";
                            break;
                        default:
                            resultfor= "null";
                            break;
                    }
                    prbar.setProgress(2);
                    List<Game> games = DataProvider.INSTANCE.requestGameList(resultfor, min.getText().toString(),
                            max.getText().toString(), w1.getText().toString(), w2.getText().toString(),
                            w3.getText().toString(), b1.getText().toString(), b2.getText().toString(),
                            b3.getText().toString(), eco.getText().toString());
                    prbar.setProgress(3);
                    if(games.isEmpty()){
                        Toast.makeText(view.getContext(), "No Results Found", Toast.LENGTH_SHORT).show();
                    }else {
                        mListener.fromGameSearchScreenTo(OnFragmentInteractionListener.GameSearchScreenOptions.GAMESEARCHRESULTLISTSCREEN, games);
                    }
                }else{
                    Toast.makeText(view.getContext(),"Connection failed",Toast.LENGTH_SHORT).show();
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
        enum GameSearchScreenOptions{
            GAMESEARCHRESULTLISTSCREEN
        }

        public void fromGameSearchScreenTo(GameSearchScreenOptions option, List<Game> games);
    }

}
