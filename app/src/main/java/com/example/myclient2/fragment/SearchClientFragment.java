package com.example.myclient2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.myclient2.R;
import com.example.myclient2.helper.DBHelperProvinceName;

/**
 * Created by iddan on 08/08/17.
 */

public class SearchClientFragment extends Fragment {

    private AutoCompleteTextView autoCompleteProvinceName;
    private AutoCompleteTextView autoCompleteProvinceNameDB;
    String[] provinceName = {"Jawa Barat", "Jawa Tengah", "Jawa Timur", "Kalimantan", "Sulawesi", "Bali"};
    private DBHelperProvinceName dbHelperProvinceName;

    public static SearchClientFragment newInstance() {
        SearchClientFragment fragment = new SearchClientFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelperProvinceName = new DBHelperProvinceName(getActivity());
        dbHelperProvinceName.loadContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_client, container, false);
        initAutoCompleteProvinceName(view);
        initAutoCompleteProvinceNameDB(view);
        return view;
    }

    private void initAutoCompleteProvinceName(View view) {
        autoCompleteProvinceName = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteProvinceName);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, provinceName);
        autoCompleteProvinceName.setAdapter(adapter);
        autoCompleteProvinceName.setThreshold(1);

        autoCompleteProvinceName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), (CharSequence) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAutoCompleteProvinceNameDB(View view) {
        autoCompleteProvinceNameDB = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteProvinceNameDB);

        final String[] provNameDB = dbHelperProvinceName.SelectAllDataProvinceName();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, provNameDB);
        autoCompleteProvinceNameDB.setAdapter(adapter);
        autoCompleteProvinceNameDB.setThreshold(1);
        autoCompleteProvinceNameDB.dismissDropDown();

        autoCompleteProvinceNameDB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), (CharSequence) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
