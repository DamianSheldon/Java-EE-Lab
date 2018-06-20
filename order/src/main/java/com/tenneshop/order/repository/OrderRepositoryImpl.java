package com.tenneshop.order.repository;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.tenneshop.order.entity.CustomerOrder;
import com.tenneshop.order.entity.LineItem;
import com.tenneshop.order.entity.Part;
import com.tenneshop.order.entity.PartKey;
import com.tenneshop.order.entity.Vendor;
import com.tenneshop.order.entity.VendorPart;

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
        
        addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "1234-5678-01", 1);
        addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "9876-4321-02", 2);
        addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "5456-6789-03", 3);
        addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "ABCD-XYZW-FF", 5);
        
        createVendor(100, "WidgetCorp",
                "111 Main St., Anytown, KY 99999", "Mr. Jones",
                "888-777-9999");
        createVendor(200, "Gadget, Inc.",
                "123 State St., Sometown, MI 88888", "Mrs. Smith",
                "866-345-6789");
        
        createVendorPart("1234-5678-01", 1,
                "PART1", 100.00, 100);
        createVendorPart("9876-4321-02", 2,
                "PART2", 10.44, 200);
        createVendorPart("5456-6789-03", 3,
                "PART3", 76.23, 200);
        createVendorPart("ABCD-XYZW-FF", 5,
                "PART4", 55.19, 100);
        createVendorPart("SDFG-ERTY-BN", 7,
                "PART5", 345.87, 100);
        
        Integer orderId = new Integer(1111);
        createOrder(orderId, 'N', 10,
                "333 New Court, New City, CA 90000");
        addLineItem(orderId, "1234-5678-01", 1, 3);
        addLineItem(orderId, "9876-4321-02", 2, 5);
        addLineItem(orderId, "ABCD-XYZW-FF", 5, 7);

        orderId = new Integer(4312);
        createOrder(orderId, 'N', 0,
                "333 New Court, New City, CA 90000");
        addLineItem(orderId, "SDFG-ERTY-BN", 7, 1);
        addLineItem(orderId, "ABCD-XYZW-FF", 5, 3);
        addLineItem(orderId, "1234-5678-01", 1, 15);
        
        em.flush();
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
    
    public void addPartToBillOfMaterial(String bomPartNumber,
            int bomRevision,
            String partNumber,
            int revision) {
        logger.log(Level.INFO, "BOM part number: {0}", bomPartNumber);
        logger.log(Level.INFO, "BOM revision: {0}", bomRevision);
        logger.log(Level.INFO, "Part number: {0}", partNumber);
        logger.log(Level.INFO, "Part revision: {0}", revision);
        
        PartKey bomKey = new PartKey();
        bomKey.setPartNumber(bomPartNumber);
        bomKey.setRevision(bomRevision);
        
        Part bom = em.find(Part.class, bomKey);
        logger.log(Level.INFO, "BOM Part found: {0}", bom.getPartNumber());
        
        PartKey partKey = new PartKey();
        partKey.setPartNumber(partNumber);
        partKey.setRevision(revision);
        
        Part part = em.find(Part.class, partKey);
        logger.log(Level.INFO, "Part found: {0}", part.getPartNumber());
        bom.getParts().add(part);
        part.setBomPart(bom);
    }
    
    public void createVendor(int vendorId,
            String name,
            String address,
            String contact,
            String phone) {
    	Vendor vendor = new Vendor(vendorId, name, address, contact, phone);
        try {
			em.persist(vendor);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void createVendorPart(String partNumber,
            int revision,
            String description,
            double price,
            int vendorId) {
        try {
            PartKey pkey = new PartKey();
            pkey.setPartNumber(partNumber);
            pkey.setRevision(revision);
            
            Part part = em.find(Part.class, pkey);
            
            VendorPart vendorPart = new VendorPart(description, price, part);
            em.persist(vendorPart);
            
            Vendor vendor = em.find(Vendor.class, vendorId);
            vendor.addVendorPart(vendorPart);
            vendorPart.setVendor(vendor);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public void createOrder(Integer orderId, char status, int discount, String shipmentInfo) {
        try {
            CustomerOrder order = new CustomerOrder(orderId, status, discount, shipmentInfo);
            em.persist(order);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public void addLineItem(Integer orderId, String partNumber, int revision, int quantity) {
        try {
            CustomerOrder order = em.find(CustomerOrder.class, orderId);
            logger.log(Level.INFO, "Found order ID {0}", orderId);
            
            PartKey pkey = new PartKey();
            pkey.setPartNumber(partNumber);
            pkey.setRevision(revision);
            
            Part part = em.find(Part.class, pkey);
            
            LineItem lineItem = new LineItem(order, quantity, part.getVendorPart());
            order.addLineItem(lineItem);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Couldn''t add {0} to order ID {1}.", new Object[]{partNumber, orderId});
            e.printStackTrace();
        }
    }
}
