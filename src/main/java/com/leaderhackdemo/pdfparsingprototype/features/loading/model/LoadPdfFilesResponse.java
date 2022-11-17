package com.leaderhackdemo.pdfparsingprototype.features.loading.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class LoadPdfFilesResponse {
    private String requestId;
    private List<PdfFileDescription> processResults;
}
