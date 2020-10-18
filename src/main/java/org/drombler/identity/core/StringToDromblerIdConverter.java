package org.drombler.identity.core;

import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.RequiredArgsConstructor;
import org.drombler.identity.management.DromblerIdentityProviderManager;

@RequiredArgsConstructor
public class StringToDromblerIdConverter extends StdConverter<String, DromblerId> {

    private final DromblerIdentityProviderManager dromblerIdentityProviderManager;

    @Override
    public DromblerId convert(String dromblerIdFormatted) {
        return DromblerUserId.parseDromblerUserId(dromblerIdFormatted, dromblerIdentityProviderManager);
    }
}
