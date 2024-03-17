package codeservice.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuildService {

    public String executeGradleBuild() throws IOException, InterruptedException {
        // Path to the Android project
        String projectDir = "/path/to/android/project";

        // building the project
        String command = "gradlew.bat assembleDebug"; // For Windows
        // String command = "./gradlew assembleDebug"; // For Linux or MacOS

        // Execute the Gradle build command
        Process process = Runtime.getRuntime().exec(command, null, new File(projectDir));

        // output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            // Successful build
            return projectDir + "/app/build/outputs/apk/debug/app-debug.apk";
        } else {
            // Build failed
            System.err.println("Build failed:\n" + output.toString());
            return null;
        }
    }
}
