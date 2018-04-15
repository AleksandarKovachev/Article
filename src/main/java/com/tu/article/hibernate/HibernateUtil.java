package com.tu.article.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		Configuration config = new Configuration();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		MetadataSources metadataSources = new MetadataSources();
		config.configure();
		builder.applySettings(config.getProperties());
		// metadataSources.addAnnotatedClass(User.class);
		Metadata metadata = metadataSources.buildMetadata(builder.build());
		sessionFactory = metadata.buildSessionFactory();
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

}
