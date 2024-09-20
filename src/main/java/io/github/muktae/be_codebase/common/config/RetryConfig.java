package io.github.muktae.be_codebase.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry(proxyTargetClass=true)
public class RetryConfig {
}
