package com.supplynext.company_api.service;

import com.supplynext.company_api.models.Document;
import com.supplynext.company_api.repository.DocumentRepository;
import io.imagekit.sdk.exceptions.*;
import io.imagekit.sdk.models.results.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class DocumentService {

    @Autowired
    private ImageKitService imageKitService;

    @Autowired
    private DocumentRepository documentRepository;
    public Document uploadDocument(MultipartFile file,
                                   String fileName,
                                   String fileType,
                                   String folderName) throws ForbiddenException, TooManyRequestsException, InternalServerException, UnauthorizedException, BadRequestException, UnknownException, IOException {
        // Uploading document on ImageKit and receiving the result
        Result result= imageKitService.uploadDocuments(file,fileName,folderName);
        // From that result extracting the url of the document
        String documentUrl= result.getUrl();

        Document document = new Document(
                    fileName,
                    file.getOriginalFilename(),
                    fileType, // gstcertficate or companylogo etc
                    documentUrl
            );

        return documentRepository.save(document);

    }

}
