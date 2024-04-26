package ProtoUtil;

import java.util.Random;

public class MyRandom {
	private Random random;
	public boolean test;
	
	public MyRandom() {
		random=new Random();
		test=false;
	}
	
	public MyRandom(boolean isTest) {
		random=new Random();
		test=isTest;
	}
	
	public int nextInt(int upper_bound, int testValue) {
		if(test)
			return testValue;
		else
			return random.nextInt(upper_bound);
	}
}
