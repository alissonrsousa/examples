package br.com.digicade.geosite.geoestatistica.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LinhaRelatorioGerenciaEspacializacaoVO {

	@Id
	@Column(name="ID_MOTIVO")
	private Long id;
	@Column(name="MOTIVO")
	private String motivo;	
	@Column(name="QTDE_LOGRADOURO")
	private Long quantidadeLogradouro;
	@Column(name="QTDE_ESTACAO")
	private Long quantidadeEstacao;
	@Column(name="QTDE_CX_TERMINAL")
	private Long quantidadeCaixaTerminal;
	@Column(name="QTDE_SEC_SERVICO")
	private Long quantidadeSecaoServico;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Long getQuantidadeLogradouro() {
		return quantidadeLogradouro;
	}
	public void setQuantidadeLogradouro(Long quantidadeLogradouro) {
		this.quantidadeLogradouro = quantidadeLogradouro;
	}
	public Long getQuantidadeEstacao() {
		return quantidadeEstacao;
	}
	public void setQuantidadeEstacao(Long quantidadeEstacao) {
		this.quantidadeEstacao = quantidadeEstacao;
	}
	public Long getQuantidadeCaixaTerminal() {
		return quantidadeCaixaTerminal;
	}
	public void setQuantidadeCaixaTerminal(Long quantidadeCaixaTerminal) {
		this.quantidadeCaixaTerminal = quantidadeCaixaTerminal;
	}
	public Long getQuantidadeSecaoServico() {
		return quantidadeSecaoServico;
	}
	public void setQuantidadeSecaoServico(Long quantidadeSecaoServico) {
		this.quantidadeSecaoServico = quantidadeSecaoServico;
	}	
	
}
