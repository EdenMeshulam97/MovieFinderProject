package com.hit.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Request<T> {
    private Map<String, String> headers;
    private T body;

    public Request(Map<String, String> headers, T body) {
        if (headers != null) {
            // Create a copy of the headers map and then make it unmodifiable
            this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
        }
        this.body = body;
    }

    public Request(Map<String, String> headers) {
        if (headers != null) {
            // Create a copy of the headers map and then make it unmodifiable
            this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
        }
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public T getBody() {
        return body;
    }
}
