/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit.views;

import com.jfoenix.controls.JFXButton;
import gestionproduit.entity.User;
import gestionproduit.services.UserServices;
import gestionproduit.utils.css.MailVerification;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author wiemj
 */
public class RegisterUserController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView close;
    @FXML
    private JFXButton seconnecter;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField addresse;
    @FXML
    private JFXTextField telep;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField reppassword;
    @FXML
    private ImageView imajout1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    @SuppressWarnings("empty-statement")
    public void register(ActionEvent event) throws MessagingException, IOException{
        boolean valid = true;
        if (nom.getText().equals("")) {
            nom.setText("Field is empty !");
            nom.setVisible(true);
            valid = false;
        }

        if (prenom.getText().equals("")) {
            prenom.setText("Field is empty !");
            prenom.setVisible(true);
            valid = false;
        }

        
        if (username.getText().equals("")) {
            username.setText("Field is empty !");
            username.setVisible(true);
            valid = false;
        }

        if (!MailVerification.validate(email.getText())) {
            email.setText("E-mail is not valid !");
            email.setVisible(true);
            valid = false;
        }

        if (telep.getText().equals("")) {
            telep.setText("Field is empty !");
            telep.setVisible(true);
            valid = false;
        }
        if (addresse.getText().equals("")) {
            addresse.setText("Field is empty !");
            addresse.setVisible(true);
            valid = false;
        }

        if (password.getText().equals("")) {
            password.setText("Field is empty !");
            password.setVisible(true);
            valid = false;
        }
        if (reppassword.getText().equals("")) {
            reppassword.setText("Field is empty !");
            reppassword.setVisible(true);
            valid = false;
        }

        if (!password.getText().equals(reppassword.getText())) {
            reppassword.setText("Password doesn't match !");
            reppassword.setVisible(true);
            valid = false;
        } else {
            reppassword.setText("Password match !");
            //reppassword.setTextFill(Color.web("GREEN"));;
            reppassword.setVisible(true);
        }
if(valid){
        UserServices us=new UserServices();
        User u=new User(username.getText(), email.getText(), 0, password.getText()+"{"+username.getText()+"}", "0", nom.getText(), prenom.getText(), addresse.getText(), Integer.parseInt(telep.getText()));
        us.AjouterUser(u);
        mailverif();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginUserFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
else{
    
}}
    @FXML
     public void back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void mailverif() throws AddressException, MessagingException {
         String host ="smtp.gmail.com";
                String user ="Jebwiem98@gmail.com";
                String pass ="aqw123147";
                String from ="Jebwiem98@gmail.com";
                String to =email.getText();
                String subject ="verification email";
                String messageText ="http://localhost/update.php?user="+username.getText();
                boolean sessionDebug =false ;
                
                Properties props = System.getProperties();
                
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");
                
                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession =Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setText(messageText);
                System.out.println(user);
                System.out.println(host);
                System.out.println(pass);
                Transport transport =mailSession.getTransport("smtp");
                transport.connect(host, user , pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                System.out.println("message envoyé");
    }
    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    
}
