/**
** File Name: SmartphonePackages.java
* Written by: Andrew Nguyen
* Description: User will be able to interact with my cell solutions application where they'll pick a data plan, a desired
*              phone model, optional insurance or WiFi hotspot, and add any additional costs. With the feature to calculate      
*              the cost, reset all selected/typed, and to exit the program with buttons. The output for cost and what was
*              selected will be prompted below in the program for the user to see.
*
*
* Challenges:  As this is my first time making any like this, I had trouble with setting up where things should be and size
*              (I used gridpane/vbox/hbox) but as I keep using this I'm sure I'll get used to it, another challenge was getting
//             variable values for each user input or select item.
// Time Spent:  About 3 days
//
//                   Revision History
* Date:                   By:               Action:
* -------------------------------------------------------
 04/02/2023                  
*/

//IMPORTING OF JAVAFX PACKAGES
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;


public class SmartphonePackages extends Application implements EventHandler<ActionEvent> {
    
    //VARIABLES ASSIGNED AND USED TO HELP GET USER INPUT 
    Button buttonCalc, buttonReset, buttonExit;
    public double checkBox1Value = 0;
    public double checkBox2Value = 0;
    public double textFieldValue = 0;
    public double phoneSalesTax;
    public double comboBoxValue;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cell Solutions");
        
        //SETTING UP MY TEXTAREA WHERE OUTPUT WILL DISPLAYED
        TextArea textArea = new TextArea();
        textArea.setPrefSize(620, 200);
        
        //NAMING RADIOBUTTONS AND GIVING THEM A VALUE DEPENDING ON WHAT USER CHOSE
        RadioButton radioButton8G = new RadioButton("8 Gigabytes per month ($45 per month)");
        radioButton8G.setUserData(45);
        RadioButton radioButton16G = new RadioButton("16 Gigabytes per month ($65 per month)");
        radioButton16G.setUserData(65);
        RadioButton radioButton20G = new RadioButton("20 Gigabytes per month ($99 per month)");
        radioButton20G.setUserData(99);
        
        //DEFAULTING 8 GIGABYTES
        radioButton8G.setSelected(true);
        
        //SETTING UP THE RADIOBUTTONS IN TOGGLEGROUP SO IT COULD BE TOGGLED
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(radioButton8G, radioButton16G, radioButton20G);
        
        
        //MAKING TEXTFIELD SO THAT USER CAN INPUT OTHER COST VALUE
        TextField textField = new TextField();
        textField.setText("Enter a numerical value");
        textField.setMinWidth(100);
        textField.setMinHeight(10);
        
        
        //MAKING EXIT BUTTON AND POSITIONING IT
        buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setOnAction(this);  
        buttonExit.setMinWidth(100);
        buttonExit.setMinHeight(30);
        GridPane.setConstraints(buttonExit, 6, 2);
        
        //MAKING CALCULATE COST BUTTON AND POSITIONING IT
        buttonCalc = new Button();
        buttonCalc.setText("Calculate Cost");
        buttonCalc.setOnAction(this);
        buttonCalc.setMinWidth(100);
        buttonCalc.setMinHeight(30);
        GridPane.setConstraints(buttonCalc, 6, 0);
        
        //MAKING RESET BUTTON AND POSITIONING IT
        buttonReset = new Button("Reset");
        buttonReset.setMinWidth(100);
        buttonReset.setMinHeight(30);
        GridPane.setConstraints(buttonReset, 6, 1);
        
        //SETTING UP COMBOBOX WITH 3 ITEMS FOR USER TO PICK
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Model 100: $299.95", "Model 110: $399.95", "Model 200: $499.95");
        comboBox.setValue("Select a model");      
        GridPane.setConstraints(comboBox, 0, 2);
        
