package edu.nju.data;

import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.json.JSONNode;
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
    public void generateArffFile(Instances instances, String path) {
        ArffSaver saver = new ArffSaver();
        saver.setInstances(instances);
        try {
            saver.setFile(new File(path));
            saver.writeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * generate instances from database
     * === Run information ===

     Scheme:       weka.classifiers.functions.MultilayerPerceptron -L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H a -R
     Relation:     repo_popular
     Instances:    45723
     Attributes:   6
     fork
     size
     sum
     avg
     weight
     star
     Test mode:    10-fold cross-validation

     === Classifier model (full training set) ===

     Linear Node 0
     Inputs    Weights
     Threshold    -1.1123887947275672
     Node 1    0.6596485082393743
     Node 2    2.451730279313244
     Node 3    1.6151656848854097
     Sigmoid Node 1
     Inputs    Weights
     Threshold    25.839806193924616
     Attrib fork    52.71725971820234
     Attrib size    -3.0552344347469726
     Attrib sum    -58.753921720365454
     Attrib avg    -122.22118288659625
     Attrib weight    160.37098347026202
     Sigmoid Node 2
     Inputs    Weights
     Threshold    -5.482052699953215
     Attrib fork    0.5846582881884442
     Attrib size    -0.7869252790178127
     Attrib sum    -1.1953236693005755
     Attrib avg    2.142161391070926
     Attrib weight    -1.3459363732147966
     Sigmoid Node 3
     Inputs    Weights
     Threshold    24.733163682062397
     Attrib fork    51.05651559222434
     Attrib size    -24.5627383596819
     Attrib sum    0.8719819728449698
     Attrib avg    2.133501000425092
     Attrib weight    -1.5291055608296875
     Class
     Input
     Node 0


     Time taken to build model: 14.71 seconds

     === Cross-validation ===
     === Summary ===

     Correlation coefficient                  0.2692
     Mean absolute error                      0.1448
     Root mean squared error                  0.2713
     Relative absolute error                 97.7075 %
     Root relative squared error             99.6564 %
     Total Number of Instances            45723


     * @param start first index of repos
     * @param max max number of repos
     * @return instances
     */
    public Instances generatePopularInstanceFromDB(int start, int max) {
        List<SecRepoEntity> secRepoEntities = getRepoEntities(start, max);
        return generatePopularInstance(secRepoEntities);
    }

    /**
     * generate activity instances from database
     * @param start first index of repos
     * @param max max number of repos
     * @return {@link Instances}
     */
    public Instances genetateActivityInstanceFromDB(int start, int max) {
        return generateActivityInstance(getFullRepoEntities(start, max));
    }

    /**
     * generate punch instances from database
     * @param start first index of repos
     * @param max max number of repos
     * @return {@link Instances}
     */
    public Instances generatePunchInstanceFromDB(int start, int max) {
        return generateActivityInstance(getFullRepoEntities(start, max));
    }

    /**
     * generate owner instances from database
     * @param start first index of repos
     * @param max max number of repos
     * @return {@link Instances}
     */
    public Instances generateOwnerInstanceFromDB(int start, int max) {
        return generateOwnerInstance(getFullRepoEntities(start, max));
    }


    /**
     * generate instances for study (without filter)
     * @return Instances
     */
    private Instances generatePopularInstance(List<SecRepoEntity> entities) {
        //set attributes
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("fork"));
        attributes.add(new Attribute("size"));
        attributes.add(new Attribute("sum"));
        attributes.add(new Attribute("avg"));
        attributes.add(new Attribute("weight"));
        attributes.add(new Attribute("star"));
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
            instance.setValue(5,secRepoEntity.getStarCount()>50?1:0);
            instances.add(instance);
        }
        return instances;
    }

    /**
     * generate activity instance
     * @param repoEntities repos
     * @return instances
     */
    //[{"days":[0,0,0,0,0,0,0],"total":0,"week":1441497600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1442102400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1442707200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1443312000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1443916800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1444521600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1445126400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1445731200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1446336000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1446940800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1447545600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1448150400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1448755200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1449360000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1449964800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1450569600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1451174400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1451779200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1452384000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1452988800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1453593600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1454198400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1454803200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1455408000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1456012800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1456617600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1457222400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1457827200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1458432000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1459036800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1459641600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1460246400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1460851200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1461456000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1462060800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1462665600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1463270400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1463875200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1464480000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1465084800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1465689600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1466294400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1466899200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1467504000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1468108800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1468713600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1469318400},{"days":[0,0,0,0,0,0,0],"total":0,"week":1469923200},{"days":[0,0,0,0,0,0,0],"total":0,"week":1470528000},{"days":[0,0,0,0,0,0,0],"total":0,"week":1471132800},{"days":[0,0,0,0,0,0,0],"total":0,"week":1471737600},{"days":[0,0,0,0,0,0,0],"total":0,"week":1472342400}]
    private Instances generateActivityInstance(List<SecRepoEntity> repoEntities) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < 364; i++) {
            attributes.add(new Attribute("day"+i));
        }

        Instances instances = new Instances("repo_activity",attributes,0);
        instances.setClassIndex(instances.numAttributes() - 1);

        for (SecRepoEntity entity: repoEntities) {
            String activity = entity.getActivityJson();
            if (activity!=null && !activity.isEmpty() && activity.contains("[")) {
                JSONArray array = new JSONArray(activity);
                Instance instance = new DenseInstance(attributes.size());
                for (int i = 0; i < array.length(); i++) {
                    JSONArray temp = array.getJSONObject(i).getJSONArray("days");
                    for (int j = 0; j < 7; j++) {
                        instance.setValue(i * 7 + j, temp.getInt(j));
                    }
                }
                instances.add(instance);
            }
        }
        return instances;
    }

    /**
     * generate punch instances
     * @param repoEntities list of repos
     * @return {@link Instances}
     */
    //[[0,0,3],[0,1,0],[0,2,0],[0,3,3],[0,4,0],[0,5,5],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,0],[0,13,2],[0,14,2],[0,15,2],[0,16,3],[0,17,3],[0,18,3],[0,19,0],[0,20,2],[0,21,4],[0,22,4],[0,23,0],[1,0,3],[1,1,0],[1,2,3],[1,3,3],[1,4,0],[1,5,1],[1,6,0],[1,7,0],[1,8,0],[1,9,2],[1,10,3],[1,11,4],[1,12,3],[1,13,0],[1,14,2],[1,15,3],[1,16,1],[1,17,4],[1,18,1],[1,19,2],[1,20,1],[1,21,1],[1,22,0],[1,23,2],[2,0,0],[2,1,1],[2,2,1],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,8],[2,10,6],[2,11,2],[2,12,11],[2,13,1],[2,14,1],[2,15,6],[2,16,10],[2,17,7],[2,18,0],[2,19,1],[2,20,1],[2,21,6],[2,22,7],[2,23,8],[3,0,6],[3,1,2],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,0],[3,9,4],[3,10,2],[3,11,4],[3,12,5],[3,13,8],[3,14,4],[3,15,2],[3,16,3],[3,17,4],[3,18,0],[3,19,3],[3,20,1],[3,21,6],[3,22,4],[3,23,4],[4,0,8],[4,1,3],[4,2,0],[4,3,1],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,3],[4,10,5],[4,11,5],[4,12,6],[4,13,5],[4,14,3],[4,15,23],[4,16,7],[4,17,12],[4,18,10],[4,19,2],[4,20,4],[4,21,3],[4,22,1],[4,23,1],[5,0,1],[5,1,2],[5,2,0],[5,3,1],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,4],[5,9,5],[5,10,1],[5,11,6],[5,12,6],[5,13,9],[5,14,4],[5,15,6],[5,16,5],[5,17,3],[5,18,3],[5,19,3],[5,20,4],[5,21,2],[5,22,5],[5,23,3],[6,0,1],[6,1,2],[6,2,5],[6,3,2],[6,4,2],[6,5,1],[6,6,0],[6,7,2],[6,8,1],[6,9,1],[6,10,1],[6,11,5],[6,12,0],[6,13,1],[6,14,8],[6,15,6],[6,16,9],[6,17,1],[6,18,1],[6,19,6],[6,20,5],[6,21,0],[6,22,0],[6,23,4]]
    private Instances generatePunchInstance(List<SecRepoEntity> repoEntities) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                attributes.add(new Attribute("d"+i+"w"+j));
            }
        }
        Instances instances = new Instances("repo_punch", attributes, 0);
        instances.setClassIndex(instances.numAttributes()-1);
        for (SecRepoEntity repoEntity: repoEntities) {
            String punch = repoEntity.getPunchJson();
            if (punch!=null && !punch.isEmpty() && punch.contains("[")) {
                JSONArray jsonArray = new JSONArray(punch);
                Instance instance = new DenseInstance(attributes.size());
                for (int i = 0; i < jsonArray.length(); i++) {
                    instance.setValue(i,jsonArray.getJSONArray(i).getInt(2));
                }
                instances.add(instance);
            }
        }
        return instances;
    }

    //{"all":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"owner":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
    private Instances generateOwnerInstance(List<SecRepoEntity> repoEntities) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            attributes.add(new Attribute("all"+i));
        }
        for (int i = 0; i < 52; i++) {
            attributes.add(new Attribute("owner"+i));
        }
        Instances instances = new Instances("repo_owner",attributes,0);
        instances.setClassIndex(instances.numAttributes() - 1);
        for (SecRepoEntity repoEntity: repoEntities) {
            String owner = repoEntity.getWeeklyJson();
            Instance instance = new DenseInstance(attributes.size());
            if (owner!=null && !owner.isEmpty() && owner.contains("{")) {
                JSONObject object = new JSONObject(owner);
                JSONArray array = object.getJSONArray("all");
                for (int i = 0; i < array.length(); i++) {
                    instance.setValue(i,array.getInt(i));
                }
                JSONArray array1 = object.getJSONArray("owner");
                for (int i = 0; i < array1.length(); i++) {
                    instance.setValue(i+52, array1.getInt(i));
                }
            }
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

    private List<SecRepoEntity> getFullRepoEntities(int start, int max) {
        Session session = sessionFactory.openSession();
        List<SecRepoEntity> entities = null;
        Query query = session.createQuery(" from SecRepoEntity ");
        query.setFirstResult(start);
        query.setMaxResults(max);
        entities = query.list();
        session.close();
        return entities;
    }

}