package com.bryan;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;
    //private final SoftwareEngineerDTOMapper softwareEngineerDTOMapper;

    public SoftwareEngineerService(
            SoftwareEngineerRepository softwareEngineerRepository
    ) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        //this.softwareEngineerDTOMapper = softwareEngineerDTOMapper;
    }
    public List<SoftwareEngineerDTO> getAllSoftwareEngineers() {
        // explain stream() and map() here
        // stream() is a method that allows us to process collections of data in a functional style.
        // It provides a way to perform operations on the elements of a collection, such as filtering, mapping, and reducing.
        // In this case, we are using stream() to convert the list of SoftwareEngineer entities retrieved from the database into a stream of elements that can be processed.
        //so the stream() is use to convert a whole list for ex: [1,2,3,4,5] into a stream 1->2->3->4->5 then map will apply DTOMapper to each item in stream then convert them back to a list
        return softwareEngineerRepository.findAll().stream()
                .map(SoftwareEngineerDTO::new)
                .toList();

    }

    public List<SoftwareEngineerDTO> getEngineersByTechStack(String techStack) {
        return softwareEngineerRepository.findByTechStackContainingIgnoreCase(techStack).stream()
                .map(SoftwareEngineerDTO::new)
                .toList();
    }
    @CachePut(value = "engineers", key = "#result.id")
    public SoftwareEngineerDTO createSWE(Integer id, String name, String techStack) {
        SoftwareEngineer swe = new SoftwareEngineer();
        swe.setId(id);
        swe.setName(name);
        swe.setTechStack(techStack);
        SoftwareEngineer saved = softwareEngineerRepository.save(swe);
        return new SoftwareEngineerDTO(saved);
    }
    @CacheEvict(value = "engineers", key = "#softwareEngineer.id")
    public void deleteSWE(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.deleteById(softwareEngineer.getId());
    }
    @Cacheable(value = "engineers", key = "#id")
    public SoftwareEngineerDTO getOneEngineer(Integer id) {
        System.out.println("Fetching from DATABASE for id: " + id);
        SoftwareEngineer engineer = softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Engineer not found with id: " + id));
        return new SoftwareEngineerDTO(engineer);
    }

}