        //SETTING UP CHECKBOXES FOR THE OPTIONAL ITEMS WITH A DEFAULT OF UNCHECKED
        CheckBox checkBox1 = new CheckBox("Phone Replacement Insurance");
        checkBox1.setSelected(false);
        GridPane.setConstraints(checkBox1, 0, 4);
        CheckBox checkBox2 = new CheckBox("WiFi Hotspot Capability");
        checkBox2.setSelected(false);
        GridPane.setConstraints(checkBox2, 0, 5);
        
        //LABELING FOR EASE OF USE FOR THE USER AND TO SHOW WHAT WIDGET DOES WHAT AND TO GUIDE
        Label labelSelectAPhone = new Label("Select a Phone:");
        GridPane.setConstraints(labelSelectAPhone, 0, 1);
        Label labelOptionsCost = new Label("Options Cost:");
        GridPane.setConstraints(labelOptionsCost, 0, 3);
        Label labelOtherCost = new Label("Other Cost:  ");
        GridPane.setConstraints(labelOtherCost, 0, 6);
        
        //SETTING UP WHAT THE RESET BUTTON WILL DO IF USER PRESSES IT
        buttonReset.setOnAction(event -> {
            textField.setText("Enter a numerical value"); //ERASES USER TEXT INPUT WITH DEFAULT STRING
            radioButton8G.setSelected(true); //DEFAULTING RADIOBUTTONS BACK TO 8G
            comboBox.setValue("Select a model"); //RESETS COMBOBOX CHOICE
            //UNCHECKS CHECKBOXES IF THEY WERE SELECTED
            checkBox1.setSelected(false);
            checkBox2.setSelected(false);
            textArea.clear(); //CLEARING TEXTAREA INFORMATION
            textField.clear(); //CLEARING TEXTFIELD INPUT
        });   
        
        //ADDING RADIOBUTTONS TO VBOX1
        VBox vbox1 = new VBox(radioButton8G, radioButton16G, radioButton20G);   
        
        //A DROP DOWN MENU TO SELECT THE RADIOBUTTON OPTIONS
        TitledPane titledPane = new TitledPane();
        titledPane.setText("Select a Data Plan");
        titledPane.setMinWidth(400);
        titledPane.setMinHeight(0);
        titledPane.setContent(vbox1);
        GridPane.setConstraints(titledPane, 0, 0);
        
        //ADDING ELEMENTS TO THE GRIDPANE LAYOUT
        GridPane grid = new GridPane();
        grid.getChildren().addAll(buttonExit, buttonReset, buttonCalc, titledPane, comboBox,
                labelSelectAPhone, labelOptionsCost, checkBox1, checkBox2);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        //GIVING VALUE TO EACH CHECKBOX IF SELECTED SO THAT I COULD CALCULATE COST LATER
        EventHandler eventHandler = event -> {
            if (checkBox1.isSelected()) {
                checkBox1Value = 5;
            } else {
                checkBox1Value = 0;
            }

            if (checkBox2.isSelected()) {
                checkBox2Value = 10;
            } else {
                checkBox2Value = 0;
            }
        };
        //SETTING HANDLER TO REACT TO IF BOXES ARE CHECKED
        checkBox1.setOnAction(eventHandler);
        checkBox2.setOnAction(eventHandler);
        
