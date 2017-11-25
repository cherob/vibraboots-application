package felixschlegel.vibrabootsmapbox;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class NavigationActivityFragment extends Fragment implements View.OnClickListener{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button btn = (Button) getActivity().findViewById(R.id.btnStart);
        btn.setOnClickListener(this);
    }

    public NavigationActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_start, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart: {
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
