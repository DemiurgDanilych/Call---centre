import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Operator  implements Runnable {
	private final Queue<String> queueClients = new ConcurrentLinkedQueue<>();
	private static final int PROCESSING_TIME = 500;
	volatile boolean clientIsAlive = true;
	
	public Queue<String> getQueueClient(){
		return queueClients;
	}
	
	public void addClient(String client){
		queueClients.add(client);
	}
	
	public void runOutOfClients(){
		clientIsAlive = false;
	}
	
	
	public void run() {
		System.out.println("Оператор начал обработку звонков.");
		String client;
		while (canBeProcessed()) {
			if ((client = queueClients.poll()) != null) {
				System.out.println("Оператор обработал " + client);
			}
			try {
				Thread.sleep(PROCESSING_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Оператор закончил обработку звонков.");
	}
	
	private boolean canBeProcessed(){
		return clientIsAlive||queueClients.size() > 0;
	}
}