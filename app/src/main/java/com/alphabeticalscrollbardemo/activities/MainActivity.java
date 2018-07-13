package com.alphabeticalscrollbardemo.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alphabeticalscrollbardemo.R;
import com.alphabeticalscrollbardemo.adapters.ListAdapter;
import com.alphabeticalscrollbardemo.model.AlphabetItem;
import com.alphabeticalscrollbardemo.model.ListModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lstView)
    RecyclerView lstView;
    @BindView(R.id.txtNoDataFound)
    TextView txtNoDataFound;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.fast_scroller)
    RecyclerViewFastScroller fastScroller;
    ArrayList<ListModel> objList = new ArrayList();

    private List<AlphabetItem> mAlphabetItems;

    List<String> strAlphabets = null;
    boolean isAtoZ = true;
    ListAdapter listAdapter = null;
    private String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialization();
    }

    private void initialization() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(MainActivity.this);
        lstView.setAdapter(listAdapter);
        dataList();

        setAlphbatesList(listAdapter.objList);


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchData();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void dataList() {

        ListModel model = new ListModel();
        model.id = "1";
        model.name = "aa";
        objList.add(model);

        model = new ListModel();
        model.id = "2";
        model.name = "bb";
        objList.add(model);

        model = new ListModel();
        model.id = "3";
        model.name = "cc";
        objList.add(model);

        model = new ListModel();
        model.id = "4";
        model.name = "ee";
        objList.add(model);


        model = new ListModel();
        model.id = "5";
        model.name = "gg";
        objList.add(model);


        model = new ListModel();
        model.id = "6";
        model.name = "hh";
        objList.add(model);


        model = new ListModel();
        model.id = "7";
        model.name = "ii";
        objList.add(model);


        model = new ListModel();
        model.id = "8";
        model.name = "jj";
        objList.add(model);

        model = new ListModel();
        model.id = "9";
        model.name = "ll";
        objList.add(model);

        model = new ListModel();
        model.id = "10";
        model.name = "ee";
        objList.add(model);

        model = new ListModel();
        model.id = "11";
        model.name = "mm";
        objList.add(model);

        model = new ListModel();
        model.id = "12";
        model.name = "nn";
        objList.add(model);

        model = new ListModel();
        model.id = "13";
        model.name = "oo";
        objList.add(model);

        model = new ListModel();
        model.id = "14";
        model.name = "pp";
        objList.add(model);

        model = new ListModel();
        model.id = "15";
        model.name = "pj";
        objList.add(model);
        listAdapter.addData(objList);
    }

    public void searchData() {
        if (objList == null || objList.size() <= 0)
            return;

        ArrayList<ListModel> tempList = null;
        if (edtSearch.getText().toString().trim().isEmpty() || edtSearch.getText().toString().trim().length() <= 0) {
            Log.e("","============FIRST IN >>>>");
            tempList = new ArrayList<>();
            tempList.addAll(objList);
            setAlphbatesList(tempList);
        } else if (searchText.isEmpty() || !searchText.equalsIgnoreCase(edtSearch.getText().toString())) {
            Log.e("","============SECOND IN >>>>");
            searchText = edtSearch.getText().toString().trim();
            tempList = new ArrayList<>();
            if (searchText.isEmpty() || searchText.length() <= 0) {
                tempList.addAll(objList);
            } else {
                for (ListModel model : objList) {
                    if (model.name.toLowerCase(Locale.getDefault()).contains(searchText.toLowerCase())) {
                        tempList.add(model);
                    }
                }
            }
            setAlphbatesList(tempList);

        }

    }

    private void setAlphbatesList(ArrayList<ListModel> list) {

        if (list == null || list.size() <= 0) {
            Log.e("","============THIRD IN >>>>");
            lstView.setVisibility(View.GONE);
            txtNoDataFound.setVisibility(View.VISIBLE);
            fastScroller.setVisibility(View.INVISIBLE);
        } else {

            Log.e("","============FOURTH IN >>>>" +list.size());
            if (listAdapter == null) {
                listAdapter = new ListAdapter(MainActivity.this);
                lstView.setAdapter(listAdapter);
            }

            listAdapter.addData(list);
           /* Collections.sort(listAdapter.objList, new Comparator<ListModel>() {
                @Override
                public int compare(ListModel s1, ListModel s2) {
                    if (isAtoZ)
                        return s1.name.compareToIgnoreCase(s2.name);

                }
            });*/
            lstView.setVisibility(View.VISIBLE);
            fastScroller.setVisibility(View.VISIBLE);
            txtNoDataFound.setVisibility(View.GONE);

            mAlphabetItems = new ArrayList<>();
            strAlphabets = new ArrayList<>();
            for (int i = 0; i < listAdapter.objList.size(); i++) {
                String name = isAtoZ ? listAdapter.objList.get(i).name : "";
//                name = name.contains(" ") ? name.split(" ")[1] : name;
                if (name == null || name.trim().isEmpty())
                    continue;

                String word = isAtoZ ? name.substring(0, 1).toUpperCase(Locale.getDefault()) : name;
                if (!strAlphabets.contains(word)) {
                    strAlphabets.add(word);
                    mAlphabetItems.add(new AlphabetItem(i, word, false));
                }
            }

            fastScroller.setRecyclerView(lstView);
            fastScroller.setUpAlphabet(mAlphabetItems);
            listAdapter.notifyDataSetChanged();
        }

    }
}
