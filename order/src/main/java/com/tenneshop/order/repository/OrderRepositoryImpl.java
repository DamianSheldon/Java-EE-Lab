package com.tenneshop.order.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.tenneshop.order.entity.CustomerOrder;
import com.tenneshop.order.entity.Part;

@Repository
public class OrderRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

	private EntityManager em;

	private static final Logger logger = Logger.getLogger(OrderRepositoryImpl.class.getName());

	public OrderRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		
		em = entityManager;
	}
	
	public void prepopulateTestOrders() {
        createPart("1234-5678-01", 1, "ABC PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        createPart("9876-4321-02", 2, "DEF PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        createPart("5456-6789-03", 3, "GHI PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        createPart("ABCD-XYZW-FF", 5, "XYZ PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        createPart("SDFG-ERTY-BN", 7, "BOM PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
	}
	    
    public void createPart(String partNumber,
            int revision,
            String description,
            java.util.Date revisionDate,
            String specification,
            Serializable drawing) {
        try {
            Part part = new Part(partNumber,
                    revision,
                    description,
                    revisionDate,
                    specification,
                    drawing);
            logger.log(Level.INFO, "Created part {0}-{1}", new Object[]{partNumber, revision});
            em.persist(part);
            logger.log(Level.INFO, "Persisted part {0}-{1}", new Object[]{partNumber, revision});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
