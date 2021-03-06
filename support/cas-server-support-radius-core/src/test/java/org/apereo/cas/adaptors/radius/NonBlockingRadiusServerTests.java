package org.apereo.cas.adaptors.radius;

import org.apereo.cas.adaptors.radius.server.NonBlockingRadiusServer;

/**
 * This is {@link NonBlockingRadiusServerTests}.
 * Runs test cases against a radius server running on "https://console.ironwifi.com/".
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
public class NonBlockingRadiusServerTests extends AbstractRadiusServerTests {
    @Override
    public RadiusServer getRadiusServer() {
        return new NonBlockingRadiusServer(RadiusProtocol.MSCHAPv2,
            new RadiusClientFactory(ACCOUNTING_PORT, AUTHENTICATION_PORT, INET_ADDRESS, SECRET));
    }
}
