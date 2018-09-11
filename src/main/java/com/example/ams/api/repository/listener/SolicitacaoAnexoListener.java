
package com.example.ams.api.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.example.ams.api.AmsApiApplication;
import com.example.ams.api.model.Solicitacao;
import com.example.ams.api.storage.S3;

public class SolicitacaoAnexoListener {


	@PostLoad
	public void postLoad(Solicitacao solicitacao) {
		if (StringUtils.hasText(solicitacao.getAnexo())) {
			S3 s3 = AmsApiApplication.getBean(S3.class);
			solicitacao.setUrlAnexo(s3.configurarUrl(solicitacao.getAnexo()));
		}
	}

}
