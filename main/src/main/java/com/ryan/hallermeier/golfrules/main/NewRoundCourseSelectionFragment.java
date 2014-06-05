package com.ryan.hallermeier.golfrules.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.ryan.hallermeier.golfrules.main.models.Course;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class NewRoundCourseSelectionFragment extends Fragment implements AbsListView.OnItemClickListener {

    private GolfFragmentInteractionInterface mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    private NumberPicker myPicker;

    public static NewRoundCourseSelectionFragment newInstance() {
        NewRoundCourseSelectionFragment fragment = new NewRoundCourseSelectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewRoundCourseSelectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<Course>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, mListener.getCourses());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        mListener.setActionBarTitle("Select Course");
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (GolfFragmentInteractionInterface) activity;
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

            showDialog();
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    DialogInterface.OnClickListener mvpdSelectorDialogListener = new DialogInterface.OnClickListener()
    {

        @Override
        public void onClick( DialogInterface dialog, int which )
        {
            switch ( which )
            {
                case DialogInterface.BUTTON_POSITIVE:
                    mListener.onNewRoundCourseSelected(myPicker.getValue());
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }
    };

    void showDialog()
    {
        //IF TEXT IS NOT VISIBLE, BE SURE YOU COMMENTED OUT
        //        mInputText.setFilters(new InputFilter[] {
        //                new InputTextFilter()
        //        });
        //IN NUMBERPICKER (net.simontvt_numberpicker)
        AlertDialog.Builder builder = new AlertDialog.Builder(
                getActivity() );


        // inform the dialog it has a custom View
        // and if you need to call some method of the class
        myPicker = new NumberPicker(getActivity());
        myPicker.setMinValue(1);
        myPicker.setMaxValue( 8 );
        myPicker.setDescendantFocusability( NumberPicker.FOCUS_BLOCK_DESCENDANTS );
        myPicker.setWrapSelectorWheel( false );
        builder.setView( myPicker );
        builder.setTitle("Select Team Number"  )
                .setCancelable( true )
                .setNegativeButton( "Cancel",
                        mvpdSelectorDialogListener )
                .setPositiveButton("OK",
                        mvpdSelectorDialogListener);
        // create the dialog from the builder then show
        builder.create();
        builder.show();
    }

}
