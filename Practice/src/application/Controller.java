package application;

//import java.awt.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {

	@FXML
	TextField t1, t2, t3, t4, t5;
	@FXML
	RadioButton r1, r2, r3;
	Stage stage;
	int discount=0;
	
	@FXML
	private AnchorPane scenePane;
	
	public void compute(ActionEvent e) {
		int amount= Integer.parseInt(t1.getText());
		t2.setText(""+discount+" %");
		int disAmt= discount* amount/100;
		t3.setText("Rs. "+disAmt);
		t4.setText("Rs. "+(amount-disAmt));
	}
	
	public void end(ActionEvent e) {
		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Do you really want to exit?");
		
		if(alert.showAndWait().get()== ButtonType.OK) {
			stage= (Stage)scenePane.getScene().getWindow();
			System.out.print("You have successfully logged out");
			stage.close();
		}
		
	}
	
	public void r1Selected(ActionEvent e) {
		if(r1.isSelected()==true) {
		r2.setDisable(true);
		r3.setDisable(true);
		}
		
		else {
			r2.setDisable(false);
			r3.setDisable(false);
		}
		discount=3;
	}
	
	public void r2Selected(ActionEvent e) {
		if(r2.isSelected()==true) {
			r1.setDisable(true);
			r3.setDisable(true);
		}
		
		else {
			r1.setDisable(false);
			r3.setDisable(false);
		}
		discount=5;
	}
	
	public void r3Selected(ActionEvent e) {
		if(r3.isSelected()==true) {
			r1.setDisable(true);
			r2.setDisable(true);
		}
		
		else {
			r1.setDisable(false);
			r2.setDisable(false);
		}
		discount=8;
	}
	
	
}
