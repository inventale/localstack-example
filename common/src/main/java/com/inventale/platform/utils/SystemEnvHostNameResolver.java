package com.inventale.platform.utils;

import cloud.localstack.docker.annotation.IHostNameResolver;
import cloud.localstack.docker.annotation.LocalHostNameResolver;
import com.google.common.base.Strings;

public class SystemEnvHostNameResolver implements IHostNameResolver {
    private static final String HOSTNAME_EXTERNAL = "HOSTNAME_EXTERNAL";

    @Override
    public String getHostName() {
        String external = System.getenv(HOSTNAME_EXTERNAL);
        return !Strings.isNullOrEmpty(external) ?
                external :
                new LocalHostNameResolver().getHostName();
    }
}
