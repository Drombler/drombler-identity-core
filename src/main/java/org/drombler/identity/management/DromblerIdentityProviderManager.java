package org.drombler.identity.management;

import org.drombler.identity.core.DromblerIdentityProvider;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
