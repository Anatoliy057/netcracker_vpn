package com.example.netcracker_vpn.exceptions;

import java.util.HashMap;
import java.util.Map;

public class UniqueException extends Exception {

    private Map<String, Object> params;

    public UniqueException() {
        params = new HashMap<>();
    }

    void putColumnAndValue(String column, Object value) {
        params.put(column, value);
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
