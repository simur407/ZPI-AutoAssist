package pl.edu.pwr.zpi.autoasystent.presenters;

import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.service.MotService;
import pl.edu.pwr.zpi.autoasystent.view.dialog.MotAddDialog;

/**
 * Created by argo on 07 maj.
 */
public class MotAddPresenter {

    MotAddDialog dialog;

    public MotAddPresenter(MotAddDialog dialog) {
        this.dialog = dialog;
    }

    public void saveMot(Mot mot) {

        MotService.getInstance().saveMot(mot);
    }
}
