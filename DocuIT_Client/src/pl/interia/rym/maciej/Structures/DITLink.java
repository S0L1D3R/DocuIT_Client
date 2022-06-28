package pl.interia.rym.maciej.Structures;

import java.awt.Point;

public class DITLink {
	
	private DITProjectElement element1;
	private DITProjectElement element2;
	
    public DITLink(DITProjectElement firstElement, DITProjectElement secondElement){
    	element1 = firstElement;
    	element2 = secondElement;
    }
    
    public boolean hasIdInOneOfElements(int id) {
    	if(element1.getId() == id || element2.getId() == id) {
    		return true;
    	}
    	return false;
    }
    
    public DITProjectElement getElementByID(int id) {
    	if(element1.getId() == id) {
    		return element1;
    	}
    	if(element2.getId() == id) {
    		return element2;
    	}
    	return null;
    }
    
    public Point getMiddleOfFirstElement() {
    	return element1.getMiddlePoint();
    }
    
    public Point getMiddlePointOfSecondElement() {
    	return element2.getMiddlePoint();
    }
    
	public DITProjectElement getElement1() {
		return element1;
	}

	public void setElement1(DITProjectElement element1) {
		this.element1 = element1;
	}

	public DITProjectElement getElement2() {
		return element2;
	}

	public void setElement2(DITProjectElement element2) {
		this.element2 = element2;
	} 
}