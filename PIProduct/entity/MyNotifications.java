
package gestionproduit.entity;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author esprit
 */
public class MyNotifications {
    
    public static void infoNotification(String title, String message) {
        Image img = new Image("file:///C:Users/bouyo/Documents/NetBeansProjects/ProjetPi/src/Ressource/images/customer-service.png") ;
        ImageView image = new ImageView(img);
        image.setFitHeight(64);
        image.setFitWidth(64);
        Notifications notificationBuilder = Notifications.create()
               // .darkStyle()
                .hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT)
                .title(title)
                .text(message)
                .graphic(image)
                ;
        notificationBuilder.show();
    }
    
    public static void ErrorNotification(String title, String message) {
        Image img = new Image("file:///C:Users/bouyo/Documents/NetBeansProjects/ProjetPi/src/Ressource/images/customer-service.png/cancel.png") ;
        ImageView image = new ImageView(img);
        image.setFitHeight(64);
        image.setFitWidth(64);
        Notifications notificationBuilder = Notifications.create()
               // .darkStyle()
                .hideAfter(Duration.seconds(30))
                .position(Pos.BOTTOM_RIGHT)
                .title(title)
                .text(message)
                .graphic(image)
                ;
        notificationBuilder.showError();
    }
    
    public static void WarningNotification(String title, String message) {
        Image img = new Image("file:///C:Users/bouyo/Documents/NetBeansProjects/ProjetPi/src/Ressource/images/customer-service.png/warning.png") ;
        ImageView image = new ImageView(img);
        image.setFitHeight(64);
        image.setFitWidth(64);
        Notifications notificationBuilder = Notifications.create()
               // .darkStyle()
                .hideAfter(Duration.seconds(30))
                .position(Pos.BOTTOM_RIGHT)
                .title(title)
                .text(message)
                .graphic(image)
                ;
        notificationBuilder.showWarning();
    }    
    
}
