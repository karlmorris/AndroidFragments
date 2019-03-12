package edu.temple.basicfragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements NavFragment.OnFragmentInteractionListener {

    boolean twoPanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Determine if only one or two panes are visible
        twoPanes = (findViewById(R.id.fragment_details) != null);

        //  Load navigation fragment by default

        Fragment navFragment = new NavFragment();

        //  Place data for the fragment to use. Must be called before it's attached to an activity.
        Bundle bundle = new Bundle();
        bundle.putStringArray(NavFragment.dataKey, getResources().getStringArray(R.array.planets));
        navFragment.setArguments(bundle);

        loadFragment(R.id.fragment_nav, navFragment, true);

        /*
         *  Check if details pain is visible in current layout (e.g. large or landscape)
         *  and load fragment if true.
         */
        if (twoPanes){
            loadFragment(R.id.fragment_details, DetailsFragment.newInstance(null), true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //  Get the current instance of DetailsFragment if visible and toggle image visibility
        Fragment fragment;
        if (twoPanes)
            fragment = getFragmentManager().findFragmentById(R.id.fragment_details);
        else
            fragment = getFragmentManager().findFragmentById(R.id.fragment_nav);

        if (item.getItemId() == R.id.action_toggle_image && fragment instanceof DetailsFragment){
            ((DetailsFragment) fragment)
                    .setImageVisibility(!((DetailsFragment) fragment).isImageVisibile());
        }

        return super.onOptionsItemSelected(item);
    }

    //  Load fragment in a specified frame
    private void loadFragment(int paneId, Fragment fragment, boolean placeOnBackstack){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction()
                .replace(paneId, fragment);
        if (placeOnBackstack)
                ft.addToBackStack(null);
        ft.commit();

        //  Ensure fragment is attachecd before attempting to call its public methods
         fm.executePendingTransactions();
    }

    @Override
    public void displayPlanetInfo(String planetName) {

        if (twoPanes){
            ((DetailsFragment) getFragmentManager()
                    .findFragmentById(R.id.fragment_details))
                    .setPlanetToShow(planetName);
        } else {
            DetailsFragment fragment = DetailsFragment.newInstance(null);
            loadFragment(R.id.fragment_nav, fragment, true);
            fragment.setPlanetToShow(planetName);
        }

    }


}










