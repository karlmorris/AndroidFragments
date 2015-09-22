package edu.temple.basicfragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    public static final String dataKey = "bundle_data_key";


    TextView headerTextView;
    ImageView planetDisplay;

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        headerTextView = (TextView) v.findViewById(R.id.detailsHeadingTextView);
        planetDisplay = (ImageView) v.findViewById(R.id.planetDisplay);

        if (getArguments() != null && getArguments().getString(dataKey) != null) {
            displayPlanet(getArguments().getString(dataKey));
        }
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_toggle_image){
            setImageVisibility(!isImageVisibile());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayPlanet(String planetName){
        headerTextView.setText(planetName.toUpperCase());

        final String[] planets = getResources().getStringArray(R.array.planets);

        int imageToDisplay = 0;

        if (planetName.equals(planets[0]))
            imageToDisplay = R.drawable.mercury;
        else if (planetName.equals(planets[1]))
            imageToDisplay = R.drawable.venus;
        else if (planetName.equals(planets[2]))
            imageToDisplay = R.drawable.earth;
        else if (planetName.equals(planets[3]))
            imageToDisplay = R.drawable.mars;
        else if (planetName.equals(planets[4]))
            imageToDisplay = R.drawable.jupiter;
        else if (planetName.equals(planets[5]))
            imageToDisplay = R.drawable.saturn;
        else if (planetName.equals(planets[6]))
            imageToDisplay = R.drawable.uranus;
        else if (planetName.equals(planets[7]))
            imageToDisplay = R.drawable.neptune;

        planetDisplay.setImageResource(imageToDisplay);
    }

    /*
     *  Part of our fragment's API. Set visibility of the imagewiew.
     *
     */
    private void setImageVisibility(boolean visibility) {
        if (planetDisplay != null) {
            if (visibility)
                planetDisplay.setVisibility(View.VISIBLE);
            else
                planetDisplay.setVisibility(View.INVISIBLE);
        }
    }

    /*
     *  Part of our fragment's API. Retrieve current visibility of the imageview.
     *
     */
    private boolean isImageVisibile(){
        if (planetDisplay != null)
            return planetDisplay.getVisibility() == View.VISIBLE;
        else
            return false;
    }

}
