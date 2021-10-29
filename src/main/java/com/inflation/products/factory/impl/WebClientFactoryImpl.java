package com.inflation.products.factory.impl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.inflation.products.factory.WebClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:credentials.yaml")
public class WebClientFactoryImpl implements WebClientFactory {

  @Value("${ip}")
  private String ip;

  @Value("${port}")
  private Integer port;

  @Value("${login}")
  private String login;

  @Value("${password}")
  private String password;

  @Override
  public WebClient createProxyClient() {
    WebClient client = new WebClient(BrowserVersion.CHROME, ip, port);
    DefaultCredentialsProvider credentialsProvider =
        (DefaultCredentialsProvider) client.getCredentialsProvider();
    credentialsProvider.addCredentials(login, password);
    return client;
  }
}
