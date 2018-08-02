package com.example.ams.api.repository.projection;

public class ResumoUsuarioPermissao {

	private Long codigo;
	private String usuario;
	private String permissao;

	public ResumoUsuarioPermissao(Long codigo, String usuario, String permissao) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.permissao = permissao;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
