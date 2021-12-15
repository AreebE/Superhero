public class Terrain{

  private boolean buff; 
  private Element terrianElement; 

  public Terrain(){
    
  }

  public boolean getsTerrainBuff(Element abilityEm){
    if(terrianElement == abilityEm){
      return true;
    }
    else{
      return false;
    }
  }

  public void setsTerrianElement(Element tElement){
    this.terrianElement = tElement;
  }

  //double it
  //in future 1/2

  //until we add changing terrian abilities just use a random number for the index for the element 
  
}