public class Player{

	//atributos do player
	private String name;
	private long startTime;
    private long finalTime;

	//getter e setter do name do player
	public String getName() {
		return this.name;
	}
	public void setName(String newName) {
		this.name = newName;
	}

	//metodo para create o tempo final 
	public long getSetTime() {
		return (this.finalTime - this.startTime)/1000;
	}

	//setters relacionados ao tempo do player
	public void setInitialTime(long newInitialTime) {
		this.startTime = newInitialTime;
	}
    public void setFinalTime(long newFinalTime) {
		this.finalTime = newFinalTime;
	}

	//toString do player
	public String toString(){
		String nameTime = "Name: " + this.name + " Time: " + this.getSetTime();
		return nameTime;
	}
}