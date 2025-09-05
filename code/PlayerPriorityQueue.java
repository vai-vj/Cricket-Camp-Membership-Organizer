
package cricCamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class PlayerPriorityQueue {
	private PriorityQueue<Player> queue;

	public PlayerPriorityQueue() {
		this.queue = new PriorityQueue<>(Collections.reverseOrder());
	}
	
	
	public void enqueue(Player player) {
		queue.offer(player);
	}
	
	public Player dequeue() {
		return queue.poll();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	//
	public List<Player> getTopPlayers(int count){
		List<Player> topPlayers = new ArrayList<>();
		for (int i=0; i<count; i++) {
			Player player = dequeue();
			if (player != null) {
				topPlayers.add(player);
			}
			else {
				break;
			}
		}
		return topPlayers;
	}
}

