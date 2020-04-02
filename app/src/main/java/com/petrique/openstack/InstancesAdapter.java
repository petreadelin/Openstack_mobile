package com.petrique.openstack;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InstancesAdapter extends RecyclerView.Adapter<InstancesAdapter.MyViewHolder> {

    private List<Instances> instancesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView region, type, name;
        public ImageButton start, pause;

        public MyViewHolder(View view) {
            super(view);
            region = (TextView) view.findViewById(R.id.region);
            name = (TextView) view.findViewById(R.id.name);
            type = (TextView) view.findViewById(R.id.type);
            start = view.findViewById(R.id.start_buttonInstances);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type.setText("Active");
                    Toast.makeText(start.getContext(),"Started", Toast.LENGTH_SHORT).show();
                }
            });
            pause = view.findViewById(R.id.pause_buttonInstances);
            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type.setText("Paused");
                    Toast.makeText(start.getContext(),"Paused", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public InstancesAdapter(List<Instances> InstancesList) {
        this.instancesList = InstancesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instances_list, parent, false);

        return new InstancesAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(InstancesAdapter.MyViewHolder holder, int position) {
        Instances instances = instancesList.get(position);
        holder.region.setText(instances.getRegion());
        holder.name.setText(instances.getName());
        holder.type.setText(instances.getType());
    }

    @Override
    public int getItemCount() {
        return instancesList.size();
    }
}