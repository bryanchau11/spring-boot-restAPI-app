package com.bryan;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/software-engineers")
public class SoftwareEngineerController {
    private final SoftwareEngineerService softwareEngineerService;
    private final SoftwareEngineerDTOMapper softwareEngineerDTOMapper;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService, SoftwareEngineerDTOMapper softwareEngineerDTOMapper) {
        this.softwareEngineerService = softwareEngineerService;
        this.softwareEngineerDTOMapper = softwareEngineerDTOMapper;
    }

    @GetMapping
    public List<SoftwareEngineerDTO> getEngineers() {
        System.out.println("DEBUG: Called getEngineers() [ALL]");
        return softwareEngineerService.getAllSoftwareEngineers().stream()
                .map(softwareEngineerDTOMapper)
                .toList();
    }

    // This method fetches software engineers by their technology stack
    @GetMapping(params = "techStack")
    public List<SoftwareEngineerDTO> getEngineersByTechStack(@RequestParam(name = "techStack") String techStack) {
        System.out.println("DEBUG: Called getEngineersByTechStack() with: " + techStack);
        return softwareEngineerService.getEngineersByTechStack(techStack).stream().map(softwareEngineerDTOMapper).toList();
    }

    @PostMapping
    public SoftwareEngineerDTO createSWE(@RequestBody SoftwareEngineer softwareEngineer) {
        SoftwareEngineer saved = softwareEngineerService.createSWE(softwareEngineer.getId(), softwareEngineer.getName(), softwareEngineer.getTechStack());
        return softwareEngineerDTOMapper.apply(saved);
    }

    @DeleteMapping
    public void deleteSWE(@RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.deleteSWE(softwareEngineer);
    }

    @GetMapping("/{id}")
    public SoftwareEngineerDTO getOneEngineer(@PathVariable Integer id) {
        SoftwareEngineer engineer = softwareEngineerService.getOneEngineer(id);

        return softwareEngineerDTOMapper.apply(engineer);
    }
}
