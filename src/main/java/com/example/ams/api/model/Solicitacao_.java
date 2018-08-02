package com.example.ams.api.model;

import java.time.LocalDate;

import javax.persistence.metamodel.SingularAttribute;

public abstract class Solicitacao_ {
	
	public static volatile SingularAttribute<Solicitacao, Long> codigo;
	public static volatile SingularAttribute<Solicitacao, Agenda> agenda;
	public static volatile SingularAttribute<Solicitacao, Paciente> paciente;
	public static volatile SingularAttribute<Solicitacao, String> descricao;
	public static volatile SingularAttribute<Solicitacao, LocalDate> data;

}
