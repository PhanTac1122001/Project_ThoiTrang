package com.n3.project_thoitrang.repository.impl;

import com.n3.project_thoitrang.model.entity.Category;
import com.n3.project_thoitrang.model.entity.Image;
import com.n3.project_thoitrang.model.entity.Product_Detail;
import com.n3.project_thoitrang.repository.IProductDetailRepository;
import com.n3.project_thoitrang.service.IImageService;
import com.n3.project_thoitrang.service.impl.UploadFileImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductDetailReopositoryImpl implements IProductDetailRepository {


    private final SessionFactory sessionFactory;
    private final UploadFileImpl uploadFile;
    private final IImageService imageService;

    @Override
    public List<Product_Detail> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select pd from Product_Detail as pd order by id", Product_Detail.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

    }

    @Override
    public List<Product_Detail> findByProductId(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select pd from Product_Detail as pd join pd.product as p where p.productId = :id order by pd.productDetailId", Product_Detail.class)
                    .setParameter("id", id)
                    .getResultList();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(findById(id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    @Transactional
    public void save(Product_Detail product_detail, List<MultipartFile> images) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            //case new product detail and have images
            if (product_detail.getProductDetailId() == null && images.get(0).getOriginalFilename() != "") {
              String sku= String.valueOf(UUID.randomUUID());
                if(!images.isEmpty()){
                    String myImage = uploadFile.uploadLocal(images.get(0));
                    product_detail.setImage(myImage);
                }
                product_detail.setSku(sku);
                session.save(product_detail);

                for (MultipartFile image : images) {
                    Image newimg = new Image();
                    newimg.setProductDetail(product_detail);
                    newimg.setImageUrl(uploadFile.uploadLocal(image));
                    imageService.save(newimg);
                }
            } else if (product_detail.getProductDetailId() != null && images.get(0).getOriginalFilename() != "") {//case has product detail to edit and have new images to edit
                //delete all which has product detail id
                Query query = session.createQuery("delete from Image where productDetail.productDetailId = :productDetailId").setParameter("productDetailId", product_detail.getProductDetailId());
                query.executeUpdate();
                session.update(product_detail);
                for (MultipartFile image : images) {
                    Image newimg = new Image();
                    newimg.setProductDetail(product_detail);
                    newimg.setImageUrl(uploadFile.uploadLocal(image));
                    imageService.save(newimg);
                }
            } else if (product_detail.getProductDetailId() == null) {//case new product detail and 0 image
                session.save(product_detail);
            } else if (product_detail.getProductDetailId() != null) { //case edit product detail but keep images with product detail id
                session.update(product_detail);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    @Override
    public Product_Detail findById(Long id) {
        Session session = sessionFactory.openSession();
        try
        {
            // HQL - Hibernate Query Language
            return session.get(Product_Detail.class, id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            session.close();
        }
    }
}
