package com.rizwan.ecomerce.handler;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
