package org.drombler.identity.management;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.drombler.identity.core.DromblerIdentityProvider;

/**
 * @author Florian
 */
public class DromblerIdentityProviderManager {

    private final ConcurrentMap<String, DromblerIdentityProvider> dromblerIdentityProviders = new ConcurrentHashMap<>();

    public DromblerIdentityProvider registerDromblerIdentityProvider(DromblerIdentityProvider dromblerIdentityProvider) {
        return dromblerIdentityProviders.put(dromblerIdentityProvider.getDromblerIdentityProviderId(), dromblerIdentityProvider);
    }

    public DromblerIdentityProvider getDromblerIdentityProvider(String dromblerIdentityProviderId) {
        return dromblerIdentityProviders.get(dromblerIdentityProviderId);
    }

}
