import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class DistributeVehiclesPc{

    //atributos auxiliares
    private Random create = new Random();
    private int lineRandom;
    private int columnRandom;
    private int auxSingle;
    private int auxVehiclePc;
    private int auxVehiclePlayer;
    private int auxDown;
    private Times times = new Times();

    //metodo para distribuicao dos navios no board do computador
    public void distribui(int[][] array, JButton[][] tabela){

        //gera uma posicao aleatoria no board
        lineRandom = create.nextInt(10);
        columnRandom = create.nextInt(10);
        boolean space = false;

        //criacao de lista contendo os vehicles do jogo
        ArrayList<Vehicles> vehicles = new ArrayList<Vehicles>();

        //ahinto dos vehicles para a lista
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

        //preenche o board
        for(int i = 0; i < vehicles.size(); i++){

            //verifica se os spaces gerados ja estao ocupados
			space = spaceVehicle(lineRandom, columnRandom, vehicles.get(i).getSizeVehicle(), array);

            //gera novas posicoes se as escolhidas anteriormente forem invalidas
			if(space == false){
				while(space == false){
					lineRandom = create.nextInt(10);
					columnRandom = create.nextInt(10);
					space = spaceVehicle(lineRandom, columnRandom, vehicles.get(i).getSizeVehicle(), array);
				}
			}

            //coloca o aircraft no board
            if(vehicles.get(i).getNameVehicle() == "Aircraft"){
                array[lineRandom][columnRandom] = 1;
                array[lineRandom][columnRandom+1] = 1;
            }

            //coloca o submarine no board
            if(vehicles.get(i).getNameVehicle() == "Submarine"){
                array[lineRandom][columnRandom] = 2;
                array[lineRandom][columnRandom+1] = 2;
            }

            //coloca o navio de escolta no board
            if(vehicles.get(i).getNameVehicle() == "EscortShip"){
                array[lineRandom][columnRandom] = 3;
                array[lineRandom][columnRandom+1] = 3;
                array[lineRandom][columnRandom+2] = 3;
            }

            //coloca o porta aviao no board
            if(vehicles.get(i).getNameVehicle() == "AircraftCarrier"){
                array[lineRandom][columnRandom] = 4;
                array[lineRandom][columnRandom+1] = 4;
                array[lineRandom][columnRandom+2] = 4;
                array[lineRandom][columnRandom+3] = 4;
            }
		}
	}

    //metodo para verificar se ha space disponivel
    public boolean spaceVehicle(int line, int column, int size, int array[][]){
		if (column > (10 - size)){
			return false;
		}
		for (int i = 0; i < size; i++){
			if (array[line][column] == 0){
				column++;
			}
			else {
				return false;
			}
		}
		return true;
	}

    //setter para o atributo que ajuda a saber se o disparo single esta pronto
    public void setAuxSingle(int value){
        auxSingle = value;
    }

    //metodo que realiza o disparo do computador
    public void tiroPc(int arrayPlayer[][], JButton[][] tablePlayer, int arrayPc[][], JButton singleShot, JButton commonShot, JButton cascade, JButton star, Player player){
        
        auxDown = 0;
        auxVehiclePc = 0;
        auxVehiclePlayer = 0;

        //verifica se ha uma posicao de veiculo do player ja atingido, para fazer o computador atirar perto dali
        for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(arrayPlayer[i][j] == -2){
                    if((j+1) < 10 && arrayPlayer[i][j+1] >= 0){
                        auxDown = 1;
                        lineRandom = i;
                        columnRandom = j+1;
                        break;
                    }else if((j-1) >= 0 && arrayPlayer[i][j-1] >= 0){
                        auxDown = 1;
                        lineRandom = i;
                        columnRandom = j-1;
                        break;
                    }
                }
			}
        }

        //se nao houver nenhum veiculo do player para o computador atirar perto, ele atirara numa posicao aleatoria
        if(auxDown != 1){
            lineRandom = create.nextInt(10);
            columnRandom = create.nextInt(10);
            while(arrayPlayer[lineRandom][columnRandom] < 0){
                lineRandom = create.nextInt(10);
                columnRandom = create.nextInt(10);
            }
        }

        //o computador atira priorizando os disparos mais efetivos

        //DISPARO ESTRELA  +

        //primeiro ele tenta atirar com o aircraft, verificando se ele esta em campo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPc[i][j] == 1){
                    auxVehiclePc = 1;
                }
			}
		}

        //caso estiver, ele atira
        if(auxVehiclePc == 1){

            //atira na posicao origem
            if(arrayPlayer[lineRandom][columnRandom] > 0){
                tablePlayer[lineRandom][columnRandom].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom][columnRandom].setText("");
                arrayPlayer[lineRandom][columnRandom] = -2;
            }else if(arrayPlayer[lineRandom][columnRandom] != -2){
                tablePlayer[lineRandom][columnRandom].setEnabled(false);
                arrayPlayer[lineRandom][columnRandom] = -1;
            }

            //atira nas posicoes adjacentes
            if((columnRandom+1) < 10 && arrayPlayer[lineRandom][columnRandom+1] > 0){
                tablePlayer[lineRandom][columnRandom+1].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom][columnRandom+1].setText("");
                arrayPlayer[lineRandom][columnRandom+1] = -2;
            }else if((columnRandom+1) < 10 && arrayPlayer[lineRandom][columnRandom+1] != -2){
                tablePlayer[lineRandom][columnRandom+1].setEnabled(false);
                arrayPlayer[lineRandom][columnRandom+1] = -1;
            }

            if((columnRandom-1) >= 0 && arrayPlayer[lineRandom][columnRandom-1] > 0){
                tablePlayer[lineRandom][columnRandom-1].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom][columnRandom-1].setText("");
                arrayPlayer[lineRandom][columnRandom-1] = -2;
            }else if((columnRandom-1) >= 0 && arrayPlayer[lineRandom][columnRandom-1] != -2){
                tablePlayer[lineRandom][columnRandom-1].setEnabled(false);
                arrayPlayer[lineRandom][columnRandom-1] = -1;
            }

            if((lineRandom+1) < 10 && arrayPlayer[lineRandom+1][columnRandom] > 0){
                tablePlayer[lineRandom+1][columnRandom].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom+1][columnRandom].setText("");
                arrayPlayer[lineRandom+1][columnRandom] = -2;
            }else if((lineRandom+1) < 10 && arrayPlayer[lineRandom+1][columnRandom] != -2){
                tablePlayer[lineRandom+1][columnRandom].setEnabled(false);
                arrayPlayer[lineRandom+1][columnRandom] = -1;
            }

            if((lineRandom-1) >= 0 && arrayPlayer[lineRandom-1][columnRandom] > 0){
                tablePlayer[lineRandom-1][columnRandom].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom-1][columnRandom].setText("");
                arrayPlayer[lineRandom-1][columnRandom] = -2;
            }else if((lineRandom-1) >= 0 && arrayPlayer[lineRandom-1][columnRandom] != -2){
                tablePlayer[lineRandom-1][columnRandom].setEnabled(false);
                arrayPlayer[lineRandom-1][columnRandom] = -1;
            }
            
            //conta +1 turno para o disparo single
            if((auxSingle % 3) != 0){
                auxSingle++;
            }
        }

        //DISPARO CASCATA  ..

        //verifica se o navio de escolta esta em jogo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPc[i][j] == 3){
                    auxVehiclePc = 1;
                }
			}
		}

        //se estiver em jogo, ele atira
        if(auxVehiclePc == 1){

            //tiro na origem
            if(arrayPlayer[lineRandom][columnRandom] > 0){
                tablePlayer[lineRandom][columnRandom].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom][columnRandom].setText("");
                arrayPlayer[lineRandom][columnRandom] = -2;
            }else if(arrayPlayer[lineRandom][columnRandom] != -2){
                tablePlayer[lineRandom][columnRandom].setEnabled(false);
                arrayPlayer[lineRandom][columnRandom] = -1;
            }
            
            //tiro na posicao a direita da origem
            if((columnRandom+1) < 10 && arrayPlayer[lineRandom][columnRandom+1] > 0){
                tablePlayer[lineRandom][columnRandom+1].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom][columnRandom+1].setText("");
                arrayPlayer[lineRandom][columnRandom+1] = -2;
            }else if((columnRandom+1) < 10 && arrayPlayer[lineRandom][columnRandom+1] != -2){
                tablePlayer[lineRandom][columnRandom+1].setEnabled(false);
                arrayPlayer[lineRandom][columnRandom+1] = -1;
            }
            
            //conta +1 turno para o disparo single se ele nao estiver preparado
            if((auxSingle % 3) != 0){
                auxSingle++;
            }
        }

        //DISPARO COMUM  .

        //verifica se o submarine esta em jogo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPc[i][j] == 2){
                    auxVehiclePc = 1;
                }
			}
		}

        //se estiver, ele atira
        if(auxVehiclePc == 1){
            if(arrayPlayer[lineRandom][columnRandom] > 0){
                tablePlayer[lineRandom][columnRandom].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom][columnRandom].setText("");
                arrayPlayer[lineRandom][columnRandom] = -2;
            }else if(arrayPlayer[lineRandom][columnRandom] != -2){
                tablePlayer[lineRandom][columnRandom].setEnabled(false);
                arrayPlayer[lineRandom][columnRandom] = -1;
            }

            //conta +1 turno para o disparo single se ele nao estiver preparado
            if((auxSingle % 3) != 0){
                auxSingle++;
            }
        }

        //DISPARO UNICO  .

        //verifica se o porta aviao esta em jogo
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPc[i][j] == 4){
                    auxVehiclePc = 1;
                }
			}
		}

        //se estiver, ele atira
        if(auxVehiclePc == 1 && (auxSingle % 3) == 0){
            if(arrayPlayer[lineRandom][columnRandom] > 0){
                tablePlayer[lineRandom][columnRandom].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/x.png")));
                tablePlayer[lineRandom][columnRandom].setText("");
                arrayPlayer[lineRandom][columnRandom] = -2;
            }else if(arrayPlayer[lineRandom][columnRandom] != -2){
                tablePlayer[lineRandom][columnRandom].setEnabled(false);
                arrayPlayer[lineRandom][columnRandom] = -1;
            }

            //adiciona +1 turno ao seu disparo apos disparar
            auxSingle++;
        }else{

            //adiciona +1 turno ao disparo single se o porta aviao for o single em campo e nao puder atirar
            auxSingle++;
        }
        
        //testa se algum veiculo do player foi destruido e desabilita o botao de disparo do mesmo

        //porta aviao - disparo single
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(arrayPlayer[i][j] == 4){
                    auxVehiclePlayer = 1;
                }
            }
        }
        if(auxVehiclePlayer != 1){
            singleShot.setEnabled(false);
        }

        //submarine - disparo common
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(arrayPlayer[i][j] == 2){
                    auxVehiclePlayer = 2;
                }
            }
        }
        if(auxVehiclePlayer != 2){
            commonShot.setEnabled(false);
        }

        //navio de escolta - disparo cascade
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(arrayPlayer[i][j] == 3){
                    auxVehiclePlayer = 3;
                }
            }
        }
        if(auxVehiclePlayer != 3){
            cascade.setEnabled(false);
        }

        //aircraft - disparo star
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(arrayPlayer[i][j] == 1){
                    auxVehiclePlayer = 4;
                }
            }
        }
        if(auxVehiclePlayer != 4){
            star.setEnabled(false);
        }

        //testa a derrota do player
        auxVehiclePlayer = 0;
        for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if(arrayPlayer[i][j] > 0){
                    auxVehiclePlayer = 1;
                }
			}
		}

        //contabiliza derrota ao verificar que nao ha mais vehicles do player disponiveis
        if(auxVehiclePlayer == 0){
            player.setFinalTime(System.currentTimeMillis());
            JOptionPane.showMessageDialog(null, "You lost!\nTime: "+player.getSetTime());

            //mostra o ranking dos melhores times na tela
            Ranking ranking = new Ranking();
			ranking.setVisible(true);
        }

        //verifica se ainda ha algum veiculo do computador em jogo, se nao houver, o player won
        if(auxVehiclePc == 0){
            player.setFinalTime(System.currentTimeMillis());
            JOptionPane.showMessageDialog(null, "You won!\nTime: "+player.getSetTime());

            //o tempo do player sera salvado
            times.saveList(player);

            //mostra o ranking dos melhores times
            Ranking ranking = new Ranking();
			ranking.setVisible(true);
        }
    }
}