public class Client implements Runnable{
	volatile Operator center;
	private static final int FILLING_TIME = 1500;
	private static final int NUMBER_OF_FILLING_CYCLES = 3;
	private static final int NUMBER_OF_CLIENTS_PER_CYCLE  = 60;
	
	public Client(Operator center) {
		this.center = center;
	}
	
	public void run() {
		int numberClient = 1;
		System.out.println("Звонки посыпались...");
			for (int i = 1; i <= NUMBER_OF_FILLING_CYCLES; i++) {
				for (int a = 1; a <= NUMBER_OF_CLIENTS_PER_CYCLE; a++) {
					String client = "Клиента под номером " + numberClient;
					center.addClient(client);
					numberClient++;
				}
				System.out.println("Добавлено 60 клиентов.");
				System.out.println("Клиентов осталось " + center.getQueueClient().size());
				
				try {
					Thread.sleep(FILLING_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		center.runOutOfClients();
	}
}
