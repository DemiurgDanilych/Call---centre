public class Main {
	public static void main(String[] args) throws InterruptedException {
		CallCenter callCenter = new CallCenter();
		
		Runnable calls = callCenter::clientCall;
		Runnable operatorsProcessed = callCenter::operatorProcessed;
		
		Thread producer = new Thread(null, calls, "Очередь звонков");
		
		Thread operator1 = new Thread(null, operatorsProcessed, "Оператор 1");
		Thread operator2 = new Thread(null, operatorsProcessed, "Оператор 2");
		Thread operator3 = new Thread(null, operatorsProcessed, "Оператор 3");
		Thread operator4 = new Thread(null, operatorsProcessed, "Оператор 4");
		
		producer.start();
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
