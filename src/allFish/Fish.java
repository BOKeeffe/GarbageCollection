package allFish;

import visual.Main;


public class Fish {
	
	
	RedFish red;
	BlueFish blue;
	YellowFish yellow;
	Main main;

	public boolean marked;
	public boolean linkedToRoot;
	public boolean root;
	public boolean referenced;
	public String id;
	public int index = 0;;


	public Fish(){
	    //list = new ArrayList<>();
		this.marked = false;
		this.linkedToRoot = false;
		this.root = false;
		this.referenced = false;
		this.id = id;
		
		
		
		
	}
	 public int indexCount(){
		 index = 0;
		 for (int i = 0; i < main.list.size(); i++) {
			index = i;
			index++;
		}
		 return index++;
	 }
	
	
	public boolean isReferenced() {
		return referenced;
	}


	public void setReferenced(boolean referenced) {
		this.referenced = referenced;
	}

/*
	public void add(Fish f){
		if (index <list.size()) {
			list.add(0, blue);
		}
	}
	*/
	
	public boolean isMarked() {
		return marked;
	}


	public void setMarked(boolean marked) {
		this.marked = false;
	}


	public boolean isRoot() {
		return root;
	}


	public void setRoot(boolean root) {
		this.root = root;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isLinkedToRoot() {
		return linkedToRoot;
	}


	public void setLinkedToRoot(boolean linkedToRoot) {
		this.linkedToRoot = linkedToRoot;
	}


	public int getIndex() {
		
		return index++;
	}


	public void setIndex(int index) {
		
		this.index = index++;
	}


	@Override
	public String toString() {
		return "Fish [red=" + red + ", blue=" + blue + ", yellow=" + yellow
				+ ", marked=" + marked + ", linkedToRoot=" + linkedToRoot
				+ ", root=" + root + ", referenced=" + referenced + ", id="
				+ id + ", index=" + index + "]";
	}

	
}