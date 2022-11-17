package com.leaderhackdemo.pdfparsingprototype.features.loading.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class PdfFileDescription {
    private String name;
    private String status;
}
