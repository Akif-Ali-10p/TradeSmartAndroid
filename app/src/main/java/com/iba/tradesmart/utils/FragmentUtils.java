package com.iba.tradesmart.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created on 2/11/2016.
 */
public class FragmentUtils {
    /**
     * Push fragment into container
     *
     * @param activity       required to get fragment manager
     * @param containerId    id of container where fragment will be added
     * @param fragment       fragment to add
     * @param bundle         data for fragment
     * @param tag            name of fragment to add
     * @param addToBackStack true if you want to add in back stack
     */
    public static void pushFragment(AppCompatActivity activity, int containerId, Fragment fragment, Bundle bundle, String tag, boolean addToBackStack/*, boolean animate*/) {

        try {
            if (fragment == null) {
                return;
            }

            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//            if (animate) {
//                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right);
//            }

            if (addToBackStack) {
                ft.addToBackStack(null);
            }

            if (!fragment.isAdded()) {
                ft.replace(containerId, fragment, tag).commit();
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove added fragment from specific container id
     *
     * @param activity    to get Fragment Manager
     * @param containerId of frame layout
     */
    public static void removeFragmentFromContainer(AppCompatActivity activity, int containerId) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        if (ft != null) {
            ft.remove(activity.getSupportFragmentManager().findFragmentById(containerId));
            ft.commit();
        }
    }

    /**
     * Removes all fragments from backstack
     *
     * @param activity required to get fragment manager
     */
    public static void removeAllFragmentsFromBackStack(AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            int count = fragmentManager.getBackStackEntryCount();
            for (int i = count; i >= 0; i--) {
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    public static int getFragmentBackStackCount(AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        return (fragmentManager != null) ? fragmentManager.getBackStackEntryCount() : 0;
    }

    public static void popFragment(AppCompatActivity activity, Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }

    /**
     * Checks if specific fragment is in back stack
     *
     * @param activity required to get fragment manager
     * @param tag      of fragment to find
     * @return
     */
    public static boolean isFragmentInStack(AppCompatActivity activity, String tag) {
        boolean inStack = false;

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                inStack = true;
            }
        }

        return inStack;
    }

    public static Fragment getFragmentByTag(AppCompatActivity activity, String tag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null)
                return fragment;
        }
        return null;
    }


    /**
     *
     * Implements default onBackPressed() of activity from any other class
     * @param activity required to get fragment manager
     */
    public static void onBackPressed(AppCompatActivity activity){

        if(getFragmentBackStackCount(activity) > 0){
            activity.getSupportFragmentManager().popBackStack();
        }
        else{
            activity.finish();
        }
    }

}
