package mazegame.item;

public class QuestObject extends Item{
	
	public QuestObject() {
		this.value = 0;
		this.sellability = false;
	}
	
	public String toString(){
		return "Cet objet n'est pas vendable";
	}
}
