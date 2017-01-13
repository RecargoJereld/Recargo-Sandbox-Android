package com.recargo.recargosandbox.list;

import com.recargo.recargosandbox.ui.list.ListContract;
import com.recargo.recargosandbox.ui.list.ListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by jereld on 1/6/17.
 */

public class ListPresenterTest {

    private static List<String> ITEMS = Arrays.asList("Abbot Kinney", "Broadway", "Electric");

    @Mock
    ListContract.View listView;

    private ListPresenter listPresenter;

    @Before
    public void setupListPresenter() {
        MockitoAnnotations.initMocks(this);

        listPresenter = new ListPresenter(listView, ITEMS);
    }

    // Verify that the list items are loaded in the start method
    @Test
    public void onStartLoadItems() {
        listPresenter.start();

        verify(listView).showListItems(ITEMS);
    }
}
