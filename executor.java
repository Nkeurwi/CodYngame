import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class executor {  // Les noms de classe commencent par une majuscule
    
    public void fonction1() {
        try {
            // 1. Lecture de l'entrée utilisateur
            BufferedReader fct = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Saisissez la fonction :");
            String fonction = fct.readLine();

            // Create a temporary directory to store source and compiled files
            Path tempDir = Files.createTempDirectory("codeyngame_");
            File sourceFile;
            List<String> execCmd = new ArrayList<>();
            sourceFile = tempDir.resolve("main.py").toFile();
            Files.writeString(sourceFile.toPath(), fonction);
            
            // Command to interpret Python script
            execCmd = List.of("python", sourceFile.getAbsolutePath());
            
            
            // Execute the program
            ProcessBuilder execBuilder = new ProcessBuilder(execCmd);
            Process process = execBuilder.start();
            
            // 3. Lecture de la sortie
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // 4. Gestion des erreurs
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Erreur d'exécution (code " + exitCode + ")");
            }
            
        } catch (IOException | InterruptedException e) {  // Ajout de InterruptedException
            System.err.println("Erreur : " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {  // Ajout d'une méthode main pour tester
        new executor().fonction1();
    }
}