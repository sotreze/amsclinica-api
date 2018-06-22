package com.example.ams.api.model;

import java.time.LocalTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agenda.class)
public abstract class Agenda_ {

	public static volatile SingularAttribute<Agenda, Long> codigo;
	public static volatile SingularAttribute<Agenda, String> diaSemana;
	public static volatile SingularAttribute<Agenda, LocalTime> hora;
	public static volatile SingularAttribute<Agenda, Boolean> ativo;
}