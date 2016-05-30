package application;

import java.lang.System;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ABEL_STUI extends Application{
	   
    VBox vBox = new VBox();
    
    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("Abel Security Toolkit");
        Scene scene = new Scene(vBox, 1024, 768, Color.BLUE);
        MenuBar menubar = new MenuBar();
        
        Menu menuFile = new Menu("File");
        
        Menu fileNew = new Menu("New");
        fileNew.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+N"));
        
        Menu passwordManager = new Menu("Password Manager");
        fileNew.getItems().add(passwordManager);
        MenuItem passwordManagerNewGroup = new MenuItem("New Group");
        MenuItem passwordManagerNewSecurityLayout = 
                new MenuItem("New Security Layout");
        MenuItem passwordManagerNewPassword = new MenuItem("New Password");
        passwordManager.getItems().addAll(passwordManagerNewGroup
                , passwordManagerNewSecurityLayout
                , passwordManagerNewPassword);
        
        Menu secureDelete = new Menu("Secure Deletion");
        fileNew.getItems().add(secureDelete);
        MenuItem deleteFile = new MenuItem("File Deletion");
        MenuItem deleteFreeSpace = new MenuItem("Free Space Deletion");
        MenuItem deletMemoryFreeSpace = 
                new MenuItem("Memory Free Space Deletion");
        secureDelete.getItems().addAll(deleteFile, deleteFreeSpace 
                , deletMemoryFreeSpace);

        menuFile.getItems().add(fileNew);    
        
        MenuItem fileOpen = new MenuItem("Open");
        menuFile.getItems().addAll(fileOpen);
        
        MenuItem fileSave = new MenuItem("Save");
        menuFile.getItems().addAll(fileSave);
        
        MenuItem fileSaveAs = new MenuItem("Save As");
        menuFile.getItems().addAll(fileSaveAs);
        
        MenuItem fileExit = new MenuItem("Exit");
        menuFile.getItems().addAll(fileExit);
        fileExit.setOnAction((ActionEvent t) -> {
                System.exit(0);
        });

        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
        Menu menuNavigate = new Menu("Navigate");
        
        Menu menuHelp = new Menu("Help");
        MenuItem helpAbout = new MenuItem("About");
        helpAbout.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Stage aboutStage = new Stage();
        		aboutStage.setTitle("About");
        		
        		FlowPane aboutPane = new FlowPane();
        		Scene aboutScene = new Scene(aboutPane, 800, 600);
        		
        		About about = new About(); 
        		Text aboutText = new Text(about.getAboutText());
        		
        		aboutPane.getChildren().addAll(aboutText);
        		aboutStage.setScene(aboutScene);
        		aboutStage.show();
        	}
        });
        
        MenuItem helpSystemInformations = new MenuItem("System Informations");
        helpSystemInformations.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Stage systemInformationsStage = new Stage();
        		systemInformationsStage.setTitle("System Informations");
        		
        		FlowPane systemInformationsPane = new FlowPane();
        		systemInformationsPane.setStyle("-fx-background-color: white;");
        		Scene systemInformationsScene = new Scene(systemInformationsPane, 800, 600);

        		SystemInformations systemInformations = new SystemInformations();
        		Text osInfo = new Text(systemInformations.getOsInfoList());
        		
        		systemInformationsPane.getChildren().addAll(osInfo);
        		systemInformationsStage.setScene(systemInformationsScene);
        		systemInformationsStage.show();
        	}
        });
        
        menuHelp.getItems().addAll(helpAbout, helpSystemInformations);
        
        menubar.getMenus().addAll(menuFile, menuEdit, menuView, menuNavigate
                , menuHelp);

        ((VBox) scene.getRoot()).getChildren().addAll(menubar /*,osInfo*/);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
