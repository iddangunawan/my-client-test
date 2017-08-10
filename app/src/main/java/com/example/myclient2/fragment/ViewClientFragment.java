package com.example.myclient2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclient2.R;
import com.example.myclient2.adapter.ClientAdapter;
import com.example.myclient2.helper.DBHandler;
import com.example.myclient2.helper.RecyclerItemClickListener;
import com.example.myclient2.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewClientFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ClientAdapter adapter;
    private DBHandler dbHandler;
    private TextView textResultAdapter;
    private List<Client> clientList = new ArrayList<>();

    public static ViewClientFragment newInstance() {
        ViewClientFragment fragment = new ViewClientFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_client, container, false);

        initComponents(view);
        initRecyclerView(view);
        checkDataRecyclerView(view);

        return view;
    }

    // Function init recyclerview with adapter
    private void initRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_client);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DBHandler(getActivity());
        clientList = dbHandler.getAllClient();
        adapter = new ClientAdapter(clientList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents(View view) {
        textResultAdapter = (TextView) view.findViewById(R.id.textResultAdapter);
    }

    // Function check data already or not on recylerview
    private  void checkDataRecyclerView(View view) {
        if (adapter.getItemCount() == 0) {
            textResultAdapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            textResultAdapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Client cln = clientList.get(position);
                        String name = cln.getName();

                        Toast.makeText(getActivity(), "Click in " + name, Toast.LENGTH_SHORT).show();
                    }
                })
            );
        }
    }
}
