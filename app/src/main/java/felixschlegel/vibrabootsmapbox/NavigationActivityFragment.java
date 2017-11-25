package felixschlegel.vibrabootsmapbox;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class NavigationActivityFragment extends Fragment implements View.OnClickListener{


    public NavigationActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_start, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}
