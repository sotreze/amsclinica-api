package com.example.ams.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuarioPermissao.class)
public class UsuarioPermissao_ {

	public static volatile SingularAttribute<UsuarioPermissao, Long> codigo;
	public static volatile SingularAttribute<UsuarioPermissao, Usuario> usuario;
	public static volatile SingularAttribute<UsuarioPermissao, Permissao> permissao;

}