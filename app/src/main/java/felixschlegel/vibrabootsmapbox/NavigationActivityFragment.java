package felixschlegel.vibrabootsmapbox;

import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class NavigationActivityFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_navigation_start, container, false);


        Button btn = (Button) view.findViewById(R.id.btnStart);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        EditText txt = (EditText) getView().findViewById(R.id.txtZiel);
        switch (view.getId()) {
            case R.id.btnStart: {
                String text = txt.getText().toString();

//                Geocoder geocoder = new Geocoder(getActivity());
//                List<Address> addresses;
//                try {
//                    addresses = geocoder.getFromLocationName(text, 1);
//                } catch (IOException e) {
//                    addresses = null;
//                    e.printStackTrace();
//                }
//                if(addresses.size() > 0) {
//                    double latitude= addresses.get(0).getLatitude();
//                    double longitude= addresses.get(0).getLongitude();
//                }


                showOtherFragment();
                break;
            }
        }

    }

    public void showOtherFragment()
    {
        Fragment fr=new NavigationShoesFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }
}
