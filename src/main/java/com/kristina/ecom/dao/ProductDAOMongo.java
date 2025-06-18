package com.kristina.ecom.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.kristina.ecom.domain.Product;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProductDAOMongo implements DAO<String, Product> {
  private MongoDataSourceFactory dataSourceFactory;
  private MongoCollection<Document> collection;

  public ProductDAOMongo() {
    this.dataSourceFactory = MongoDataSourceFactory.getInstance();
    this.collection = dataSourceFactory.getDatabase().getCollection("products");
  }

  @Override
  public Product create(Product product) throws DAOException {
    if (product == null)
      return null;
      
      try {
      Document document = UtilDAOMongo.toDocument(product);
      collection.insertOne(document);
      return product;
      } catch (MongoException ex) {
        throw new DAOException("Product creation error", ex);
      }
   }
  
  @Override
  public  Product read(String id) throws DAOException {
    Document document = collection.find(eq("_id", new ObjectId(id))).first();

    if (document != null) {
      Product product = UtilDAOMongo.toProduct(document);
      return product;
    } else {
      System.out.println("Coudn't find product with id: " + id);
    }

    return null;
  }

  @Override
  public List<Product> readAll() throws DAOException {
    List<Product> products = new ArrayList<>();
    FindIterable<Document> documents = collection.find();

    for (Document document : documents) {
      if (document != null) {
        Product product = UtilDAOMongo.toProduct(document);
      products.add(product);
      }
    }
    return products;
  }

  @Override
  public int update(Product product) throws DAOException {
    if (product == null)
      return 0;

    try {
    Bson query = eq("_id", product.getId());
    UpdateResult result = collection.replaceOne(query, UtilDAOMongo.toDocument(product));
    return (int) result.getModifiedCount();
    } catch (MongoException ex) {
      throw new DAOException("Product update error.", ex);
    }
  }

  @Override
  public int delete(String id) throws DAOException {
    try {
    DeleteResult result = collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    return (int) result.getDeletedCount();
    } catch (MongoException ex) {
      throw new DAOException("Product delete error", ex);
    }
  }
}

