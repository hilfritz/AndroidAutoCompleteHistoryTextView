package com.hilfritz.autocompletehistorytextview.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hilfritz Camallere on 5/7/17.
 */

public class AutoCompleteHistoryTextViewUtil {
    public static final String PREFERENCE_NAME = "AutoCompleteHistoryTextView-prefs";
    public static final String TAG = "AutoCompleteHistoryTextViewUtil";


    public static final Set<String> getSavedStringSet(AutoCompleteHistoryTextView autoCompleteHistoryTextView){
        final SharedPreferences sharePreferences = autoCompleteHistoryTextView.getSharedPreferences();


        //RETRIEVE THE LIST
        Set<String> stringSet = sharePreferences.getStringSet(autoCompleteHistoryTextView.getPreferenceSaveKey(), null);
        if (stringSet==null){
            Log.d(TAG, "refreshHistoryList: empty history");
            return null;
        }
        return  stringSet;
    }

    public static final void saveHistoryList(AutoCompleteHistoryTextView autoCompleteHistoryTextView, ArrayList<String> historyList){
        //CONVERT TO SET
        Set<String> set = new HashSet<String>();
        set.addAll(historyList);
        String preferenceSaveKey = autoCompleteHistoryTextView.getPreferenceSaveKey();
        Log.d(TAG, "saveHistoryList: [preferenceSaveKey="+preferenceSaveKey+"]list size to save:"+historyList+" data:"+historyList.toString());

        //SAVE THE SET
        saveTheSet(autoCompleteHistoryTextView, set, preferenceSaveKey);
    }

    public static final void resetHistory(AutoCompleteHistoryTextView autoCompleteHistoryTextView){
        String preferenceSaveKey = autoCompleteHistoryTextView.getPreferenceSaveKey();
        //SAVE THE SET
        saveTheSet(autoCompleteHistoryTextView, null, preferenceSaveKey);
    }


    private static final ArrayList<String> getExistingSavedStringSetHistory(AutoCompleteHistoryTextView autoCompleteHistoryTextView, int autoCompleteResourceIdSource){
        //CONVERT SET TO ARRAYLIST
        ArrayList<String> list = new ArrayList<>();
        Set<String> stringSet = AutoCompleteHistoryTextViewUtil.getSavedStringSet(autoCompleteHistoryTextView);
        if (stringSet==null){
            Log.d(TAG, "getExistingSavedHistory: empty history");
            return list;
        }
        //CONVERT SET TO ARRAYLIST
        return new ArrayList<>(stringSet);
    }
    private static final boolean saveHistoryToAutoCompleteEdittext(AutoCompleteHistoryTextView autoCompleteHistoryTextView, int autoCompleteResourceIdTarget, ArrayList<String> existingList){
        //CONVERT TO SET
        Set<String> set = new HashSet<String>();
        set.addAll(existingList);
        Log.d(TAG, "saveHistoryToAutoCompleteEdittext: list size to save:"+existingList+" data:"+existingList.toString());

        String preferenceSaveKey = String.valueOf(autoCompleteResourceIdTarget);

        //SAVE THE SET
        saveTheSet(autoCompleteHistoryTextView, set, preferenceSaveKey);
        return true;
    }

    private static void saveTheSet(AutoCompleteHistoryTextView autoCompleteHistoryTextView, Set<String> set, String preferenceSaveKey) {
        SharedPreferences.Editor editor = autoCompleteHistoryTextView.getSharedPreferences().edit();
        editor.putStringSet(preferenceSaveKey,set);
        editor.commit();
    }



}
