package org.example.timestamp_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorObject {
    String error = "Invalid Date";
}
