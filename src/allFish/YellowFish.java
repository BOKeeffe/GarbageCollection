package allFish;
/**
 * @author williamokeeffe
 *20050506
 *
 *This class is a sub class of the fish and represents 
 *yellow fish the smallest fish 
 */

public class YellowFish extends Fish {
	
	private YellowFish friend;
	String color;

	
	
	public YellowFish(){
		super();
		String color = "yellowFish";
	}



	@Override
	public String toString() {
		return  ": YellowFish id=" + id + "  isReferenced= "+ isReferenced() +  ",  Root= " + isRoot() + ",  lined to root=" + isLinkedToRoot() + ",  Marked="+ marked + "\n";
	}
	
	
	
}