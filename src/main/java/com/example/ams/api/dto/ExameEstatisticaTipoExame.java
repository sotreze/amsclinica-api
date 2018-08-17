package com.example.ams.api.dto;


import java.math.BigDecimal;

import com.example.ams.api.model.TipoExame;

public class ExameEstatisticaTipoExame {

	private TipoExame tipoExame;
	
	private BigDecimal total;


	public ExameEstatisticaTipoExame(TipoExame tipoExame, BigDecimal total) {
		this.tipoExame = tipoExame;
		this.total = total;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}