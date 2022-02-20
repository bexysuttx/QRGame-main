package com.kolomin.balansir.Service;

import com.kolomin.balansir.Entity.Resource;
import com.kolomin.balansir.Repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public void saveOrUpdate(Resource newResource) {
        resourceRepository.save(newResource);
    }

    public boolean findUrl(String url) {
        if (resourceRepository.findUrl(url) != null){
            return true;
        } else {
            return false;
        }
    }
    public boolean findUrl(String url,Long qrId) {
        if (resourceRepository.findUrl(url, qrId) != null){
            return true;
        } else {
            return false;
        }
    }

    public Resource getByQRSuffixNotDeletedAndCamePeopleCountMin(String path) {
        return resourceRepository.getByQRSuffixNotDeletedAndCamePeopleCountMin(path);
    }

    public Resource getByQRSuffixNotDeleted(String path) {
        return resourceRepository.getByQRSuffixNotDeleted(path);
    }

    public Resource getById(Long id) {
        return resourceRepository.getById(id);
    }

    public void delete(Resource res) {
        resourceRepository.delete(res);
    }
}
