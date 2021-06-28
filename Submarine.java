public class Submarine extends Vehicles{

	//atributos do submarine
	private int sizeVehicle = 2;
	private String nameVehicle = "Submarine";
	
	//override dos metodos herdados
	@Override
	public int getSizeVehicle() {
		return sizeVehicle;
	}
	@Override
	public String getNameVehicle(){
		return nameVehicle;
	}
}