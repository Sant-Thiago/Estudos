package com.example.integration;

import com.example.service.Formatar;

public class Info {

    private static Boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
    private String directory = "/src/main/java/com/example/scripts";

    private ProcessBuilder processBuilder = new ProcessBuilder();
    private String hostname;
    private String username;

    private Formatar formatar = new Formatar();

    public String getHostname() {
        try {
            String[] command;
            if (!isWindows) {
                String scriptPath = directory + "/bash/userInfo.sh";
                command = formatar.commandBash(scriptPath);
                
            } else {
                String scriptPath = directory + "/powershell/userInfo.ps1";
                command = formatar.commandPowershell(scriptPath); 

            }

            System.out.println(command.toString());

            processBuilder.command(command);


        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

}
