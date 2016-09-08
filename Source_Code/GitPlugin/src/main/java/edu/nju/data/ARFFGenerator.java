package edu.nju.data;

import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.experiment.InstanceQuery;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * generate arff file for weka package
 * @author cuihao
 */
@Repository
public class ARFFGenerator {

    @Resource
    private SessionFactory sessionFactory;

    /**
     * generate weka dataSource file
     * @param instances weka Instances
     */
    public void generateArffFile(Instances instances) {
        ArffSaver saver = new ArffSaver();
        saver.setInstances(instances);
        try {
            saver.setFile(new File("/WEKA/repo_popular.arff"));
            saver.writeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * generate instances from database
     * @param start first index of repos
     * @param max max number of repos
     * @return instances
     */
    public Instances generatePopularInstanceFromDB(int start, int max) {
        List<SecRepoEntity> secRepoEntities = getRepoEntities(start, max);
        return generatePopularInstance(secRepoEntities);
    }

    /**
     * generate instances for study (without filter)
     * @return Instances
     */
    public Instances generatePopularInstance(List<SecRepoEntity> entities) {
        //set attributes
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("fork"));
        attributes.add(new Attribute("size"));
        attributes.add(new Attribute("sum"));
        attributes.add(new Attribute("avg"));
        attributes.add(new Attribute("weight"));
        //set instances
        Instances instances = new Instances("repo_popular",attributes,0);
        instances.setClassIndex(instances.numAttributes() - 1);
        //add instance
        for (SecRepoEntity secRepoEntity: entities) {
            Instance instance = new DenseInstance(attributes.size());
            instance.setValue(0,secRepoEntity.getForkCount());
            instance.setValue(1,secRepoEntity.getSize());
            instance.setValue(2,secRepoEntity.getSumFollower());
            instance.setValue(3,secRepoEntity.getAvgFollower());
            instance.setValue(4,secRepoEntity.getWeightFollower());
            instances.add(instance);
        }
        return instances;
    }

    private List<SecRepoEntity> getRepoEntities(int start, int max) {
        Session session = sessionFactory.openSession();
        List<SecRepoEntity> entities = null;
        Query query = session.createQuery(" from SecRepoEntity where createAt<'2014-07-05' ");
        query.setMaxResults(max);
        query.setFirstResult(start);
        entities = query.list();
        session.close();
        return entities;
    }

}
