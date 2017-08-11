package com.example.myclient2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myclient2.R;
import com.example.myclient2.helper.DBHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteClientFragment extends Fragment {

    private Button button_delete_data;

    private DBHandler dbHandler;

    public static DeleteClientFragment newInstance() {
        DeleteClientFragment fragment = new DeleteClientFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHandler = new DBHandler(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_client, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        button_delete_data = (Button) view.findViewById(R.id.button_delete_data);

        button_delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteAllDataClient();

                Toast.makeText(getActivity(), "Success delete data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
