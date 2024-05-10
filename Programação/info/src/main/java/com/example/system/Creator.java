package com.example.system;

import com.example.model.Rede;

public class Creator {
    
    Rede rede = new Rede();
    Computer computer = new Computer();

    public void criarRede() {
        try {
            rede.setNome(computer.getNomeRede());
            rede.setInterfaceRede(computer.getInterfaceRede());
            rede.setSinal(computer.getSinalRede());
            rede.setTransmissao(computer.getTransmissaoRede());
            rede.setBssid(computer.getBssidRede());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
