package felixschlegel.vibrabootsmapbox;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Observable;
import java.util.Observer;

import static felixschlegel.vibrabootsmapbox.BlinkController.*;
import static felixschlegel.vibrabootsmapbox.BlinkController.BLINKLEFT;
import static felixschlegel.vibrabootsmapbox.BlinkController.BLINKRIGHT;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavigationShoesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigationShoesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationShoesFragment extends Fragment implements Observer{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ImageView rightShoe;
    private ImageView leftShoe;

    public NavigationShoesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationShoesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationShoesFragment newInstance(String param1, String param2) {
        NavigationShoesFragment fragment = new NavigationShoesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        App.getInstance().getBlinkController().startRun();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_navigation_shoes, container, false);
        leftShoe = (ImageView) view.findViewById(R.id.left_shoe);
        rightShoe = (ImageView) view.findViewById(R.id.right_shoe);
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        update(null,null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        App.getInstance().getBlinkController().addObserver(this);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

        App.getInstance().getBlinkController().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        final int state = App.getInstance().getBlinkController().getState();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (state){
                    case BLINKNONE:
                        leftShoe.setImageResource(R.drawable.inactive_left);
                        rightShoe.setImageResource(R.drawable.inactive_right);
                        break;
                    case BLINKLEFT:
                        leftShoe.setImageResource(R.drawable.green_left);
                        rightShoe.setImageResource(R.drawable.inactive_right);
                        App.getInstance().getShoeCommunication().leftShoeAction(3);
                        break;
                    case BLINKRIGHT:
                        leftShoe.setImageResource(R.drawable.inactive_left);
                        rightShoe.setImageResource(R.drawable.green_right);
                        App.getInstance().getShoeCommunication().rightShoeAction(3);
                        break;
                }
            }
        });
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
        //TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
