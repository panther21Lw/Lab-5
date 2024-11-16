package code;

import javafx.animation.PauseTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


/*
 * Цей клас представляє конкретну фігуру — квадрат, що реалізує інтерфейс {@link iShape}, 
 * і взаємодіє з іншими фігурами через медіатор {@link iMediator}.
 * Клас надає методи для малювання кола, відправки та отримання повідомлень,
 * а також підсвічування квадрата при отриманні відповідного повідомлення. 
 */ 
public class SquareShape implements iShape{
	
	/*
	 * Цілочислена довжина сторони квадрата
	 */
	private int side;
	
	/*
	 * Унікальний цілочислений ідентифікатор квадрата
	 */
	private int myNumber;
	
	/*
	 * Медіатор, через який фігури обмінюються повідомленнями.
	 */
	private iMediator mediator;
	
	/*
	 * Посилання на прямокутник JavaFX Rectangle, який використовується для малювання квадрата.
	 */
	private Rectangle square;
	
	/*
	 * Конструктор класу
	 * 
	 * @param side Довжина сторони квадрата.
	 * @param mediator Медіатор, через який спілкуються фігури.
	 * @param number Цілочислений унікальний номер квадрата.
	 */
	public SquareShape(int side, iMediator mediator, int number) {
		this.side = side;
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
		System.out.println("Graphic element <square> with number " + myNumber + " received message: " + number);
		if (number == myNumber) {
			System.out.println("SQUARE with number " + myNumber + " READY!");
			blink();
		}
	}
	
	/*
	 * Цей метод забезпечує підсвічування квадрата.
	 * Квадрат змінює колір на {@link Color#YELLOWGREEN}, а також отримує ефект тіні жовтого кольору.
     * Через 2 секунди тінь зникає, і квадрат повертається до початкового кольору.
	 */
	private void blink(){
    			
		Color oldColor = (Color) square.getFill();
        DropShadow glow = new DropShadow();
        glow.setRadius(side + 5);
        glow.setColor(Color.YELLOW); 
        square.setFill(Color.YELLOWGREEN);
        square.setEffect(glow);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
        	square.setFill(oldColor);
            square.setEffect(null);
        });
        pause.play();
	}
	
	/*
	 * Цей метод рисує квадрат у заданих координатах на панелі.
	 * Також метод виводить в консоль інформацію про малювання.
	 * 
	 * @param x Координата x для лівої верхньої вершини квадрата.
	 * @param y Координата y для лівої верхньої вершини квадрата.
	 * @param pane Панель, на якій буде розміщено квадрат.
	 */
	@Override
	public void draw(int x, int y, Pane pane) {
		System.out.println("Drawing the square with number " + myNumber + " and side " + side + " at position"
				+ ": (" + x + "; " + y + ")");
		square = new Rectangle(x, y, side, side);
		square.setFill(Color.RED);
		pane.getChildren().add(square);
		
	}
	

}
