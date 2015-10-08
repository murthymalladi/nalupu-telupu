import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class BreadthFirstSearch {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private int s;
	public BreadthFirstSearch(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		this.s = s;
		bfs(G,s);
	}
	public void bfs(Graph G,int s){
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int v = 0; v < G.V();v++)
			distTo[v] = INFINITY;
		distTo[s] = 0;
		marked[s] = true;
		queue.add(s);
		int v,w;
		while( !queue.isEmpty() ){
			v = queue.poll();
		    ArrayList<Integer> list = G.adjList(v);
		    for(int i = 0; i < list.size(); i++){
		    	w = list.get(i);
		    	if(!marked[w]){
		    		distTo[w] = distTo[v] + 1;
		    		edgeTo[w] = v;
		    		marked[w] = true;
		    		queue.add(w);
		    	}
		    }
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public void pathTo(int v){
		Stack<Integer> stack = new Stack<Integer>();
		if(hasPathTo(v)){
			for(int x = v; x != s; x = edgeTo[x])
				stack.push(x);
		}
		while(!stack.isEmpty()){
			System.out.print(stack.peek()+" ");
			stack.pop();
		}
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int source = in.nextInt();
		Graph G = new Graph(in);
		System.out.println(G);
		BreadthFirstSearch bfsPaths = new BreadthFirstSearch(G,source);
		for(int v = 0; v < G.V(); v++){
	    	System.out.printf("%d to %d: ",source,v);
			if(bfsPaths.hasPathTo(v)){
				bfsPaths.pathTo(v);
			}
			else
			System.out.print("not connected");
			System.out.println();
	    }
	}
}