        //NOT ALLOWING CHARACTERS BUT ONLY NUMBERS AND DECIMAL IN TEXTFIELD
        buttonCalc.setOnAction(event -> {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                // If the new value contains non-digit characters, replace it with the old value
                textField.setText(oldValue);
            }
            });
            
            //GIVING VALUE TO COMBOBOX DEPENDING ON USER SELECTION
            if ("Model 100: $299.95".equals(comboBox.getValue())) {
                comboBoxValue = 299.95;
            } else if ("Model 110: $399.95".equals(comboBox.getValue())) {
                comboBoxValue = 399.95;
            } else if ("Model 200: $499.95".equals(comboBox.getValue())) {
                comboBoxValue = 499.95;
            } else {
                comboBoxValue = 0;
            }
            
            //HIGHLIGHTING TEXTFIELD IF USER DOESN'T TYPE OR LEAVES IT BLANK BUT ALSO GIVING IT A VALUE OF ZERO TO COMPENSATE FOR PRICE
            if("Enter a numerical value".equals(textField.getText()) || "".equals(textField.getText())){
                textFieldValue = 0;
                textField.setText("Enter a numerical value");
                textField.requestFocus();
                textField.selectAll();
            } else{ //TURNING TEXTFIELD INPUT INTO A DOUBLE FOR LATER CALCULATIONS
                String textFieldText = textField.getText();
                textFieldValue = Double.parseDouble(textFieldText); 
            }
            
            //CALCULATION FOR 6% SALES TAX
            phoneSalesTax = comboBoxValue / 100 * 6;
            //ROUNDING THE VALUE CALCULATED
            int roundedValue = (int) Math.round(phoneSalesTax);
            String formatted = String.format("%d", roundedValue);
            String formattedOther = String.format("%.2f", textFieldValue); //FORMATTING OTHER COST TO DISPLAY CORRECTLY
            
            //VARIABLE SELECTEDRADIO HOLDS DOUBLE VALUE OF WHATEVER RADIO BUTTON WAS SELECTED
            double selectedRadio = (int) group.getSelectedToggle().getUserData();
            //ADDING THE OPTIONS COST TOGETHER AND USING ANOTHER VARIABLE TO HOLD IT FOR TOTAL COST LATER
            double optionsCosts = checkBox1Value + checkBox2Value;
            //TURNING THE FORMATTED INTO DOUBLE VALUE
            double forDouble = Double.parseDouble(formatted);
            double forDoubleOther = Double.parseDouble(formattedOther);
            //FINAL CALCULATION FOR THE TOTAL COST
            double totalCost = selectedRadio + comboBoxValue + optionsCosts + forDouble + forDoubleOther;
            
            //DISPLAYING EACH SELECTED AND INPUT USER DID WHILE ALSO DISPLAYING THE TOTAL COST
            textArea.setText("Data Plan Charges: $" + selectedRadio + "0" + "\n" + "Phone Charges: $" +comboBoxValue + " and Phone Tax Charges: $" 
             + formatted + ".00"+ "\n" + "Options Costs: $" + optionsCosts + "0" + "\n" + "Others: $" + formattedOther
            + "\n" + "\n" + "The total cost: $" + totalCost);
            //HIGHLIGHTING TEXTFIELD SO THAT USER CAN CHANGE PRICE IF THEY WISH
            textField.requestFocus();
            textField.selectAll();
            //TO MAKE SURE USER SELECTS A MODEL AND LETTING THEM KNOW TO DO SO IN TEXTAREA
            if ("Select a model".equals(comboBox.getValue()) || "".equals(comboBox.getValue())) {
                textArea.clear();
                textArea.setText("Please select a model");
            }          
        }); 
        
        //ADDING TEXTAREA TO VBOX2
        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(textArea);
        
        //MAKING HBOX TO HAVE LABELOTHERCOST AND TEXTFIELD BE HORIZONTAL FROM EACH OTHER
        HBox hbox = new HBox();
        hbox.getChildren().addAll(labelOtherCost, textField);
        
        //ADDING HBOX AND VBOX2 TO GRID
        grid.add(hbox, 0, 6);
        grid.add(vbox2, 0, 8);
        
        //SETTING UP SCENE WITH PARENT GRID AND THE DIMENSIONS
        Scene scene = new Scene(grid, 620, 550);
  
        //SETTING SCENE AND SHOWING TO SHOW THE PROGRAM
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //HANDLING IF USER WANTS TO EXIT WITH EXIT BUTTON
    public void handle(ActionEvent event) {
        if(event.getSource()==buttonExit){
            Platform.exit();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}

