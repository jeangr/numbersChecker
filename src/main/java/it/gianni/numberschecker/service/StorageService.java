package it.gianni.numberschecker.service;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {

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
    Path store(@NonNull MultipartFile file);

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
