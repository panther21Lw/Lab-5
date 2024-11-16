package code;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * Клас {@code Client} є клієнтським додатком JavaFX, що реалізує інтерфейс {@link Application}.
 * У цьому класі створюється медіатор, фігури (круги та квадрати), які взаємодіють через цей медіатор.
 * Фігури по черзі отримують повідомлення від інших фігур і підсвічуються, виконуючи анімацію.
 * Після виконання анімації, фігури відправляють повідомлення у зворотному напрямку.
 * <p>
 * 	Є клієнтським додатком JavaFX, що реалізує інтерфейс {@link Application}.
 * </p>
 */
public class Client extends Application{
		
	/*
     * Метод {@code start} є точкою входу в JavaFX додаток.
     * У цьому методі ініціалізуються фігури, медіатор, панель для малювання, сцена і запускається анімація.
     * 
     * @param primaryStage головне вікно JavaFX додатка.
     */
	@Override
	public void start(Stage primaryStage) {
		
		iMediator mediator = new ConcreteMediator();
		List<iShape> animation = new ArrayList<>();
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 500, 500);
		
		
				
		for(int i=1; i<5; i++) {
			iShape circle= new CircleShape(20, mediator, i);
			mediator.addShape(circle);
			animation.add(circle);
			circle.draw(i*50, 200, pane);
		}
		
		for (int i=5; i<8; i++) {
			iShape square = new SquareShape(40, mediator, i);
			mediator.addShape(square);
			animation.add(square);
			square.draw(i*50, 200-20, pane);
		}
		
		
		int delayTime = 2 * 1000;
		
		new Thread(() -> {
			threadSleepMs(2000); //Щоб побачити початковий стан нарисованих фігур
            for (int i = 0; i < animation.size()-1; i++) {
                animation.get(i).sendMessage();
                threadSleepMs(delayTime);
            }
            
            System.out.println("\nReversed direction");
            
            for(int i = animation.size()-1; i>0; i--) {
            	animation.get(i).sendMessageReverse();
            	threadSleepMs(delayTime);
            }
        }).start();
				
		primaryStage.setTitle("Лабораторна робота 5");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	/*
     * Точка входу в програму. Запускає додаток JavaFX.
     * 
     * @param args Аргументи командного рядка.
     */
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
     * Метод для затримки виконання потоку на задану кількість мілісекунд.
     * 
     * @param miliseconds Час затримки в мілісекундах.
     */
	public void threadSleepMs(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
