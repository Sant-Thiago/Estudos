package model.componentes;

import util.reports.CreatePDFInfos;
import util.reports.TablePrinter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Bateria extends Componente {
	private Double amperagem;
	private String nomeDispositivo;
	private String numeroSerial;
	private String quimica;
	private Double voltagem;
	private String unidadesCapacidade;
	private Double capacidadeAtual;
	private int ciclos;
	private Double capacidadeDesign;
	private Double tempoRestanteInstantaneo;
	private Double tempoRestanteEstimado;
	private Double taxaUsoEnergia;
	private Double temperatura;
	private Double capacidadeMaxima;
	private Double percentualCapacidadeRestante;
	private String dataFabricacao;
	private Double bateriaAtual;

	public Bateria() {
		this.dataCaptura = LocalDateTime.now();
	}

	public Bateria(Double amperagem, String nomeDispositivo, String numeroSerial, String quimica, Double voltagem,
			String unidadesCapacidade, Double capacidadeAtual, int ciclos, Double capacidadeDesign,
			Double tempoRestanteInstantaneo, Double tempoRestanteEstimado, Double taxaUsoEnergia, Double temperatura,
			Double capacidadeMaxima, Double percentualCapacidadeRestante, String dataFabricacao, Double bateriaAtual) {
		super();
		this.amperagem = amperagem;
		this.nomeDispositivo = nomeDispositivo;
		this.numeroSerial = numeroSerial;
		this.quimica = quimica;
		this.voltagem = voltagem;
		this.unidadesCapacidade = unidadesCapacidade;
		this.capacidadeAtual = capacidadeAtual;
		this.ciclos = ciclos;
		this.capacidadeDesign = capacidadeDesign;
		this.tempoRestanteInstantaneo = tempoRestanteInstantaneo;
		this.tempoRestanteEstimado = tempoRestanteEstimado;
		this.taxaUsoEnergia = taxaUsoEnergia;
		this.temperatura = temperatura;
		this.capacidadeMaxima = capacidadeMaxima;
		this.percentualCapacidadeRestante = percentualCapacidadeRestante;
		this.dataFabricacao = dataFabricacao;
		this.dataCaptura = LocalDateTime.now();
		this.bateriaAtual = bateriaAtual;
	}

	public LocalDateTime getDataHoraCaptura() {
		return dataCaptura;
	}

	public Double getBateriaAtual() {
		return bateriaAtual;
	}

	public void setBateriaAtual(Double bateriaAtual) {
		this.bateriaAtual = bateriaAtual;
	}

	public void setDataHoraCaptura(LocalDateTime dataCaptura) {
		this.dataCaptura = dataCaptura;
	}

	public Double getAmperagem() {
		return amperagem;
	}

	public void setAmperagem(Double amperagem) {
		this.amperagem = amperagem;
	}

	public String getNomeDispositivo() {
		return nomeDispositivo;
	}

	public void setNomeDispositivo(String nomeDispositivo) {
		this.nomeDispositivo = nomeDispositivo;
	}

	public String getNumeroSerial() {
		return numeroSerial;
	}

	public void setNumeroSerial(String numeroSerial) {
		this.numeroSerial = numeroSerial;
	}

	public String getQuimica() {
		return quimica;
	}

	public void setQuimica(String quimica) {
		this.quimica = quimica;
	}

	public Double getVoltagem() {
		return voltagem;
	}

	public void setVoltagem(Double voltagem) {
		this.voltagem = voltagem;
	}

	public String getUnidadesCapacidade() {
		return unidadesCapacidade;
	}

	public void setUnidadesCapacidade(String unidadesCapacidade) {
		this.unidadesCapacidade = unidadesCapacidade;
	}

	public Double getCapacidadeAtual() {
		return capacidadeAtual;
	}

	public void setCapacidadeAtual(Double capacidadeAtual) {
		this.capacidadeAtual = capacidadeAtual;
	}

	public Integer getCiclos() {
		return ciclos;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}

	public Double getCapacidadeDesign() {
		return capacidadeDesign;
	}

	public void setCapacidadeDesign(Double capacidadeDesign) {
		this.capacidadeDesign = capacidadeDesign;
	}

	public Double getTempoRestanteInstantaneo() {
		return tempoRestanteInstantaneo;
	}

	public void setTempoRestanteInstantaneo(Double tempoRestanteInstantaneo) {
		this.tempoRestanteInstantaneo = tempoRestanteInstantaneo;
	}

	public Double getTempoRestanteEstimado() {
		return tempoRestanteEstimado;
	}

	public void setTempoRestanteEstimado(Double tempoRestanteEstimado) {
		this.tempoRestanteEstimado = tempoRestanteEstimado;
	}

	public Double getTaxaUsoEnergia() {
		return taxaUsoEnergia;
	}

	public void setTaxaUsoEnergia(Double taxaUsoEnergia) {
		this.taxaUsoEnergia = taxaUsoEnergia;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public void setCapacidadeMaxima(Double capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public Double getPercentualCapacidadeRestante() {
		return percentualCapacidadeRestante;
	}

	public void setPercentualCapacidadeRestante(Double percentualCapacidadeRestante) {
		this.percentualCapacidadeRestante = percentualCapacidadeRestante;
	}

	public String getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(String dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String getComponente() {
		return this.getClass().getSimpleName();
	}

	@Override
	public List<List<String>> tabela() {
		return Arrays.asList(Arrays.asList("", "Bateria"),
				Arrays.asList("Data Hora captura", String.valueOf(dataCaptura)),
				Arrays.asList("ID", Optional.ofNullable(super.idComponente).map(Object::toString).orElse("N/A")),
				Arrays.asList("Amperagem", Optional.ofNullable(arredondaValor(amperagem)).map(Object::toString).orElse("N/A") + " mAh"),
				Arrays.asList("Nome do Dispositivo", Optional.ofNullable(nomeDispositivo).orElse("N/A")),
				Arrays.asList("Número Serial", Optional.ofNullable(numeroSerial).orElse("N/A")),
				Arrays.asList("Química", Optional.ofNullable(quimica).orElse("N/A")),
				Arrays.asList("Nome", Optional.ofNullable(modelo).orElse("N/A")),
				Arrays.asList("Voltagem", Optional.ofNullable(arredondaValor(voltagem)).map(Object::toString).orElse("N/A") + " V"),
				Arrays.asList("Unidades de Capacidade", Optional.ofNullable(unidadesCapacidade).orElse("N/A")),
				Arrays.asList("Capacidade Atual",
						Optional.ofNullable(arredondaValor(capacidadeAtual)).map(Object::toString).orElse("N/A") + " Wh"),
				Arrays.asList("Ciclos", Optional.ofNullable(ciclos).map(Object::toString).orElse("N/A")),
				Arrays.asList("Capacidade Design",
						Optional.ofNullable(arredondaValor(capacidadeDesign)).map(Object::toString).orElse("N/A") + " Wh"),
				Arrays.asList("Tempo Restante Instantâneo",
						Optional.ofNullable(tempoRestanteInstantaneo).map(Object::toString).orElse("N/A")),
				Arrays.asList("Tempo Restante Estimado",
						Optional.ofNullable(tempoRestanteEstimado).map(Object::toString).orElse("N/A")),
				Arrays.asList("Taxa de Uso de Energia",
						Optional.ofNullable(taxaUsoEnergia).map(Object::toString).orElse("N/A") + " mAh"),
				Arrays.asList("Temperatura", Optional.ofNullable(temperatura).map(Object::toString).orElse("N/A")),
				Arrays.asList("Capacidade Máxima",
						Optional.ofNullable(arredondaValor(capacidadeMaxima)).map(Object::toString).orElse("N/A") + " Wh"),
				Arrays.asList("Percentual Capacidade Restante",
						Optional.ofNullable(percentualCapacidadeRestante).map(Object::toString).orElse("N/A")),
				Arrays.asList("Data de Fabricação", Optional.ofNullable(dataFabricacao).orElse("N/A")),
				Arrays.asList("Fabricante", Optional.ofNullable(fabricante).orElse("N/A")),
				Arrays.asList("Bateria Atual (%)",
						Optional.ofNullable(bateriaAtual).map(Object::toString).orElse("N/A") + "%"));
	}

	@Override
	public String tabelaConvert() {
		return TablePrinter.printTable(tabela());
	}

	@Override
	public String pdfLayout() {
		return CreatePDFInfos.gerarLayoutPDF(tabela());
	}

	@Override
	public String getUnidadeMedida() {
		return "%";
	}

	@Override
	public Double getDadoCaptura() {
		return bateriaAtual;
	}

	public Double arredondaValor(Double valor){
		if(!valor.equals(null)){
			return (double) Math.round(valor * 100) / 100;
		}
		return null;
	}
}
