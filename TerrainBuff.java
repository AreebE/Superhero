public class TerrainBuff{

  private Element terrainElement;
  private Ability a;

  public TerrainBuff(Ability a, Element tE){
    this.a = a;
    this.terrainElement = tE;
  }

  public void appliesTerrianBuff(){
    //if tE == a.getElement() than attack x 1.5 and defense x.5
    Element aElement = a.getElement();
    if(terrainElement.getsName() == aElement.getsName()){

    }

  }
  
}