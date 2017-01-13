package com.recargo.recargosandbox.data;

/**
 * Created by jereld on 1/11/17.
 */

public interface ServiceCallback<T> {
    void onSuccess(T response);

    void onFailure();
}
