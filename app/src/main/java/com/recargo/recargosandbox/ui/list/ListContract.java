package com.recargo.recargosandbox.ui.list;

import com.recargo.recargosandbox.BasePresenter;
import com.recargo.recargosandbox.BaseView;

import java.util.List;

/**
 * Created by jereld on 1/6/17.
 */

public interface ListContract {
    interface View extends BaseView {
        void showListItems(List<String> items);
    }

    interface Presenter extends BasePresenter {

    }
}
