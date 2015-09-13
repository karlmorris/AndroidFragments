package edu.temple.basicfragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements NavFragment.OnFragmentInteractionListener {

    boolean twoPanes;
    Fragment detailsFragment;

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

        loadFragment(R.id.fragment_nav, navFragment, false);

        /*
         *  Check if details pain is visible in current layout (e.g. large or landscape)
         *  and load fragment if true.
         */
        if (twoPanes){
            loadFragment(R.id.fragment_details, new DetailsFragment(), false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_toggle_image && detailsFragment != null){
            ((DetailsFragment) detailsFragment)
                    .setImageVisibility(!((DetailsFragment) detailsFragment).isImageVisibile());
        }

        return super.onOptionsItemSelected(item);
    }

    //  Load fragment in a specified frame
    private void loadFragment(int paneId, Fragment fragment, boolean placeOnBackstack){
        FragmentTransaction ft = getFragmentManager()
                .beginTransaction()
                .replace(paneId, fragment);
        if (placeOnBackstack)
                ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void displayPlanetInfo(String planetName) {
        detailsFragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(DetailsFragment.dataKey, planetName);
        detailsFragment.setArguments(bundle);

        loadFragment(twoPanes ? R.id.fragment_details : R.id.fragment_nav, detailsFragment, !twoPanes);
    }


}
