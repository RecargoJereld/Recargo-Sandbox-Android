package com.recargo.recargosandbox.ui.list;

import dagger.Component;

/**
 * Created by jereld on 1/6/17.
 */

@Component(modules = ListPresenterModule.class)
public interface ListComponent {
    void inject(ListFragment listFragment);
}
