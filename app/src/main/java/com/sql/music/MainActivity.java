package com.sql.music;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.sql.music.myapplication.R;

/**
 * Created by giro on 29/05/16.
 */
public class MainActivity extends FragmentActivity
       // implements EmpDialogFragmentListener
{

    private Fragment contentFragment;
    private ListOfArtistsFragment artistListFragment;
    private ArtAddFragment artAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        FragmentManager fragmentManager = getSupportFragmentManager();

		/*
		 * This is called when orientation is changed.
		 */
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("content")) {
                String content = savedInstanceState.getString("content");
                if (content.equals(artAddFragment.ARG_ITEM_ID)) {
                    if (fragmentManager
                            .findFragmentByTag(artAddFragment.ARG_ITEM_ID) != null) {
                        setFragmentTitle(R.string.app_add_user);
                        contentFragment = fragmentManager
                                .findFragmentByTag(artAddFragment.ARG_ITEM_ID);
                    }
                }
            }
            if (fragmentManager.findFragmentByTag(
                    ListOfArtistsFragment.ARG_ITEM_ID) != null) {
                artistListFragment = (ListOfArtistsFragment) fragmentManager
                        .findFragmentByTag(ListOfArtistsFragment.ARG_ITEM_ID);
                contentFragment =  artistListFragment;
            }
        } else {
            artistListFragment = new    ListOfArtistsFragment();
           // setFragmentTitle(R.string.app_name);
            switchContent(artistListFragment, ListOfArtistsFragment.ARG_ITEM_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (contentFragment instanceof ArtAddFragment) {
            outState.putString("content", ArtAddFragment.ARG_ITEM_ID);
        } else {
            outState.putString("content", ListOfArtistsFragment.ARG_ITEM_ID);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_art:
                setFragmentTitle(R.string.app_add_user);
                artAddFragment = new ArtAddFragment();
                switchContent( artAddFragment, ArtAddFragment.ARG_ITEM_ID);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * We consider EmpListFragment as the home fragment and it is not added to
     * the back stack.
     */
    public void switchContent(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.popBackStackImmediate());

        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            transaction.replace(R.id.content_frame, fragment, tag);
            // Only EmpAddFragment is added to the back stack.
            if (!(fragment instanceof ListOfArtistsFragment)) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
            contentFragment = fragment;
        }
    }

    protected void setFragmentTitle(int resourseId) {
        setTitle(resourseId);
        //getActionBar().setTitle(resourseId);

    }

    /*
     * We call super.onBackPressed(); when the stack entry count is > 0. if it
     * is instanceof EmpListFragment or if the stack entry count is == 0, then
     * we prompt the user whether to quit the app or not by displaying dialog.
     * In other words, from EmpListFragment on back press it quits the app.
     */
    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else if (contentFragment instanceof ListOfArtistsFragment
                || fm.getBackStackEntryCount() == 0) {
            //Shows an alert dialog on quit
            onShowQuitDialog();
        }
    }

    public void onShowQuitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        builder.setMessage("Do You Want To Quit?");
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }
/*
    @Override
    public void onFinishDialog() {
        if (artistListFragment != null) {
            artistListFragment.updateView();
        }
    }
    */


}
