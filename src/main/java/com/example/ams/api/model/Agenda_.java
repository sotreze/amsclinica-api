package com.example.ams.api.model;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agenda.class)
public abstract class Agenda_ {

	public static volatile SingularAttribute<Agenda, Long> codigo;
	public static volatile SingularAttribute<Agenda, Boolean> ativo;
	public static volatile SingularAttribute<Agenda, LocalDateTime> dataHora;
}