package com.codenotfound.crnk.client;

import io.crnk.client.CrnkClient;
import io.crnk.client.http.HttpAdapterProvider;
import io.crnk.client.http.apache.HttpClientAdapterProvider;
import io.crnk.core.queryspec.mapper.DefaultQuerySpecUrlMapper;

/**
 * Created by maurice on 3/1/18
 */
public class APIClient extends CrnkClient {

    public APIClient() {
        this(System.getProperty("API_URL", "https://api.barmer.prod.ddg-webservice.de/2.0.0/"));
    }

    public APIClient(String host) {
        super(host);
        if (getUrlMapper() instanceof DefaultQuerySpecUrlMapper) {
            ((DefaultQuerySpecUrlMapper) getUrlMapper()).setAllowUnknownAttributes(true);
        }
//        initialize();
    }

    @Override
    public void registerHttpAdapterProvider(HttpAdapterProvider httpAdapterProvider) {
        if (httpAdapterProvider instanceof HttpClientAdapterProvider) {
            super.registerHttpAdapterProvider(httpAdapterProvider);
        }
    }

//    private void initialize() {
//        HttpClientAdapter adapter = (HttpClientAdapter) getHttpAdapter();
//        adapter.addListener(builder -> builder.setDefaultHeaders(AuthService.getDefaultHeader()));
//    }
}
