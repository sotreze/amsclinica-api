package com.example.ams.api.model;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Periodo.class)
public abstract class Periodo_ {

	public static volatile SingularAttribute<Periodo, Long> codigo;
	public static volatile SingularAttribute<Periodo, String> nome;
	public static volatile SingularAttribute<Periodo, LocalDate> dataInicial;
	public static volatile SingularAttribute<Periodo, LocalDate> dataFinal;
	public static volatile SingularAttribute<Periodo, Boolean> ativo;
	public static volatile SingularAttribute<Periodo, Agenda> agenda;

}