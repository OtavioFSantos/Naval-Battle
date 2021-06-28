import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class DistributeVehiclesPlayer{

    //atributos que irao auxiliar
    private Random create = new Random();
    private int auxSingle;
    private int auxVehicle;
    private boolean space = false;

    //metodo para distribuicao dos navios no board do player
    public void distribui(int[][] array, JButton[][] tabela){

        //gera uma posicao aleatoria no board
        int lineRandom = create.nextInt(10);
        int columnRandom = create.nextInt(10);

        //criacao de lista contendo os vehicles do jogo
        ArrayList<Vehicles> vehicles = new ArrayList<Vehicles>();

        //adiciona os vehicles para a lista
        vehicles.add(new AircraftCarrier());
        vehicles.add(new Aircraft());
        vehicles.add(new Submarine());
        vehicles.add(new EscortShip());

        //inicializa um array em zero, tal array que ira auxiliar para saber quando ha ou nao ha vehicles em celulas
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				array[i][j] = 0;
			}
		}
        
        //repeticao para preencher o board do player
        for(int i = 0; i < vehicles.size(); i++){

            //verifica se pode tem space na posicao gerada
			space = spaceVehicle(lineRandom, columnRandom, vehicles.get(i).getSizeVehicle(), array);

            //loop ate ser gerada uma posicao valida no board
			if(space == false){
				while(space == false){
					lineRandom = create.nextInt(10);
					columnRandom = create.nextInt(10);
					space = spaceVehicle(lineRandom, columnRandom, vehicles.get(i).getSizeVehicle(), array);
				}
			}

            //coloca o aircraft na board
            if(vehicles.get(i).getNameVehicle() == "Aircraft"){
                tabela[lineRandom][columnRandom].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft1.png")));
                tabela[lineRandom][columnRandom].setText("");
                array[lineRandom][columnRandom] = 1;
                tabela[lineRandom][columnRandom+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft2.png")));
                tabela[lineRandom][columnRandom+1].setText("");
                array[lineRandom][columnRandom+1] = 1;
            }

            //coloca o submarine no board
            if(vehicles.get(i).getNameVehicle() == "Submarine"){
                tabela[lineRandom][columnRandom].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine1.png")));
                tabela[lineRandom][columnRandom].setText("");
                array[lineRandom][columnRandom] = 2;
                tabela[lineRandom][columnRandom+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine2.png")));
                tabela[lineRandom][columnRandom+1].setText("");
                array[lineRandom][columnRandom+1] = 2;
            }

            //coloca o navio de escolta no board
            if(vehicles.get(i).getNameVehicle() == "EscortShip"){
                tabela[lineRandom][columnRandom].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip1.png")));
                tabela[lineRandom][columnRandom].setText("");
                array[lineRandom][columnRandom] = 3;
                tabela[lineRandom][columnRandom+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip2.png")));
                tabela[lineRandom][columnRandom+1].setText("");
                array[lineRandom][columnRandom+1] = 3;
                tabela[lineRandom][columnRandom+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip3.png")));
                tabela[lineRandom][columnRandom+2].setText("");
                array[lineRandom][columnRandom+2] = 3;
            }

            //coloca o porta aviao no board
            if(vehicles.get(i).getNameVehicle() == "AircraftCarrier"){
                tabela[lineRandom][columnRandom].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier1.png")));
                tabela[lineRandom][columnRandom].setText("");
                array[lineRandom][columnRandom] = 4;
                tabela[lineRandom][columnRandom+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier2.png")));
                tabela[lineRandom][columnRandom+1].setText("");
                array[lineRandom][columnRandom+1] = 4;
                tabela[lineRandom][columnRandom+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier3.png")));
                tabela[lineRandom][columnRandom+2].setText("");
                array[lineRandom][columnRandom+2] = 4;
                tabela[lineRandom][columnRandom+3].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier4.png")));
                tabela[lineRandom][columnRandom+3].setText("");
                array[lineRandom][columnRandom+3] = 4;
            }
		}
	}

    //metodo para verificar se ha espaço para por veiculo
    public boolean spaceVehicle(int line, int column, int size, int array[][]){
		if(column > (10 - size)){
			return false;
		}
		for(int i = 0; i < size; i++){
			if(array[line][column] == 0){
				column++;
			}
			else{
				return false;
			}
		}
		return true;
	}

    //setter para o atributo responsavel por auxiliar no disparo single
    public void setAuxSingle(int value){
        auxSingle = value;
    }

    //metodo para colocar os vehicles em campo de acordo com a posicao que o player escolher
    public void define(String selected, int[][] arrayPlayer, JButton[][] tablePlayer, JButton aircraft, JButton submarine, JButton escortShip, JButton aircraftCarrier, int line, int column){
        
        //verifica se ha space para o aircraft
        space = spaceVehicle(line, column, 2, arrayPlayer);

        //se houver space, coloca o aircraft
        if(selected == "aircraft" && space){
            tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft1.png")));
            tablePlayer[line][column].setText("");
            arrayPlayer[line][column] = 1;
            tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft2.png")));
            tablePlayer[line][column+1].setText("");
            arrayPlayer[line][column+1] = 1;
            aircraft.setEnabled(false);
        }

        //verifica se ha space para o submarine
        space = spaceVehicle(line, column, 2, arrayPlayer);

        //se houver space, coloca o submarine
        if(selected == "submarine" && space){
            tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine1.png")));
            tablePlayer[line][column].setText("");
            arrayPlayer[line][column] = 2;
            tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine2.png")));
            tablePlayer[line][column+1].setText("");
            arrayPlayer[line][column+1] = 2;
            submarine.setEnabled(false);
        }

        //verifica se ha space para o navio de escolta
        space = spaceVehicle(line, column, 3, arrayPlayer);
        
        //se houver space, coloca o navio de escolta
        if(selected == "escortShip" && space){
            tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip1.png")));
            tablePlayer[line][column].setText("");
            arrayPlayer[line][column] = 3;
            tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip2.png")));
            tablePlayer[line][column+1].setText("");
            arrayPlayer[line][column+1] = 3;
            tablePlayer[line][column+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip3.png")));
            tablePlayer[line][column+2].setText("");
            arrayPlayer[line][column+2] = 3;
            escortShip.setEnabled(false);
        }

        //verifica se ha space para o porta aviao
        space = spaceVehicle(line, column, 4, arrayPlayer);
        
        //se houver space, coloca o porta aviao
        if(selected == "aircraftCarrier" && space){
            tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier1.png")));
            tablePlayer[line][column].setText("");
            arrayPlayer[line][column] = 4;
            tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier2.png")));
            tablePlayer[line][column+1].setText("");
            arrayPlayer[line][column+1] = 4;
            tablePlayer[line][column+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier3.png")));
            tablePlayer[line][column+2].setText("");
            arrayPlayer[line][column+2] = 4;
            tablePlayer[line][column+3].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier4.png")));
            tablePlayer[line][column+3].setText("");
            arrayPlayer[line][column+3] = 4;
            aircraftCarrier.setEnabled(false);
        }
    }

    //metodo para realizar o disparo do player
    public void tiroPlayer(String selected, int arrayPc[][], JButton[][] tablePc, int line, int column, int arrayPlayer[][], JButton singleShot){
        
        auxVehicle = 0;

        //DISPARO UNICO  .

        //verifica se o porta aviao ainda esta em jogo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPlayer[i][j] == 4){
                    auxVehicle = 1;
                }
			}
		}

        //realiza o disparo se houver porta aviao e ele estiver pronto para atirar
        if(selected == "single" && (auxSingle % 3) == 0 && auxVehicle == 1){
            if(arrayPc[line][column] > 0){
                tablePc[line][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line][column].setText("");
                arrayPc[line][column] = -2;
            }else if(arrayPc[line][column] != -2){
                tablePc[line][column].setEnabled(false);
                arrayPc[line][column] = -1;
            }
            auxSingle++;
        } else if(auxVehicle == 1 && selected == "single"){
            auxSingle++;
        }
        
        //DISPARO COMUM  .

        //verifica se o submarine ainda esta em jogo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPlayer[i][j] == 2){
                    auxVehicle = 2;
                }
			}
		}

        //realiza o disparo se houver submarine em jogo
        if(selected == "common" && auxVehicle == 2){
            if(arrayPc[line][column] > 0){
                tablePc[line][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line][column].setText("");
                arrayPc[line][column] = -2;
            }else if(arrayPc[line][column] != -2){
                tablePc[line][column].setEnabled(false);
                arrayPc[line][column] = -1;
            }

            //conta +1 turno para o disparo single
            if((auxSingle % 3) != 0){
                auxSingle++;
            }
        }
    
        //DISPARO CASCATA  ..

        //verifica se o navio de escolta ainda esta em jogo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPlayer[i][j] == 3){
                    auxVehicle = 3;
                }
			}
		}

        //realiza o disparo se houver o navio de escolta ainda em campo
        if(selected == "cascade" && auxVehicle == 3){

            //disparo no local escolhido
            if(arrayPc[line][column] > 0){
                tablePc[line][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line][column].setText("");
                arrayPc[line][column] = -2;
            }else if(arrayPc[line][column] != -2){
                tablePc[line][column].setEnabled(false);
                arrayPc[line][column] = -1;
            }

            //disparo a direita do local escolhido
            if(arrayPc[line][column+1] > 0 && (column+1) < 10){
                tablePc[line][column+1].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line][column+1].setText("");
                arrayPc[line][column+1] = -2;
            }else if(arrayPc[line][column+1] != -2 && (column+1) < 10){
                tablePc[line][column+1].setEnabled(false);
                arrayPc[line][column+1] = -1;
            }

            //conta +1 turno para o disparo single
            if((auxSingle % 3) != 0){
                auxSingle++;
            }
        }
        
        //DISPARO ESTRELA  +

        //verifica se o aircraft ainda esta em jogo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPlayer[i][j] == 1){
                    auxVehicle = 4;
                }
			}
		}

        //realiza o disparo se o aircraft estiver em campo
        if(selected == "star" && auxVehicle == 4){

            //disparo no local escolhido
            if(arrayPc[line][column] > 0){
                tablePc[line][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line][column].setText("");
                arrayPc[line][column] = -2;
            }else if(arrayPc[line][column] != -2){
                tablePc[line][column].setEnabled(false);
                arrayPc[line][column] = -1;
            }

            //disparo nos locais 4 adjacentes
            if((column+1) < 10 && arrayPc[line][column+1] > 0){
                tablePc[line][column+1].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line][column+1].setText("");
                arrayPc[line][column+1] = -2;
            }else if((column+1) < 10 && arrayPc[line][column+1] != -2){
                tablePc[line][column+1].setEnabled(false);
                arrayPc[line][column+1] = -1;
            }

            if((column-1) >= 0 && arrayPc[line][column-1] > 0){
                tablePc[line][column-1].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line][column-1].setText("");
                arrayPc[line][column-1] = -2;
            }else if((column-1) >= 0 && arrayPc[line][column-1] != -2){
                tablePc[line][column-1].setEnabled(false);
                arrayPc[line][column-1] = -1;
            }

            if((line+1) < 10 && arrayPc[line+1][column] > 0){
                tablePc[line+1][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line+1][column].setText("");
                arrayPc[line+1][column] = -2;
            }else if((line+1) < 10 && arrayPc[line+1][column] != -2){
                tablePc[line+1][column].setEnabled(false);
                arrayPc[line+1][column] = -1;
            }

            if((line-1) >= 0 && arrayPc[line-1][column] > 0){
                tablePc[line-1][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePc[line-1][column].setText("");
                arrayPc[line-1][column] = -2;
            }else if((line-1) >= 0 && arrayPc[line-1][column] != -2){
                tablePc[line-1][column].setEnabled(false);
                arrayPc[line-1][column] = -1;
            }  
            
            //conta +1 turno para o disparo single
            if((auxSingle % 3) != 0){
                auxSingle++;
            }
        }
        
        //conta +1 turno para o disparo single quando o porta aviao for o single em campo
        if(auxVehicle == 1){
            auxSingle++;
        }

        //teste para ativacao ou desativacao do botao de disparo single se houver porta aviao e seu delay ja tiver cessado
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPlayer[i][j] == 4){
                    auxVehicle = 1;
                }
			}
		}
        if((auxSingle % 3) != 0){
            singleShot.setEnabled(false);
        }else if(auxVehicle == 1){
            singleShot.setEnabled(true);
        }
    }
}