package com.recargo.recargosandbox;

import com.recargo.recargosandbox.data.api.PlugShareServiceModule;
import com.recargo.recargosandbox.util.ApplicationScope;

import dagger.Component;

/**
 * Created by jereld on 1/11/17.
 */

@ApplicationScope
@Component(modules ={PlugShareServiceModule.class, ApplicationModule.class})
public interface ApplicationComponent {
    void inject(RecargoSandboxApp app);
}
