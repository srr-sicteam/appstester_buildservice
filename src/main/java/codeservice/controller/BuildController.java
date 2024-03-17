package codeservice.controller;

import codeservice.service.BuildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class BuildController {

    private final BuildService buildService;

    public BuildController(BuildService gradleBuildService) {
        this.buildService = gradleBuildService;
    }

    @PostMapping("/build")
    public ResponseEntity<String> buildProject() {
        try {
            // Build the project and get the APK path
            String apkPath = buildService.executeGradleBuild();

            if (apkPath != null) {
                return ResponseEntity.ok(apkPath);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to build APK");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error building project: " + e.getMessage());
        }
    }
}

