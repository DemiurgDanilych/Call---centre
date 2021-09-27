public class Main {
	public static void main(String[] args) throws InterruptedException {
		Operator operator = new Operator();
		Client client = new Client(operator);
		
		Thread clients = new Thread(null, client, "Очередь звонков");
		
		Thread operator1 = new Thread(null, operator, "Оператор 1");
		Thread operator2 = new Thread(null, operator, "Оператор 2");
		Thread operator3 = new Thread(null, operator, "Оператор 3");
		Thread operator4 = new Thread(null, operator, "Оператор 4");
		
		clients.start();
		operator1.start();
		operator2.start();
		operator3.start();
		operator4.start();
		
		operator1.join();
		operator2.join();
		operator3.join();
		operator4.join();
	}
}
