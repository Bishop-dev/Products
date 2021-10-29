package com.inflation.products.factory;

import com.gargoylesoftware.htmlunit.WebClient;

public interface WebClientFactory {

  WebClient createProxyClient();
}
