package it.gianni.numberschecker.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IStorageService {

    void init();

    Path store(MultipartFile file);

    void deleteDirectory();

    void deleteFiles() throws IOException;

}
