package com.example.myclient2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myclient2.R;
import com.example.myclient2.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iddan on 04/08/17.
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private List<Client> clientList = new ArrayList<>();

    public ClientAdapter(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public ClientAdapter.ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_client, parent, false);
        ClientViewHolder clientViewHolder = new ClientViewHolder(view);

        return clientViewHolder;
    }

    @Override
    public void onBindViewHolder(ClientAdapter.ClientViewHolder holder, int position) {
        holder.textResultName.setText(clientList.get(position).getName());
        holder.textResultAddress.setText(clientList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView textResultName;
        TextView textResultAddress;

        public ClientViewHolder(View itemView) {
            super(itemView);

            textResultName = (TextView) itemView.findViewById(R.id.textResultName);
            textResultAddress = (TextView) itemView.findViewById(R.id.textResultAddress);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
