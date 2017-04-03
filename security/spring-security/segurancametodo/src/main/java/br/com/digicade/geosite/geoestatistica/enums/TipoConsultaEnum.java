package br.com.digicade.geosite.geoestatistica.enums;

public enum TipoConsultaEnum {
	
	FEICOES_GRAFICAS_MUB(1), ESPACIALIZACAO_DADOS(2), GERENCIA_ESPACIALIZACAO(3);
	
	private final int valor; 
	
	TipoConsultaEnum(int valorOpcao){ 
		valor = valorOpcao; 
	} 
	
	public int getValor(){ 
		return valor; 
	}
	
	public static TipoConsultaEnum getByValor(Integer valor){
		switch (valor) {
		case 1:
			return FEICOES_GRAFICAS_MUB;
		case 2:
			return ESPACIALIZACAO_DADOS;			
		default:
			return GERENCIA_ESPACIALIZACAO;
		}
	}

}
