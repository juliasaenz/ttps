package quecomemos.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final EntityManagerFactory em = Persistence.createEntityManagerFactory("unlp");

	private EMF() {
    }

	public static EntityManagerFactory getEMF() {
		return em;
	}
}
