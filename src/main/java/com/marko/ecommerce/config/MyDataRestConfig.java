package com.marko.ecommerce.config;

import com.marko.ecommerce.entity.Country;
import com.marko.ecommerce.entity.Product;
import com.marko.ecommerce.entity.ProductCategory;
import com.marko.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private final EntityManager entityManager;

    public MyDataRestConfig(EntityManager theEntityManager){
        this.entityManager=theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};

        //disable HTTP methods for Product: PUT,POST and DELETE
        disableHttpMethods(Product.class,config, theUnsupportedActions);

        //disable HTTP methods for ProductCategory: PUT,POST and DELETE
        disableHttpMethods(ProductCategory.class,config, theUnsupportedActions);

        //disable HTTP methods for Country: PUT,POST and DELETE
        disableHttpMethods(Country.class,config, theUnsupportedActions);

        //disable HTTP methods for State: PUT,POST and DELETE
        disableHttpMethods(State.class,config, theUnsupportedActions);



        //call an internal helper method
        exposeIds(config);
    }

    private void disableHttpMethods(Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for(EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
