package code;

import java.util.ArrayList;
import java.util.List;

/*
 * Цей клас реалізує інтерфейс {@link iMediator} і виступає як конкретний медіатор для фігур. 
 * Він дозволяє фігурам взаємодіяти через медіатор, а не безпосередньо між собою.
 * Медіатор має методи для додавання і видалення фігур, а також для надсилання повідомлень 
 * всім фігурам, що до нього прив'язані, за винятком фігури, яка є відправником повідомлення.
 * 
 * @see iMediator
 */
public class ConcreteMediator implements iMediator{
	/*
	 * Цей список містить фігури, які реалізують інтерфейс {@link iShape}, та прив'язані 
	 * до конкретного медіатора. Ці фігури отримують повідомлення від медіатора.
	 */
	private List<iShape> shapes = new ArrayList<>();
	
	/*
	 * Цей метод прив'язує фігуру до конкретного медіатора, що дозволяє їй отримувати повідомлення.
	 * 
	 * @param shape Фігура, що реалізує інтерфейс {@link iShape}.
	 */
	@Override
	public void addShape(iShape shape) {
		shapes.add(shape);
	}
	
	/*
	 * Цей метод відв'язує фігуру від конкретного медіатора --- фігура не буде отримувати повідомлення.
	 * 
	 * @param shape Фігура, що реалізує інтерфейс {@link iShape}.
	 */
	@Override
	public void removeShape(iShape shape) {
		shapes.remove(shape);
	}
	
	/*
	 * Цей метод надсилає повідомлення усім фігурам, які прив'язані до конкретного медіатора,
	 * за винятком фігури, яка є відправником повідомлення.
	 * 
	 * @param number Ціле число; є змістом повідомлення.
	 * @param senderShape Фігура, що реалізує інтерфейс {@link iShape}, забезпечує ненадсилання 
	 * повідомлення фігурі-відправнику.
	 * @param id Унікальний ідентифікатор кожної фігури.
	 */
	@Override
	public void sendMessage(int number, iShape senderShape, int id) {
		System.out.println("\nSENDING MESSAGE: by number " + id + " with context: " + number);
		for(iShape shape : shapes) {
			if (shape != senderShape) {
				shape.receiveMessage(number);
			}
		}
	}
	
}
