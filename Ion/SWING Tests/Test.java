import Game_Engine.*;
import java.util.*;

class Test{
	Board b;
	DataForEngine d;
	Timer t;
	public Test(){
		d = new DataForEngine();
		b = new Board(500,500, "bancho",1,1);
		b.setGameMode(1);
		t = new Timer();
		t.schedule(new TimerTask(){
			@Override
			public void run(){
				b.update(d);
			}
		},0,500);
	}
	public static void main(String[] args) {
		new Test();
	}
}