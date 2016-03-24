package edu.nju.git.ui.control.function;

import edu.nju.git.VO.RepoVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.ui.control.GitPanel;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * RepoDetail controller
 */
public class RepoDetailFunction extends GitPanel{
    /**
     * FXML components
     */
    @FXML private Label generalName;
    @FXML private Hyperlink name;
    @FXML private Label description;
    @FXML private Label create;
    @FXML private Label update;
    @FXML private Hyperlink url;
    @FXML private Hyperlink owner;
    @FXML private Label language;
    @FXML private Label size;
    @FXML private Label star;
    @FXML private Label fork;
    @FXML private Label contri;
    @FXML private Label subcri;
    @FXML private Label collab;

    /**
     * repository business logic service
     */
    private RepoBlService service;
    /**
     * vo of the repository
     */
    private RepoVO repoVO;
    @Override
    public void initPanel(Object[] bundle) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                repoVO = service.getRepoBasicInfo((String)bundle[0],(String)bundle[1]);
                if (repoVO == null) return null;
                generalName.setText(repoVO.getName());
                name.setText(repoVO.getName());
                description.setText(repoVO.getDescription());
                create.setText(repoVO.getCreate_at());
                update.setText(repoVO.getUpdate_at());
                url.setText(repoVO.getUrl());
                owner.setText(repoVO.getOwnerName());
                language.setText(repoVO.getLanguage());
                size.setText(repoVO.getSize()+"");
                star.setText(repoVO.getNum_stars()+"");
                fork.setText(repoVO.getNum_forks()+"");
                contri.setText(repoVO.getNum_ontributors()+"");
                subcri.setText(repoVO.getNum_subscribers()+"");
                collab.setText(repoVO.getNum_collaboration()+"");
                return null;
            }
        };
        new Thread(task).start();
    }

    @Override
    public void setChildren(Parent region) {

    }

    @Override
    public void removeChild() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = BlFactory.instance().getRepoBlService();
    }

}
