package com.example.model;

public class Rede {

    private Integer id;
    private String nome;
    private String interfaceRede;
    private Integer sinal;
    private Float transmissao;
    private String bssid;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getInterfaceRede() {
        return interfaceRede;
    }
    public void setInterfaceRede(String interfaceRede) {
        this.interfaceRede = interfaceRede;
    }
    public Integer getSinal() {
        return sinal;
    }
    public void setSinal(Integer sinal) {
        this.sinal = sinal;
    }
    public Float getTransmissao() {
        return transmissao;
    }
    public void setTransmissao(Float transmissao) {
        this.transmissao = transmissao;
    }
    public String getBssid() {
        return bssid;
    }
    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

}
