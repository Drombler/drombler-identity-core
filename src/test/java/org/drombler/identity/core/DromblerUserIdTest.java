package org.drombler.identity.core;

import org.drombler.identity.management.DromblerIdentityProviderManager;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DromblerUserIdTest {

    private static final String DROMBLER_ID_STRING = "test";

    @Nested
    class GetDromblerIdFormatted {

        @Test
        public void privateDromblerIdProvider_dromblerIdString() {
            DromblerId testUserId = new DromblerUserId(DROMBLER_ID_STRING);

            String dromblerIdFormatted = testUserId.getDromblerIdFormatted();

            assertEquals(testUserId.getDromblerIdString(), dromblerIdFormatted);
        }

        @Test
        public void nonPrivateDromblerIdProvider_includeIdPName() {
            DromblerIdentityProvider testDromblerIdProvider = new TestDromblerIdProvider();
            DromblerId testUserId = new DromblerUserId(DROMBLER_ID_STRING, testDromblerIdProvider);

            String dromblerIdFormatted = testUserId.getDromblerIdFormatted();

            assertEquals(testUserId.getDromblerIdString() + "@" + testDromblerIdProvider.getDromblerIdentityProviderId(), dromblerIdFormatted);
        }
    }

    @Nested
    class ParseDromblerUserId {
        @Test
        public void privateDromblerIdProvider_dromblerIdString() {
            DromblerIdentityProvider testDromblerIdProvider = new TestDromblerIdProvider();
            DromblerIdentityProviderManager dromblerIdentityProviderManager = new DromblerIdentityProviderManager();
            dromblerIdentityProviderManager.registerDromblerIdentityProvider(testDromblerIdProvider);

            String dromblerIdFormatted = DROMBLER_ID_STRING;
            DromblerUserId dromblerUserId = DromblerUserId.parseDromblerUserId(dromblerIdFormatted, dromblerIdentityProviderManager);

            assertEquals(dromblerIdFormatted, dromblerUserId.getDromblerIdFormatted());
            assertEquals(DROMBLER_ID_STRING, dromblerUserId.getDromblerIdString());
            assertEquals(PrivateDromblerIdProvider.getInstance(), dromblerUserId.getDromblerIdentityProvider());
            assertEquals(DromblerIdType.USER, dromblerUserId.getType());
        }

        @Test
        public void nonPrivateDromblerIdProvider_includeIdPName() {
            DromblerIdentityProvider testDromblerIdProvider = new TestDromblerIdProvider();
            DromblerIdentityProviderManager dromblerIdentityProviderManager = new DromblerIdentityProviderManager();
            dromblerIdentityProviderManager.registerDromblerIdentityProvider(testDromblerIdProvider);

            String dromblerIdFormatted = DROMBLER_ID_STRING + "@" + testDromblerIdProvider.getDromblerIdentityProviderId();
            DromblerUserId dromblerUserId = DromblerUserId.parseDromblerUserId(dromblerIdFormatted, dromblerIdentityProviderManager);

            assertEquals(dromblerIdFormatted, dromblerUserId.getDromblerIdFormatted());
            assertEquals(DROMBLER_ID_STRING, dromblerUserId.getDromblerIdString());
            assertEquals(testDromblerIdProvider, dromblerUserId.getDromblerIdentityProvider());
            assertEquals(DromblerIdType.USER, dromblerUserId.getType());
        }
    }

    private static class TestDromblerIdProvider implements DromblerIdentityProvider {

        @Override
        public String getDromblerIdentityProviderId() {
            return "test";
        }

        @Override
        public boolean isPrivate() {
            return false;
        }
    }
}