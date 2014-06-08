package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projectchessapp.projectchess.R;

import java.util.ArrayList;

/**
 * Created by otteran on 6/8/2014.
 */
public class LoadProfileArrayAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private final ArrayList<Item> profileArrayList;

    public LoadProfileArrayAdapter(Context context, ArrayList<Item> profileArrayList, String values[]){

        super(context, R.layout.profile_list_row, profileArrayList);

        this.context = context;
        this.profileArrayList = profileArrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.profile_list_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView player_name = (TextView) rowView.findViewById(R.id.player_name);
        TextView most_recent_rating = (TextView) rowView.findViewById(R.id.most_recent_rating);
        TextView number_of_games = (TextView) rowView.findViewById(R.id.number_of_games);

        // 4. Set the text for textView
        player_name.setText(profileArrayList.get(position).getName());
        most_recent_rating.setText(profileArrayList.get(position).getRating());
        number_of_games.setText(profileArrayList.get(position).getNumber_games());

        // 5. retrn rowView
        return rowView;
    }
}
