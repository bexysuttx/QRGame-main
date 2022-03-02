package com.kolomin.balansir.Service;

import com.kolomin.balansir.Model.CSVResourceModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author macbook on 23.02.2022
 */
public interface CSVFilesService {

    Path transferFiles(Path source,Path dest);

    List<CSVResourceModel> parseCSVFile(File sourcePath,Class cz) throws FileNotFoundException;

    String resourceFileHandler(MultipartFile multipartFile) throws IOException;
}
