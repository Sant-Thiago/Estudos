package com.example.system;

import com.example.model.Rede;

public class Creator {
    
    Rede rede = new Rede();
    Computer computer = new Computer();

    public Rede criarRede() {
        try {
            rede.setNome(computer.getNomeRede());
            rede.setInterfaceRede(computer.getInterfaceRede());
            rede.setSinal(computer.getSinalRede());
            rede.setTransmissao(computer.getTransmissaoRede());
            rede.setBssid(computer.getBssidRede());
            return rede;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
