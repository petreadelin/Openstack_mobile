package com.petrique.openstack.OverviewFragment;

import com.petrique.openstack.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment { //
    private List<Endpoints> endpointsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EndpointsAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_services);

        mAdapter = new EndpointsAdapter(endpointsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareEndpointsData();
        return rootView;
    }

    private void prepareEndpointsData() {
        Endpoints endpoints_nova = new Endpoints("COMPUTE", "Virtual Machines", "nova");
        endpointsList.add(endpoints_nova);

        Endpoints endpoints_neutron = new Endpoints("NETWORKING", "SDN", "neutron");
        endpointsList.add(endpoints_neutron);

        Endpoints endpoints_octavia = new Endpoints("NETWORKING", "Load Balancing", "octavia");
        endpointsList.add(endpoints_octavia);

        Endpoints endpoints_cinder = new Endpoints("STORAGE", "Block storage", "cinder");
        endpointsList.add(endpoints_cinder);

        Endpoints endpoints_swift = new Endpoints("STORAGE", "Object storage", "swift");
        endpointsList.add(endpoints_swift);

        Endpoints endpoints_manila = new Endpoints("STORAGE", "File storage", "manila");
        endpointsList.add(endpoints_manila);

        Endpoints endpoints_horizon = new Endpoints("FRONTEND", "Dashboard", "horizon");
        endpointsList.add(endpoints_horizon);

        Endpoints endpoints_heat = new Endpoints("ORCHESTRATION", "Cloudformation", "heat");
        endpointsList.add(endpoints_heat);

        Endpoints endpoints_glance = new Endpoints("IMAGE", "Image service", "glance");
        endpointsList.add(endpoints_glance);

        Endpoints endpoints_keystone = new Endpoints("SECURITY", "Identity service", "keystone");
        endpointsList.add(endpoints_keystone);


        mAdapter.notifyDataSetChanged();
    }
}