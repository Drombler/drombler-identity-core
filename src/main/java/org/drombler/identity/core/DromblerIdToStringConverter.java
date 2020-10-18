package org.drombler.identity.core;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DromblerIdToStringConverter extends StdConverter<DromblerId, String> {

    @Override
    public String convert(DromblerId dromblerId) {
        return dromblerId != null ? dromblerId.getDromblerIdFormatted() : null;
    }
}
