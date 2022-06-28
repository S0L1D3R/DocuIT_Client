package pl.interia.rym.maciej.Structures;

public class DITLinkGhost {

	private int element1Id;
	private int element2Id;
	
	public DITLinkGhost(int element1Id, int element2Id) {
		this.element1Id = element1Id;
		this.element2Id = element2Id;
	}

	public int getElement1Id() {
		return element1Id;
	}

	public void setElement1Id(int element1Id) {
		this.element1Id = element1Id;
	}

	public int getElement2Id() {
		return element2Id;
	}

	public void setElement2Id(int element2Id) {
		this.element2Id = element2Id;
	}
	
}
