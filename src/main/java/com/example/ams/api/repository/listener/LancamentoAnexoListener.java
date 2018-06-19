package com.example.ams.api.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.example.ams.api.AmsApiApplication;
import com.example.ams.api.model.Lancamento;
import com.example.ams.api.storage.S3;

public class LancamentoAnexoListener {


	@PostLoad
	public void postLoad(Lancamento lancamento) {
		if (StringUtils.hasText(lancamento.getAnexo())) {
			S3 s3 = AmsApiApplication.getBean(S3.class);
			lancamento.setUrlAnexo(s3.configurarUrl(lancamento.getAnexo()));
		}
	}

}
