package com.example.myclient2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myclient2.R;
import com.example.myclient2.adapter.ClientAdapter;
import com.example.myclient2.helper.DBHandler;
import com.example.myclient2.model.Client;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddClientFragment extends Fragment {

    private EditText et_name;
    private EditText et_address;
    private Button button_add_data;

    private DBHandler dbHandler;
    private ClientAdapter adapter;

    public static AddClientFragment newInstance() {
        AddClientFragment fragment = new AddClientFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHandler = new DBHandler(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_address = (EditText) view.findViewById(R.id.et_address);
        button_add_data = (Button) view.findViewById(R.id.button_add_data);

        button_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationForm();
            }
        });
    }

    private void validationForm() {
        String form_name = et_name.getText().toString();
        String form_address = et_address.getText().toString();

        if (form_name.isEmpty()) {
            et_name.setError("is required");
            et_name.requestFocus();
        } else if (form_address.isEmpty()) {
            et_address.setError("is required");
            et_address.requestFocus();
        } else {
            dbHandler.addClient(new Client(form_name, form_address));
            List<Client> clientList = dbHandler.getAllClient();
            adapter = new ClientAdapter(clientList);
            adapter.notifyDataSetChanged();

            et_name.setText("");
            et_address.setText("");

            Toast.makeText(getActivity(), "Success add data", Toast.LENGTH_SHORT).show();
        }
    }

}
