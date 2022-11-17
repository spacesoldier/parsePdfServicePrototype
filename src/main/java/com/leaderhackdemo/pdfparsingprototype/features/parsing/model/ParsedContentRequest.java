package com.leaderhackdemo.pdfparsingprototype.features.parsing.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ParsedContentRequest {
    private String documentId;
}
