package com.example.service;

import java.util.List;

public class Pegar {
    
    public String string(Integer index, List<String> listStrings) {
        for (int i = 0; i < listStrings.size(); i++) {
            if (i == index) return listStrings.get(i);
        }
        return null;
    }

}
