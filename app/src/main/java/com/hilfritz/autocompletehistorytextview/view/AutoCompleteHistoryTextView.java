package com.hilfritz.autocompletehistorytextview.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.hilfritz.autocompletehistorytextview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/**
 * Created by Hilfritz Camallere on 5/7/17.
 */

public class AutoCompleteHistoryTextView extends AppCompatAutoCompleteTextView {
    SharedPreferences sharedpreferences;
    ArrayList<String> historyList = new ArrayList<>();
    public static final String TAG = "AutoCompleteHistoryTextView";
    private String preferenceSaveKey = "";
    ArrayAdapter arrayAdapter = null;
    public AutoCompleteHistoryTextView(Context context) {
        super(context);
        initialize();
    }



    public AutoCompleteHistoryTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public AutoCompleteHistoryTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        setId();
        refreshHistoryList();
    }

    private void setId(){
        final int id = getId();
        if (id==0){
            throw new RuntimeException("AutoCompleteHistoryTextView needs to have an id");
        }
        preferenceSaveKey = String.valueOf(id);
    }
    public SharedPreferences getSharedPreferences(){
        if (sharedpreferences==null){
            sharedpreferences = getContext().getSharedPreferences(AutoCompleteHistoryTextViewUtil.PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return sharedpreferences;
    }

    public void refreshHistoryList(){
        //RETRIEVE THE LIST
        Set<String> stringSet = AutoCompleteHistoryTextViewUtil.getSavedStringSet(this);
        if (stringSet==null){
            Log.d(TAG, "refreshHistoryList: empty history");
            return;
        }

        //CONVERT SET TO ARRAYLIST
        historyList.clear();
        ArrayList<String> list = new ArrayList<>(stringSet);
        historyList.addAll(list);
        Collections.sort(historyList);

        arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.listitem_autocompletehistory, historyList);
        setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        Log.d(TAG, "refreshHistoryList: history list size:"+ historyList.size()+" data:"+ historyList.toString());
    }

    public void saveToHistory(String str){
        add(str);
    }

    private void add(String str){
        if (str!=null && str.isEmpty()==false){
            //ADD TO EXISTING LIST
            historyList.add(str);
            Log.d(TAG, "add: "+str);
            AutoCompleteHistoryTextViewUtil.saveHistoryList(this,historyList);
            refreshHistoryList();
        }
    }

    public String getPreferenceSaveKey() {
        return preferenceSaveKey;
    }

    public void setPreferenceSaveKey(String preferenceSaveKey) {
        this.preferenceSaveKey = preferenceSaveKey;
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        Log.d(TAG, "onFocusChanged: focused:"+focused);
        if (focused) {
            //performFiltering(getText(), 0); //don't use this yet
            //IF LIST IS NOT EMPTY AND TEXT IS NOT EMPTY, FILTER THE LIST
            if (getText()!=null && !isStringEmpty(getText().toString()) && (historyList !=null && historyList.size()>0)){
                performFiltering(getText(), 0);
            }
            showDropDown();
        }
    }

    public boolean isEmptyInput(){
        return isStringEmpty(getText().toString());
    }

    private boolean isStringEmpty(String str){
        if (str != null && !str.isEmpty() && !str.equals("null")){
            return false;
        }
        return true;
    }

    public void reset(){
        AutoCompleteHistoryTextViewUtil.resetHistory(this);
    }

}
