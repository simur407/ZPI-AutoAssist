package pl.edu.pwr.zpi.autoasystent.service;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;

/**
 * Created by Marek on 10.04.2016.
 */
public class ModelService {

    private static ModelService instance = null;

    public static ModelService getInstance() {
        if (instance == null) {
            instance = new ModelService();
        }
        return instance;
    }

    private ModelService() {

    }

    public void saveModel(Model model) {
        Model.save(model);
    }

    public void deleteModel(Model model) {
        Model.delete(model);
    }

    public void saveModelList(List<Model> models) {
        Model.saveInTx(models);
    }

    public List<Model> getModelsByMake(Make make) {
        return Model.find(Model.class, "make = ?", String.valueOf(make.getId()));
    }
}
