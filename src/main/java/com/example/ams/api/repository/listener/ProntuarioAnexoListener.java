package com.example.ams.api.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.example.ams.api.AmsApiApplication;
import com.example.ams.api.model.Prontuario;
import com.example.ams.api.storage.S3;

public class ProntuarioAnexoListener {


	@PostLoad
	public void postLoad(Prontuario prontuario) {
		if (StringUtils.hasText(prontuario.getAnexo())) {
			S3 s3 = AmsApiApplication.getBean(S3.class);
			prontuario.setUrlAnexo(s3.configurarUrl(prontuario.getAnexo()));
		}
	}

}
