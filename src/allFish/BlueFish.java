package allFish;


/**
 * @author williamokeeffe
 *20050506
 *
 *This class is a sub class of the fish represents 
 *blue fish the medium sized fish 
 *Blue fish can eat yellow fish but not red fish
 */
public class BlueFish extends Fish{
	
		private BlueFish friend;
		private YellowFish lunch;
		String color;
		
		
		
		public BlueFish (){
			super();
			String color = "blueFish";
			lunch = new YellowFish();
		}


		@Override
		public String toString() {
			return  ": BlueFish id=" + id + "  isReferenced= "+ isReferenced() +  ",  Root= " + isRoot() + ",  lined to root=" + isLinkedToRoot() + ",  Marked="+ marked + "\n";
		}

}