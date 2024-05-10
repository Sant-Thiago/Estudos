package com.example.service;

import java.util.ArrayList;
import java.util.List;


public class Formatar {

    private List<String> commandsHistory = new ArrayList<>();

    public String[] commandBash(String scriptPath) {

        String[] commandBash = new String[]{
            "sh",
            scriptPath
        };

        commandsHistory.add(commandBash.toString());
        return commandBash;
    }

    public String[] commandPowershell(String scriptPath) {

        String[] commandPShell = new String[] {
            "powershell.exe",
            "-ExecutionPolicy",
            "Bypass",
            "-File",
            scriptPath
        };

        commandsHistory.add(commandPShell.toString());
        return commandPShell;
    }

    public String[] commandCommandPrompt(String scriptPath) {
        
        String[] commandCommandPrompt = new String[] {
            "cmd.exe",
            "/c",
            scriptPath
        };

        commandsHistory.add(commandCommandPrompt.toString());
        return commandCommandPrompt;
    }

    public String[] commandCommandPrompt(String scriptPath, String params) {
        
        String[] commandCommandPrompt = new String[] {
            "cmd.exe",
            "/c",
            scriptPath,
            params+"0/24"
        };

        commandsHistory.add(commandCommandPrompt.toString());
        return commandCommandPrompt;
    }


}
