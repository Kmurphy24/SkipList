import java.util.Arrays;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Random;

public class SkipList <E extends Comparable<E>>{
	private int maxLevel, size;
	private int maxCap;
	private SLNode head,tail;
	static final double LOG2 = Math.log(2.0);
	
	public static class SLNode<E>{
		private SLNode<E>[] links;
		private E data;
		
		SLNode(int m, E data){
			links =(SLNode<E>[]) new SLNode[m];
			this.data = data;
		}		
	}
	
	public SkipList(int maxLevel){
		this.maxLevel = maxLevel;
		size = 0;
		maxCap = 2^maxLevel - 1;
	}
	
	public E find(E target){
		SLNode<E>[] pred = search(target);
		if(pred[0].links[0] != null && pred[0].links[0].data.compareTo(target)==0){
			return pred[0].links[0].data;
		}else return null;
	}
	
	@SuppressWarnings("unchecked")
	private SLNode<E>[] search(E target){
		SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
		SLNode<E> current = head;
		for(int i = current.links.length - 1; i >=0; i--){
			while(current.links[i]!=null && 
					current.links[i].data.compareTo(target) < 0)
				current = current.links[i];
			pred[i] = current;
		}
		return pred;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean add(E item){
		if(head == null){
			head =new SLNode(maxLevel, item);
			size++;
			return true;
		}
		SLNode<E>[] update = search(item);
		if(update[0].links[0] != null && 
				update[0].links[0].data.compareTo(item)==0)
			return false;
		size++;
		if(size>maxCap){
			maxLevel++;
			maxCap = computeMaxCap(maxLevel);
			head.links = Arrays.copyOf(head.links, maxLevel);
			update = Arrays.copyOf(update, maxLevel);
			update[maxLevel - 1] = head;
		}
		
		SLNode newNode =new SLNode(logRandom(), item);
		for(int i = 0; i < newNode.links.length; i++){
			newNode.links[i] = update[i].links[i];
			update[i].links[i] = newNode;
		}
		return true;		
	}
	
	private int logRandom(){
		Random rand = new Random();
		int r = rand.nextInt(maxLevel);
		int k = (int)(Math.log(r+1)/2);
		if(k> maxLevel - 1)
			k = maxLevel-1;
		return maxLevel-1;
	}
	
	private int computeMaxCap(int level){
		return (2^level) - 1;
	}
	
	public int getSize(){
		return size;
	}
}
