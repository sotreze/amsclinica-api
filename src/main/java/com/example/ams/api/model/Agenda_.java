package com.example.ams.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agenda.class)
public abstract class Agenda_ {

	public static volatile SingularAttribute<Agenda, Long> codigo;
	public static volatile SingularAttribute<Agenda, Boolean> ativo;
	public static volatile SingularAttribute<Agenda, String> email;
	public static volatile SingularAttribute<Agenda, LocalDate> data;
	public static volatile SingularAttribute<Agenda, String> hora;
	public static volatile SingularAttribute<Agenda, Medico> medico;
	public static volatile SingularAttribute<Agenda, Paciente> paciente;
	public static volatile SingularAttribute<Agenda, LocalDateTime> dataAgendamento;
	public static volatile SingularAttribute<Agenda, Horario> horario;
}