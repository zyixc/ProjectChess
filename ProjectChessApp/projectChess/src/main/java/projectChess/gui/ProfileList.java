package com.projectchessapp.projectchess;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import adapters.Item;
import adapters.LoadProfileArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProfileList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getMenuInflater().inflate(R.menu.game_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MoveSearch.class);
        startActivityForResult(myIntent, 0);
        int id = item.getItemId();
        if (id == R.id.settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile_list, container, false);
            final ListView listview = (ListView) rootView.findViewById(R.id.profile_list_view);
            //hardcoded names can be deleted
            String[] values = new String[] { "Julita", "kevin", "Tobi",
                    "ahhaha", "paul", "Ubu", "Win", "Mama",
                    "Linda", "Oliver", "ntu", "Wind", "MaX", "nux",
                    "ama", "ng", "Willem", "Xavier", "nuxer", "OSer",
                    "thomas", "mat", "Don" };

            LoadProfileArrayAdapter adapter = new LoadProfileArrayAdapter(this, generateData());
            ListView listView = (ListView) rootView.findViewById(R.id.profile_list_view);

            listView.setAdapter(adapter);


            private ArrayList<Item> generateData(){
                ArrayList<Item> profile = new  ArrayList<Item>();
                for (int i = 0; i < values.length; ++i)
                    //what is best to show?
                profile.add(new Item("", "", ""));
            }

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {

                    final int lastItem = firstVisibleItem + visibleItemCount;
                    if (lastItem == totalItemCount) {
                        //load more data
                    }
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);

                    Intent profile_detail = new Intent(getActivity(), ProfileDetail.class);
                    getActivity().startActivity(profile_detail);
                }

            });
            return rootView;


        }



        private class StableArrayAdapter extends ArrayAdapter<String> {

            HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

            public StableArrayAdapter(Context context, int textViewResourceId,
                                      List<String> objects) {
                super(context, textViewResourceId, objects);
                for (int i = 0; i < objects.size(); ++i) {
                    mIdMap.put(objects.get(i), i);
                }
            }

            @Override
            public long getItemId(int position) {
                String item = getItem(position);
                return mIdMap.get(item);
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

        }
    }
}
