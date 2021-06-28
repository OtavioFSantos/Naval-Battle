public class AircraftCarrier extends Vehicles{

	//atributos do porta aviao
	private int sizeVehicle = 4;
	private String nameVehicle = "AircraftCarrier";
	
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