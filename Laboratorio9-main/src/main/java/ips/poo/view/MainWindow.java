package  ips.poo.view;

import ips.poo.model.Equipment;
import ips.poo.model.Request;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;

public class MainWindow extends BorderPane
{

    public MainWindow()
    {

        //TOPO
        HBox barraTopo = new HBox();
        setTop(barraTopo);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Button ajuda = new Button("Ajuda");

        MenuItem button1 = new MenuItem("New File");

        MenuItem button2 = new MenuItem("Sair");

        menuFile.getItems().add(button1);
        menuFile.getItems().add(button2);
        menuBar.getMenus().addAll(menuFile);

        barraTopo.getChildren().addAll(menuBar, ajuda);

        //CENTRO

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10));

        //PAINELDOS RADIO BUTTONS
        HBox radioButtonsBox = new HBox(20);
        radioButtonsBox.setSpacing(5);
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Estudante");
        RadioButton radioButton2 = new RadioButton("Docente");
        RadioButton radioButton3 = new RadioButton("Funcionario");
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);
        TitledPane painel1 =
                new TitledPane("Tipo de utilizador", radioButtonsBox);
        painel1.setCollapsible(false);

        radioButtonsBox.getChildren().addAll(radioButton1,  radioButton2, radioButton3);


        //PAINEL DOS EQUIPAMENTOS
        HBox equipamentos = new HBox(20);
        equipamentos.setSpacing(5);
        Label label1 = new Label("Selecione o Equipamento ou serviço:");
        ComboBox<Equipment> comboBox1 = new ComboBox<>();
        List<Equipment> equipamentosList = List.of(
                new Equipment("Computador","Hardware"),
                new Equipment("Impressora", "Hardware"),
                new Equipment("Rede Wi-fi", "Rede"),
                new Equipment("Moodle", "Software"),
                new Equipment("Email Institutional", "Software"),
                new Equipment("Other Equipment", "Other")
        );
        comboBox1.getItems().addAll(equipamentosList);
        TextField textFieldSecreto = new TextField();
        textFieldSecreto.setVisible(false);

        comboBox1.setOnAction(e ->
        {
            Equipment selected = comboBox1.getValue();

            if (selected != null && selected.getName().equals("Other Equipment")) {
                textFieldSecreto.setVisible(true);
            }
            else
            {
                textFieldSecreto.setVisible(false);
            }
        });
        textFieldSecreto.setOnAction(event ->
        {
            Equipment newEquipment = new Equipment(textFieldSecreto.getText(), "Other");
            equipamentosList.add(newEquipment);
        });
        equipamentos.getChildren().addAll(label1, comboBox1, textFieldSecreto);
        TitledPane painel2 = new TitledPane("Equipamento /Serviço", equipamentos);
        painel2.setCollapsible(false);

        //Dados de utlizador

        HBox dados = new HBox();
        dados.setSpacing(5);
        dados.setPadding(new Insets(10));
        Label label2 = new Label("Nome completo");
        TextField textField1 = new TextField();
        dados.getChildren().addAll(label2, textField1);
        TitledPane painel3 = new TitledPane("Dados do utlizador", dados);
        painel3.setCollapsible(false);

        //Descrição

        HBox descricao = new HBox();
        descricao.setSpacing(5);
        descricao.setPadding(new Insets(10));
        TextArea textArea1 = new TextArea();
        descricao.getChildren().addAll(textArea1);
        TitledPane painel4 = new TitledPane("Descrição do problema", descricao);
        painel4.setCollapsible(false);

        //JUNÇÃO DE TUDO
        vBox.getChildren().addAll(radioButtonsBox);
        vBox.getChildren().addAll(painel1, painel2, painel3, painel4);

        setCenter(vBox);

        //BOTTOM

        HBox fundo = new HBox();
        fundo.setSpacing(5);
        fundo.setAlignment(Pos.CENTER_RIGHT);
        fundo.setPadding(new Insets(10));
        Button registar = new Button("Registar");
        registar.setOnAction(e ->
        {
            RadioButton selecionado = (RadioButton) toggleGroup.getSelectedToggle();
            String texto = selecionado.getText();
            String textoCombo;
            if(textFieldSecreto.isVisible())
            {
                textoCombo = textFieldSecreto.getText();

            }
            else
            {
                textoCombo = comboBox1.getValue().toString();

            }
            CriarRequest(texto, textField1.getText(), new Equipment(textoCombo, "Other"), textArea1.getText() );
        }
        );

        Button limpar = new Button("Limpar");
        limpar.setOnAction(e->{
        limpar(toggleGroup, textField1, comboBox1, textArea1, textFieldSecreto);
        });
        Button cancelar = new Button("Cancelar");
        fundo.getChildren().addAll(registar, limpar, cancelar);

        setBottom(fundo);

        button1.setOnAction(e->
        {
            limpar(toggleGroup, textField1, comboBox1, textArea1, textFieldSecreto);
        });

        button2.setOnAction(e->{
            Platform.exit();
        });

        ajuda.setOnAction(e->{
            Stage helpStage = new Stage();
            VBox root = new VBox();
            root.setPadding(new Insets(10));
            root.setSpacing(5);
            Label labelAjuda = new Label("Precisa de ajuda ?");
            root.getChildren().addAll(labelAjuda);
            Scene scene = new Scene(root, 300, 200);

            helpStage.setTitle("Ajuda");
            helpStage.setScene(scene);
            helpStage.show();
        });

    }

    public void CriarRequest(String userType, String name, Equipment equipment, String description)
    {
        System.out.printf("Criando request %s %s %s %s", userType, name, equipment, description);
        Request request = new Request(userType, name, equipment, description);
    }

    public void limpar(ToggleGroup toggleGroup, TextField textField1, ComboBox<Equipment> comboBox1, TextArea textArea1, TextField textFieldSecreto)
    {
        toggleGroup.selectToggle(null);
        textField1.clear();
        comboBox1.setValue(null);
        textArea1.clear();
        textFieldSecreto.clear();
        textFieldSecreto.setVisible(false);
    }
}
