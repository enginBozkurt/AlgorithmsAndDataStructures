import java.util.ArrayList;

public class SolutionRouters {

	// reachedRouters will be used to mark which routers we can reach from the
	// one we are starting
	ArrayList<Integer> reachedRouters = new ArrayList<Integer>();
	int numRouters;
	int numLinks;
	ArrayList<ArrayList<Integer>> links = new ArrayList();

	public static void main(String[] args) {
		// create object, initialize it, and run it
		SolutionRouters s = new SolutionRouters();
		s.init();
		s.criticalAmazonRouters();
	}

	public void init() {
		// initialize routers and links
		numRouters = 7;
		numLinks = 7;
		ArrayList<Integer> link0 = new ArrayList<Integer>();
		link0.add(0);
		link0.add(1);
		ArrayList<Integer> link1 = new ArrayList<Integer>();
		link1.add(0);
		link1.add(2);
		ArrayList<Integer> link2 = new ArrayList<Integer>();
		link2.add(1);
		link2.add(3);
		ArrayList<Integer> link3 = new ArrayList<Integer>();
		link3.add(2);
		link3.add(3);
		ArrayList<Integer> link4 = new ArrayList<Integer>();
		link4.add(2);
		link4.add(5);
		ArrayList<Integer> link5 = new ArrayList<Integer>();
		link5.add(3);
		link5.add(4);
		ArrayList<Integer> link6 = new ArrayList<Integer>();
		link6.add(5);
		link6.add(6);
		links.add(link0);
		links.add(link1);
		links.add(link2);
		links.add(link3);
		links.add(link4);
		links.add(link5);
		links.add(link6);
		// initialize reachedRouters arrayList to the size of the number of
		// routers
		for (int i = 0; i < numRouters; i++) {
			reachedRouters.add(0);
		}
	}
	
	public void criticalAmazonRouters() {
		// iterate through routers and check if they are critical. If yes, print
		// them
		for (int i = 0; i < numRouters; i++) {
			if (checkIfCritical(i)) {
				System.out.println(i);
			}
		}
	}
	
	public boolean checkIfCritical(int offlineRouter) {
		System.out.println("checking " + offlineRouter);
		// reset the reached routers to 0
		for (int i = 0; i < reachedRouters.size(); i++) {
			reachedRouters.set(i, 0);
		}
		// pick starting router to traverse from
		int currentRouter = 0;
		if (offlineRouter == 0)
			currentRouter = 1;
		// mark offline router and where we start traversing as visited
		reachedRouters.set(offlineRouter, 1);
		reachedRouters.set(currentRouter, 1);
		System.out.println("     " + reachedRouters);
		// start following the links
		traverse(currentRouter);
		for (int i = 0; i < reachedRouters.size(); i++) {
			if (reachedRouters.get(i) == 0)
				return true;
		}
		return false;
	}

	public void traverse(int currentRouter) {
		System.out.println("     Traversing " + currentRouter);
		// check all links to find links related to the router we are at
		for (int j = 0; j < links.size(); j++) {
			ArrayList<Integer> currentLink = links.get(j);
			// if either side of the link matches our router, we set the
			// connectedRouter
			int connectedRouter = -1;
			if (currentLink.get(0) == currentRouter)
				connectedRouter = currentLink.get(1);
			else if (currentLink.get(1) == currentRouter)
				connectedRouter = currentLink.get(0);
			// if we found a connected router, we check if we've visited it
			// before
			if (connectedRouter > -1) {
				System.out.println("     Found connected router: " + connectedRouter);
				// if not marked before, we mark it now as visited, and we
				// continue traversing from there
				if (reachedRouters.get(connectedRouter) == 0) {
					reachedRouters.set(connectedRouter, 1);
					System.out.println("     Marking router as visited");
					System.out.println("     " + reachedRouters);
					traverse(connectedRouter);
				}
				// if already marked, we stop the traversing here
				System.out.println("     Router already visited, stop this traversing");
			}
		}
	}
}
