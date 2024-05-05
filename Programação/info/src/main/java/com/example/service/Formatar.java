package com.example.service;

import java.util.ArrayList;
import java.util.List;


public class Formatar {

    private List<String> commands = new ArrayList<>();

    public String[] commandBash(String scriptPath) {

        String[] commandBash = new String[]{
            "sh",
            scriptPath
        };

        commands.add(commandBash.toString());
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

        commands.add(commandPShell.toString());
        return commandPShell;
    }

    public String[] commandCommandPrompt(String scriptPath) {
        
        String[] commandCommandPrompt = new String[] {
            "cmd.exe",
            "/c",
            scriptPath
        };

        commands.add(commandCommandPrompt.toString());
        return commandCommandPrompt;
    }


}
