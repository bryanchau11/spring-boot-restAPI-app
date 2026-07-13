package com.bryan;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/software-engineers")
public class SoftwareEngineerController {
    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineerDTO> getEngineers() {
        System.out.println("DEBUG: Called getEngineers() [ALL]");
        return softwareEngineerService.getAllSoftwareEngineers();
    }

    // This method fetches software engineers by their technology stack
    @GetMapping(params = "techStack")
    public List<SoftwareEngineerDTO> getEngineersByTechStack(@RequestParam(name = "techStack") String techStack) {
        System.out.println("DEBUG: Called getEngineersByTechStack() with: " + techStack);
        return softwareEngineerService.getEngineersByTechStack(techStack);
    }

    @PostMapping
    public SoftwareEngineerDTO createSWE(@RequestBody SoftwareEngineer softwareEngineer) {
        return softwareEngineerService.createSWE(softwareEngineer.getId(), softwareEngineer.getName(), softwareEngineer.getTechStack());
    }

    @DeleteMapping
    public void deleteSWE(@RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.deleteSWE(softwareEngineer);
    }

    @GetMapping("/{id}")
    public SoftwareEngineerDTO getOneEngineer(@PathVariable Integer id) {
        return softwareEngineerService.getOneEngineer(id);
    }
}
