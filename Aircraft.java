public class Aircraft extends Vehicles{

	//atributos do aircraft
	private int sizeVehicle = 2;
	private String nameVehicle = "Aircraft";
	
	//overrida dos metodos herdados
	@Override
	public int getSizeVehicle() {
		return sizeVehicle;
	}
	@Override
	public String getNameVehicle(){
		return nameVehicle;
	}
}