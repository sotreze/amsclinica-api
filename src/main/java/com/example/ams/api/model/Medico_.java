package com.example.ams.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

//import org.joda.time.DateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medico.class)
public abstract class Medico_ {

	public static volatile SingularAttribute<Medico, Long> codigo;
	public static volatile SingularAttribute<Medico, String> nome;
	public static volatile SingularAttribute<Medico, String> especializacao;
	public static volatile SingularAttribute<Medico, String> crm;
	public static volatile SingularAttribute<Medico, Pessoa> pessoa;
	//public static volatile SingularAttribute<Medico, Agenda> agenda;
}
