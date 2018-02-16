package com.example.sisily.puzzle_15;

/**
 * Created by sisily on 15/02/18.
 */

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import com.example.sisily.puzzle_15.MainActivity;

public class PuzzleTestActivity extends ActivityInstrumentationTestCase2<MainActivity>  {

    private MainActivity mActivity;
    private TextView mView;
    private String resourceString;

    public PuzzleTestActivity() {
        super("com.example.sisily.puzzle_15", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        mView = (TextView) mActivity.findViewById
                (R.id.moves);
       // resourceString = mActivity.getString
         //       (com.example.sisily.puzzle_15.R.string.hello);
    }

    public void testPreconditions() {
        assertNotNull(mView);
    }

   // public void testText() {
     //   assertEquals(resourceString,(String)mView.getText());
    //}
}
