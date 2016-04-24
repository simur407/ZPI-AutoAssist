package pl.edu.pwr.zpi.autoasystent.service;

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

    public void SaveModel(Model model) {
        Model.save(model);
    }

    public void DeleteModel(Model model) {
        Model.delete(model);
    }
}
