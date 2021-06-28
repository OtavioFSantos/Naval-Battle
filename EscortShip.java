public class EscortShip extends Vehicles{

	//atributos do navio de escolta
	private int sizeVehicle = 3;
	private String nameVehicle = "EscortShip";
	
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