package com.kolomin.balansir.Service.impl;

import com.kolomin.balansir.Configuration.ConfigHandler;
import com.kolomin.balansir.Entity.Page;
import com.kolomin.balansir.Entity.QR;
import com.kolomin.balansir.Entity.Resource;
import com.kolomin.balansir.Model.CSVResourceModel;
import com.kolomin.balansir.Repository.PageRepository;
import com.kolomin.balansir.Repository.QRRepository;
import com.kolomin.balansir.Service.CSVFilesService;
import com.kolomin.balansir.Util.DataUtil;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macbook on 23.02.2022
 */
@Service
@Slf4j
public class CSVFilesServiceImpl implements CSVFilesService {

    @Autowired
    private QRRepository qrRepository;

    @Autowired
    private PageRepository pageRepository;

    @Override
    public Path transferFiles(Path source, Path dest) {
        return null;
    }

    @Override
    public List<CSVResourceModel> parseCSVFile(File sourcePath,Class cz) throws FileNotFoundException {
        List<CSVResourceModel> beans = new CsvToBeanBuilder(new FileReader(sourcePath))
                .withSeparator(';')
                .withSkipLines(1)
                .withType(cz)
                .build()
                .parse();
        beans.forEach(System.out::println);
        return beans;

    }

    @Override
    public String resourceFileHandler(MultipartFile multipartFile) throws IOException {
        File dir = new File("handler/csv");
        if (!dir.exists()){
            dir.mkdirs();
            log.debug("Созданы директории: " + dir);
        }
        File file = new File(dir+"/"+multipartFile.getOriginalFilename());
        multipartFile.transferTo(file.toPath());
        List<CSVResourceModel> model =parseCSVFile(file.getAbsoluteFile(),CSVResourceModel.class);
        try {
            generateCustomResources(model);
        } catch (IOException e) {
            return "Ошибка при загрузке, проверьте корректность данных в файле!";
        }
        return "Файл загружен успешно!";
    }


    private void  generateCustomResources(List<CSVResourceModel> propResources) throws IOException {
        List<String> qr_suff = new ArrayList<>();
        qr_suff.add(propResources.get(0).getSuffixQR());
        QR qr = null;
        for (int i=0;i<propResources.size();i++) {
            if (qr == null || !qr.getQr_suffix().equals(propResources.get(i).getSuffixQR()) || !qr_suff.contains(propResources.get(i).getSuffixQR())) {
                qr_suff.add(propResources.get(i).getSuffixQR());
                qr = qrRepository.getBySuffix(propResources.get(i).getSuffixQR());
            }
            if (qr == null) {
                throw new IOException("Передали пустой файл!");
            } else {
                qr.setResources(addCustomResource(qr,propResources.get(i)));
                qrRepository.save(qr);
            }

        }
    }

    private List<Resource> addCustomResource(QR qr,CSVResourceModel resourceModel) {
        List<Resource> resources = qr.getResources();
        String resourcePath = DataUtil.generatePath();
        generateTemplateForPath(resourcePath,resourceModel);
        resourcePath= ConfigHandler.thisHostPort.substring(0,ConfigHandler.thisHostPort.length()-1)+resourcePath;
        Resource resource = new Resource(qr,resourceModel.getSuffixQR(),resourcePath);
        if (resourceModel.getPeopleCount()== null) {
            resource.setInfinity(true);
            resource.setPeople_count(0L);
        }
        else if (resourceModel.getPeopleCount()==0L) {
            resource.setDeleted(true);
            resource.setPeople_count(0L);
        } else {
            resource.setInfinity(false);
            resource.setDeleted(false);
            resource.setPeople_count(resourceModel.getPeopleCount());
        }
        resource.setCame_people_count(0L);

        resources.add(resource);
        return resources;
    }

    private void generateTemplateForPath(String resourcePath, CSVResourceModel resourceModel) {
        Page page = new Page(resourcePath,resourceModel.getMessageResource());
        pageRepository.save(page);
    }
}
