package com.bryan;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(
            SoftwareEngineerRepository softwareEngineerRepository
    ) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }
    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }
    @CachePut(value = "engineers", key = "#id")
    public SoftwareEngineer createSWE(Integer id, String name, String techStack) {
        SoftwareEngineer swe = new SoftwareEngineer();
        swe.setId(id);
        swe.setName(name);
        swe.setTechStack(techStack);
        return softwareEngineerRepository.save(swe);

    }
    @CacheEvict(value = "engineers", key = "#softwareEngineer.id")
    public void deleteSWE(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.deleteById(softwareEngineer.getId());
    }
    @Cacheable(value = "engineers", key = "#id")
    public SoftwareEngineer getOneEngineer(Integer id) {
        System.out.println("Fetching from DATABASE for id: " + id);
        return softwareEngineerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Engineer not found with id: " + id));

    }
}

