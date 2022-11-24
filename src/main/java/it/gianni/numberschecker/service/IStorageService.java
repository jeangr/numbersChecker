package it.gianni.numberschecker.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IStorageService {

    /**
     * Init the storage.
     *
     */
    void init();

    /**
     * Store the file.
     *
     * @param file {@link MultipartFile}
     * @return {@link Path}
     */
    Path store(MultipartFile file);

    /**
     * Delete the store directory.
     *
     */
    void deleteDirectory();

    /**
     * Delete files in the store directory
     *
     */
    void deleteFiles() throws IOException;

}
