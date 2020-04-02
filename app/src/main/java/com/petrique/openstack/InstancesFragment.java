package com.petrique.openstack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InstancesFragment extends Fragment{
    private List<Instances> instancesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InstancesAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_instances, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view_instances);

        mAdapter = new InstancesAdapter(instancesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareInstancesData();
        return rootView;
    }


    private void prepareInstancesData() {
        Instances instances_nginx = new Instances("host: stack1", "Active", "Nginx");
        instancesList.add(instances_nginx);

        Instances instances_cockpit = new Instances("host:stack1", "Active", "Cockpit");
        instancesList.add(instances_cockpit);

        mAdapter.notifyDataSetChanged();
    }
}
