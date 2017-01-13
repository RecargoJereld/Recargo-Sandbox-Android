package com.recargo.recargosandbox.ui.list;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/6/17.
 */

@Module
public class ListPresenterModule {

    private final ListContract.View listView;
    private final List<String> items;

    public ListPresenterModule(ListContract.View listView, List<String> items) {
        this.listView = listView;
        this.items = items;
    }

    @Provides
    ListContract.View provideListView() {
        return listView;
    }

    @Provides
    @Named("list_items")
    List<String> provideListItems() {
        return items;
    }
}
