package code;

import javafx.animation.PauseTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/*
 * Цей клас представляє конкретну фігуру — коло, що реалізує інтерфейс {@link iShape}, 
 * і взаємодіє з іншими фігурами через медіатор {@link iMediator}.
 * Клас надає методи для малювання кола, відправки та отримання повідомлень,
 * а також підсвічування кола при отриманні відповідного повідомлення. 
 */
public class CircleShape implements iShape{
	/*
	 * Цілочислений радіус кола
	 */
	private int radius;
	
	/*
	 * Унікальний цілочислений ідентифікатор кола
	 */
	private int myNumber; 
	
	/*
	 * Медіатор, через який фігури обмінюються повідомленнями.
	 */
	private iMediator mediator;
	/*
	 * Посилання на коло JavaFX Circle, яке використовується для малювання кола.
	 */
	private Circle circle;
	
	/*
	 * Конструктор класу
	 * 
	 * @param radius Радіус кола.
	 * @param mediator Медіатор, через який спілкуються фігури.
	 * @param number Цілочислений унікальний номер фігури.
	 */
	public CircleShape(int radius, iMediator mediator, int number) {
		this.radius = radius;
		this.mediator = mediator;
		this.myNumber = number;
	}
	
	/*
	 * Цей метод надсилає повідомлення, змістом якого є число на 1 більше 
	 * за унікальний номер фігури, за допомогою медіатора.
	 * 
	 * @see iMediator#sendMessage(int, iShape, int)
	 */
	@Override
	public void sendMessage() {
		mediator.sendMessage(myNumber + 1, this, myNumber);
	}
	
	/*
	 * Цей метод надсилає повідомлення, змістом якого є число на 1 менше 
	 * за унікальний номер фігури, за допомогою медіатора.
	 * 
	 * @see iMediator#sendMessage(int, iShape, int) 
	 */
	@Override
	public void sendMessageReverse() {
		mediator.sendMessage(myNumber - 1, this, myNumber);
	}
	
	/*
	 * Цей метод отримує повідомлення, змістом якого є ціле число. 
	 * Отримавши повідомлення, фігура сповістить про це у консолі.
	 * Якщо отримане повідомлення збігається з унікальним номером цієї фігури,
	 * то вона повідомить про це у консолі та викликає метод для підсвічування себе.
	 * 
	 * @param number Цілочислене повідомлення.
	 */
	@Override
	public void receiveMessage(int number) {
		System.out.println("Graphic element <circle> with number " + myNumber + " received message: " + number);
		if (number == myNumber) {
			System.out.println("CIRCLE with number " + myNumber + " READY!");
			blink();
		}
	}
	
	/*
	 * Цей метод забезпечує підсвічування кола.
	 * Коло змінює колір на {@link Color#YELLOWGREEN}, а також отримує ефект тіні жовтого кольору.
     * Через 2 секунди тінь зникає, і коло повертається до початкового кольору.
	 */
	private void blink(){
    			
		Color oldColor = (Color) circle.getFill();
        DropShadow glow = new DropShadow();
        glow.setRadius(circle.getRadius() + 8);
        glow.setColor(Color.YELLOW); 
        circle.setFill(Color.YELLOWGREEN);
        circle.setEffect(glow);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
        	circle.setFill(oldColor);
            circle.setEffect(null);
        });
        pause.play();
	}
	
	/*
	 * Цей метод рисує коло у заданих координатах на панелі.
	 * Також метод виводить в консоль інформацію про малювання.
	 * 
	 * @param x Координата x для центра кола.
	 * @param y Координата y для центра кола.
	 * @param pane Панель, на якій буде розміщено коло.
	 */
	@Override
	public void draw(int x, int y, Pane pane) {
		System.out.println("Drawing the cirlce with number " + myNumber + " and radius " + radius + " at position"
				+ ": (" + x + "; " + y + ")");
		circle = new Circle(x, y, radius, Color.RED);
		pane.getChildren().add(circle);
		
	}
}
