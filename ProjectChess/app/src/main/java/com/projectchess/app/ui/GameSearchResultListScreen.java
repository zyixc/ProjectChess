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
import com.projectchess.app.data.Game;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameSearchResultListScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameSearchResultListScreen#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class GameSearchResultListScreen extends Fragment {
    private OnFragmentInteractionListener mListener;
    private static List<Game> listOfGames;

    public static GameSearchResultListScreen newInstance(List<Game> linkedgames) {
        GameSearchResultListScreen fragment = new GameSearchResultListScreen();
        listOfGames = linkedgames;
        return fragment;
    }
    public GameSearchResultListScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_game_search_result_list_screen, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.FGSR_game_results_listView);
        final GameArrayAdapter adapter = new GameArrayAdapter(view.getContext(), listOfGames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,final View view, int position, long id) {
                Game game = (Game) listView.getAdapter().getItem(position);
                mListener.fromGameSearchResultListTo(OnFragmentInteractionListener.GameSearchResultListScreenOptions.GAMEPROFILESCREEN, game);
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
        enum GameSearchResultListScreenOptions{
            GAMEPROFILESCREEN
        }
        public void fromGameSearchResultListTo(GameSearchResultListScreenOptions options, Game game);
    }

    private class GameArrayAdapter extends ArrayAdapter<Game> {
        private final Context context;
        private final List<Game> gamesList;

        public GameArrayAdapter(Context context, List<Game> games) {
            super(context, R.layout.fragment_game_search_result_list_row, games);
            this.gamesList = games;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_game_search_result_list_row,parent,false);
                ViewHolderItem viewHolder = new ViewHolderItem();
                viewHolder.nameWhite = (TextView) convertView.findViewById(R.id.fGSRLSR_nameWhite_TextView);
                viewHolder.ratingWhite = (TextView) convertView.findViewById(R.id.fGSRLSR_ratingWhite_TextView);
                viewHolder.nameBlack = (TextView) convertView.findViewById(R.id.fGSRLSR_nameBlack_TextView);
                viewHolder.ratingBlack = (TextView) convertView.findViewById(R.id.fGSRLSR_ratingBlack_TextView);
                viewHolder.result = (TextView) convertView.findViewById(R.id.fGSRLSR_Result_TextView);

                convertView.setTag(viewHolder);
            }

            ViewHolderItem viewHolder = (ViewHolderItem) convertView.getTag();
            Game game = gamesList.get(position);
            viewHolder.nameWhite.setText(game.getWhite());
            viewHolder.ratingWhite.setText(Integer.toString(game.getWhite_elo()));
            viewHolder.nameBlack.setText(game.getBlack());
            viewHolder.ratingBlack.setText(Integer.toString(game.getBlack_elo()));
            viewHolder.result.setText(game.getResult());
            return convertView;
        }
    }

    static class ViewHolderItem{
        TextView nameWhite;
        TextView ratingWhite;
        TextView nameBlack;
        TextView ratingBlack;
        TextView result;
    }
}
