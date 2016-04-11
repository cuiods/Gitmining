package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.chart.ChartType;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * RepoStatistic panel controller <br>
 * Including buttons to other statistic panel
 * @author cuihao
 *
 */
public class RepoStatisticFunction extends FunctionPanel{
	
	@FXML private AnchorPane language;
	@FXML private AnchorPane createTime;
	@FXML private AnchorPane contributors;
	@FXML private AnchorPane collaborator;
	@FXML private AnchorPane size;
	@FXML private AnchorPane subscriber;
	@FXML private AnchorPane forks;
	@FXML private AnchorPane stars;
	
	@FXML private Label language_label;
	@FXML private Label createTime_label;
	@FXML private Label contributors_label;
	@FXML private Label collaborator_label;
	@FXML private Label size_label;
	@FXML private Label subscriber_label;
	@FXML private Label forks_label;
	@FXML private Label stars_label;
	
	@Override
	public String getLocationName() {
		return "RepoStatistic";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initLanguage();
		initCreateTime();
		initContributor();
		initCollaborator();
		initSize();
		initSubscriber();
		initForks();
		initStars();
	}
	
	private void labelAppear(Label label) {
		RotateTransition rotate = new RotateTransition(Duration.millis(1000), label);
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setFromAngle(0);
		rotate.setToAngle(360f);
		FadeTransition fade = new FadeTransition(Duration.millis(1000),label);
		fade.setFromValue(0);
		fade.setToValue(1f);
		ParallelTransition parallel = new ParallelTransition();
		parallel.getChildren().addAll(rotate, fade);
		parallel.play();
	}
	
	private void labelDisappear(Label label) {
		RotateTransition rotate = new RotateTransition(Duration.millis(1000), label);
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setFromAngle(360f);
		rotate.setToAngle(0);
		FadeTransition fade = new FadeTransition(Duration.millis(1000),label);
		fade.setFromValue(1);
		fade.setToValue(0);
		ParallelTransition parallel = new ParallelTransition();
		parallel.getChildren().addAll(rotate, fade);
		parallel.play();
	}

	private void initStars() {
		CubeButton button = new CubeButton();
		URL url1 = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/star.png");
		stars.getChildren().add(button.createContent(1.65, null, new Image(url1.toExternalForm()), Color.TRANSPARENT));
		button.play();
		stars.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(stars_label);
			}
		});
		stars.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(stars_label);
			}
		});
		stars.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoStars});
			}
		});
	}

	private void initForks() {
		CubeButton button = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/fork.png");
		forks.getChildren().add(button.createContent(1.65,null, new Image(url.toExternalForm()), Color.TRANSPARENT));
		button.play();
		forks.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(forks_label);
			}
		});
		forks.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(forks_label);
			}
		});
		forks.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoForks});
			}
		});
	}

	private void initSubscriber() {
		CubeButton button = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/subscribe.png");
		subscriber.getChildren().add(button.createContent(1.65,null,new Image(url.toExternalForm()), Color.TRANSPARENT));
		button.play();
		subscriber.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(subscriber_label);
			}
		});
		subscriber.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(subscriber_label);
			}
		});
		subscriber.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoSubscribor});
			}
		});
	}

	private void initSize() {
		CubeButton button = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/size.png");
		size.getChildren().add(button.createContent(1.65,null, new Image(url.toExternalForm()), Color.TRANSPARENT));
		button.play();
		size.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(size_label);
			}
		});
		size.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(size_label);
			}
		});
		size.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoSize});
			}
		});
	}

	private void initCollaborator() {
		CubeButton button = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/collaborate.png");
		collaborator.getChildren().add(button.createContent(1.65, null, new Image(url.toExternalForm()), Color.TRANSPARENT));
		button.play();
		collaborator.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(collaborator_label);
			}
		});
		collaborator.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(collaborator_label);
			}
		});
		collaborator.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoCollaborator});
			}
		});
	}

	private void initContributor() {
		CubeButton button = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/contribute.png");
		contributors.getChildren().add(button.createContent(1.65, null, new Image(url.toExternalForm()), Color.TRANSPARENT));
		button.play();
		contributors.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(contributors_label);
			}
		});
		contributors.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(contributors_label);
			}
		});
		contributors.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoContributor});
			}
		});
	}

	private void initCreateTime() {
		CubeButton button = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/time.png");
		createTime.getChildren().add(button.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		button.play();
		createTime.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(createTime_label);
			}
		});
		createTime.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(createTime_label);
			}
		});
		createTime.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoCreateTime});
			}
		});
	}
	
	private void initLanguage() {
		CubeButton button = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/java.png");
		language.getChildren().add(button.createContent(1.65, null, new Image(url.toExternalForm()), Color.TRANSPARENT));
		button.play();
		language.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.stop();
				labelAppear(language_label);
			}
		});
		language.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				button.play();
				labelDisappear(language_label);
			}
		});
		language.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoLanguage});
			}
		});
	}

}
