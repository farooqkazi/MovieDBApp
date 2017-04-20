package info.androidhive.retrofit.utilities;

import android.view.View;

/**
 * Created by Cameron Stobie - Waracle tech test  on 10/14/2015.
 */
public interface ItemClickListener {
    /**
     *
     * @param view
     * @param position
     * @param isLongClick
     */
    void onClick(View view, int position, boolean isLongClick);

}
