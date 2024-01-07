package com.api.book.bookrestbook.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    // public final String UPLOAD_DIR =
    // "/Users/wanderwise/Desktop/Java/SpringBoot/SpringBootVSProject/bookrestbook/src/main/resources/static/images";

    public final String UPLOAD_DIR = new ClassPathResource("static/images").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uplaodFile(MultipartFile multipartFile) {
        boolean f = false;
        try {

            //
            // InputStream inputStream = multipartFile.getInputStream();
            // byte data[] = new byte[inputStream.available()];

            // inputStream.read(data);

            // // wite
            // FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + "/" +
            // multipartFile.getOriginalFilename());
            // fos.write(data);
            // fos.flush();
            // fos.close();

            Files.copy(multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            f = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return f;
    }

}
