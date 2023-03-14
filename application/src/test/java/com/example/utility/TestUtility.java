package com.example.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtility {
    private TestUtility() {
    }

    public static String asJsonString(final Object obj) {
        final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
