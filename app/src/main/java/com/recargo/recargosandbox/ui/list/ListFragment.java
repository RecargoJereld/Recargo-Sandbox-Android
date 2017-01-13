package com.recargo.recargosandbox.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recargo.recargosandbox.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jereld on 1/6/17.
 */

public class ListFragment extends Fragment implements ListContract.View {
    private static final String TAG = ListFragment.class.getSimpleName();

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    private ListAdapter listAdapter;

    @Inject
    ListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listAdapter = new ListAdapter(new ArrayList<>(0));

        List<String> items = Arrays.asList(getResources().getStringArray(R.array.list_items));
        DaggerListComponent.builder()
                .listPresenterModule(new ListPresenterModule(this, items))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = ButterKnife.findById(view, R.id.recycler_view);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showListItems(List<String> items) {
         listAdapter.replaceListItems(items);
    }

    static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private List<String> items;

        public ListAdapter(List<String> items) {
            setList(items);
        }

        private void setList(List<String> items) {
            this.items = items;
        }

        public void replaceListItems(List<String> items) {
            setList(items);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            View itemView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(android.R.id.text1)
            TextView text;

            public ViewHolder(View itemView) {
                super(itemView);

                ButterKnife.bind(this, itemView);

                text.setOnClickListener(view -> onItemClicked());
            }

            private void onItemClicked() {
                Log.d(TAG, "onItemClicked: ");
            }
        }
    }
}
