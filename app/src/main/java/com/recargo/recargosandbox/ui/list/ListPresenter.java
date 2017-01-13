package com.recargo.recargosandbox.ui.list;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jereld on 1/6/17.
 */

public class ListPresenter implements ListContract.Presenter {

    private final ListContract.View listView;
    private final List<String> items;

    @Inject
    public ListPresenter(ListContract.View listView, @Named("list_items") List<String> items) {
        this.listView = listView;
        this.items = items;
    }

    @Override
    public void start() {
        listView.showListItems(items);
    }
}
