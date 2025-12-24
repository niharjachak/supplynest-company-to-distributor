package com.supplynext.company_api.service;

import com.supplynext.company_api.exceptions.UploadFileException;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.exceptions.*;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@Slf4j
public class ImageKitService {
    @Value("${image.kit.public.key}")
    private String publicKey;
    @Value("${image.kit.private.key}")
    private String privateKey;
    @Value("${image.kit.urlEndpoint}")
    private String urlEndpoint;


    public ImageKit getImageKitObject(){
        ImageKit imageKit = ImageKit.getInstance();
        Configuration configuration = new Configuration(publicKey,privateKey, urlEndpoint);
        imageKit.setConfig(configuration);
        return imageKit;
    }

    public Result uploadDocuments(
            MultipartFile file,
            String fileName,
            String folder) throws ForbiddenException, TooManyRequestsException, InternalServerException, UnauthorizedException, BadRequestException, UnknownException, IOException {
        try {
            long fileSize =file.getSize()/(1024*1024); // fileSize is converted in to MBs
            if(fileSize >25){
                throw  new UploadFileException("File  Size Greater than 25 MB");
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
            //Converting the file to upload in byte array
            byte[] fileBytes= file.getBytes();
            // Encrypting  the byte[] into base64
            String base64= Base64.getEncoder().encodeToString(fileBytes);
            FileCreateRequest fileCreateRequest= new FileCreateRequest(
                    base64,
                    fileName
            );
            //setting folder in which we want to upload the file
            fileCreateRequest.setFolder(folder);
            ImageKit imageKit = getImageKitObject();
            // uploading file to imageKit
            Result result= imageKit.upload(fileCreateRequest);
            // returns the url of the file uploaded on ImageKit server
            return result;
        }


}

