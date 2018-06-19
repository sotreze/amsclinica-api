package com.example.ams.api.dto;

import java.math.BigDecimal;

import com.example.ams.api.model.Exame;

public class LancamentoEstatisticaExame {

private Exame exame;

	private BigDecimal total;

	public LancamentoEstatisticaExame(Exame exame, BigDecimal total) {
		this.exame = exame;
		this.total = total;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
