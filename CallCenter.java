import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
	private final Queue<String> queue = new ConcurrentLinkedQueue<>();
	private boolean producerLive = true;
	private static final int FILLING_TIME = 1500;
	private static final int PROCESSING_TIME = 3000;
	private static final int NUMBER_OF_FILLING_CYCLES = 3;
	private static final int NUMBER_OF_CLIENTS_PER_CYCLE  = 60;
	
	public void clientCall() {
		int numberClient = 1;
		System.out.println("Звонки посыпались...");
		try {
			for (int i = 1; i <= NUMBER_OF_FILLING_CYCLES; i++) {
				for (int a = 1; a <= NUMBER_OF_CLIENTS_PER_CYCLE; a++) {
					String client = "Клиент " + numberClient;
					queue.add(client);
					numberClient++;
				}
				System.out.println("Добавлено 60 клиентов.");
				System.out.println("Клиентов осталось " + queue.size());
				Thread.sleep(FILLING_TIME);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		producerLive = false;
	}
	
	public void operatorProcessed() {
		System.out.println("Оператор начал обработку звонков.");
		String client;
		while (canBeProcessed()) {
			if ((client = queue.poll()) != null) {
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
		return producerLive || queue.size() > 0;
	}
}
