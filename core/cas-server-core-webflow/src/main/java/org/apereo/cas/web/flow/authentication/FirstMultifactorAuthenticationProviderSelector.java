package org.apereo.cas.web.flow.authentication;

import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.services.MultifactorAuthenticationProvider;
import org.apereo.cas.services.MultifactorAuthenticationProviderSelector;
import org.apereo.cas.services.RegisteredService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is {@link FirstMultifactorAuthenticationProviderSelector}
 * that grabs onto the first authentication provider in the collection.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
public class FirstMultifactorAuthenticationProviderSelector implements MultifactorAuthenticationProviderSelector {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstMultifactorAuthenticationProviderSelector.class);

    @Override
    public MultifactorAuthenticationProvider resolve(final Collection<MultifactorAuthenticationProvider> providers,
                                                     final RegisteredService service, final Principal principal) {
        final List<MultifactorAuthenticationProvider> sorted = new ArrayList<>(providers);
        if (sorted.isEmpty()) {
            throw new RuntimeException("List of candidate multifactor authentication providers is empty");
        }
        OrderComparator.sort(sorted);
        final MultifactorAuthenticationProvider provider = sorted.get(sorted.size() - 1);
        LOGGER.debug("Selected the provider [{}] for service [{}] out of [{}] providers", provider, service, providers.size());
        return provider;
    }
}
