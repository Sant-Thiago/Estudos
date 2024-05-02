package com.example.integration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.service.Formatar;
import com.example.service.Pegar;

public class Info {

    private static Boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
    private String directory = "src\\main\\java\\com\\example\\scripts";

    private ProcessBuilder processBuilder;
    private String hostname;
    private String username;

    private Formatar formatar = new Formatar();
    private Pegar pegar = new Pegar();
    

    public String getHostname() {
        try {

            String[] command;
            if (!isWindows) {
                String scriptPath = directory + "\\bash\\userInfo.sh";
                command = formatar.commandBash(scriptPath);
                
            } else {
                String scriptPath = directory + "\\cmd\\userinfo.cmd";
                command = formatar.commandCommandPrompt(scriptPath); 

            }

            processBuilder = new ProcessBuilder(command);
            
            Process process = processBuilder.start();
            
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            List<String> listStrings = new ArrayList<>();
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                listStrings.add(line);
            }
            int output = process.waitFor();

            BufferedReader erroReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            while((line = erroReader.readLine()) != null) {
                System.err.println("Saida de erro: "+ line);
            }

            this.hostname = pegar.string(2, listStrings);

            return this.hostname;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
