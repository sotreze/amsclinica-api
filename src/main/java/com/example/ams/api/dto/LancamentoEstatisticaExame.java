package com.example.ams.api.dto;

import java.math.BigDecimal;

import com.example.ams.api.model.TipoExame;

public class LancamentoEstatisticaExame {

private TipoExame tipoExame;

	private BigDecimal total;

	public LancamentoEstatisticaExame(TipoExame tipoExame, BigDecimal total) {
		this.tipoExame = tipoExame;
		this.total = total;
	}

	public TipoExame getExame() {
		return tipoExame;
	}

	public void setExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
