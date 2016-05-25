package allFish;

/**
 * @author williamokeeffe
 *20050506
 *
 *This class is a sub class of the fish and represents 
 *red fish the largest fish and can eat both blue and yellow fish.
 */


public class RedFish extends Fish{
	
    private RedFish friend;
	private BlueFish lunch;
	private YellowFish snack;
	
	
	public String color;

	public RedFish(){
		super();
		String color = "redFish";
		lunch = new BlueFish();
		snack = new YellowFish();
		
	}

	@Override
	public String toString() {
		return  ": RedFish id=" + id + "  isReferenced= "+ isReferenced() +  ",  Root= " + isRoot() + ",  lined to root=" + isLinkedToRoot() + ",  Marked="+ marked + "\n";
	}

	
	
}