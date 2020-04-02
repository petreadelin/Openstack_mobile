package com.petrique.openstack.OverviewFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.petrique.openstack.R;

import java.util.List;

public class EndpointsAdapter extends RecyclerView.Adapter<EndpointsAdapter.MyViewHolder> {

    private List<Endpoints> endpointsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView region, type, name;

        public MyViewHolder(View view) {
            super(view);
            region = (TextView) view.findViewById(R.id.region);
            name = (TextView) view.findViewById(R.id.name);
            type = (TextView) view.findViewById(R.id.type);
        }
    }

    public EndpointsAdapter(List<Endpoints> endpointsList) {
        this.endpointsList = endpointsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.endpoint_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Endpoints endpoints = endpointsList.get(position);
        holder.region.setText(endpoints.getRegion());
        holder.name.setText(endpoints.getName());
        holder.type.setText(endpoints.getType());
    }

    @Override
    public int getItemCount() {
        return endpointsList.size();
    }
}