package com.example.service;

import java.util.ArrayList;
import java.util.List;


public class Formatar {

    private List<String> commands = new ArrayList<>();

    public String[] commandBash(String scriptPath) {

        String[] commandBash = new String[]{
            "sh ",
            scriptPath
        };

        commands.add(commandBash.toString());
        return commandBash;
    }

    public String[] commandPowershell(String scriptPath) {

        String[] commandBash = new String[] {
            "powershell.exe",
            "-ExecutionPolicy",
            "Bypass",
            "-File",
            scriptPath
        };

        commands.add(commandBash.toString());
        return commandBash;
    }


}
