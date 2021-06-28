import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SetBoard extends JFrame implements ActionListener{

    //cria todos componentes necessarios para o set board
    JPanel gridPlayer = new JPanel();
    JPanel gridPc = new JPanel();
    JPanel panel = new JPanel();
    Container container;

    JLabel playerLabel = new JLabel("Player");
	JLabel pc = new JLabel("Computer");
    JLabel stopwatch = new JLabel("");

    JButton[][] tablePlayer = new JButton[10][10];
    JButton[][] tablePc = new JButton[10][10];
	JButton hint = new JButton("Hint");
	JButton singleShot = new JButton("Single");
    JButton commonShot = new JButton("Common");
    JButton cascade = new JButton("Cascade");
    JButton star = new JButton("Star");
    JButton newGame = new JButton("New Game");
    JButton reset = new JButton("Reset");
    JButton exit = new JButton("Exit");
    JButton aircraft = new JButton("");
    JButton submarine = new JButton("");
    JButton escortShip = new JButton("");
    JButton aircraftCarrier = new JButton("");
    JButton play = new JButton("Play");

    //cria atributos que irao auxiliar
    int arrayPlayer[][] = new int[10][10];
    int arrayPc[][] = new int[10][10];
    int arrayPlayerAux[][] = new int[10][10];
    int arrayPcAux[][] = new int[10][10];
    int countHint = 0;
    int auxPlay = 0;
    int auxC;
    int auxS;
    int auxN;
    int auxP;
    String selected = "";
    String auxVer;
    String auxHor;
    String name;
    String msg;
    String msg2;

    //cria objetos que serao de suma importancia
    DistributeVehiclesPlayer distVehPlayer = new DistributeVehiclesPlayer();
    DistributeVehiclesPc distVehPc = new DistributeVehiclesPc();
	Player player = new Player();

    //construtor do set board
    public SetBoard(String newName){
        super("BattleShip Set");

        //configuracoes basicas da janela
		setSize(1100, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel);
        container = getContentPane();
        container.setLayout(null);

        //cria a base do board do player e do computador
        gridPlayer.setBounds(3, 100, 497, 325);
		gridPlayer.setLayout(new GridLayout(10, 10, 2, 2));
        gridPc.setBounds(599, 100, 497, 325);
		gridPc.setLayout(new GridLayout(10, 10, 2, 2));

        //pega o name do player passado por parametro
        player.setName(newName);
        name = newName;

        //preenche ambos boards com uma imagem de ocean
        for(int line = 0; line < 10; line++){
			for(int column = 0; column < 10; column++){
				tablePlayer[line][column] = new JButton(line+"-"+column, new ImageIcon(RandomBoard.class.getResource("/images/ocean.png")));
                tablePlayer[line][column].setHorizontalTextPosition(SwingConstants.CENTER);
                tablePlayer[line][column].setMargin( new Insets(10, 10, 10, 10) );
				tablePlayer[line][column].addActionListener(this);
                tablePlayer[line][column].setForeground(Color.WHITE);
				gridPlayer.add(tablePlayer[line][column]);
                tablePc[line][column] = new JButton(line+"-"+column, new ImageIcon(RandomBoard.class.getResource("/images/ocean.png")));
                tablePc[line][column].setHorizontalTextPosition(SwingConstants.CENTER);
                tablePc[line][column].setMargin( new Insets(10, 10, 10, 10) );
				tablePc[line][column].addActionListener(this);
                tablePc[line][column].setForeground(Color.WHITE);
				gridPc.add(tablePc[line][column]);
			}
		}

        //posiciona os elementos na janela
        exit.setBounds(7, 14, 120, 35);
		exit.addActionListener(this);

		newGame.setBounds(127, 14, 120, 35);
		newGame.addActionListener(this);

        reset.setBounds(247, 14, 120, 35);
		reset.addActionListener(this);

        hint.setBounds(367, 14, 120, 35);
		hint.addActionListener(this);

        singleShot.setBounds(487, 14, 120, 35);
		singleShot.addActionListener(this);

        commonShot.setBounds(607, 14, 120, 35);
		commonShot.addActionListener(this);

        cascade.setBounds(727, 14, 120, 35);
		cascade.addActionListener(this);

        star.setBounds(847, 14, 120, 35);
		star.addActionListener(this);

        playerLabel.setFont(new Font("Arial", Font.BOLD, 15));
		playerLabel.setBounds(12, 78, 120, 18);

        pc.setFont(new Font("Arial", Font.BOLD, 15));
		pc.setBounds(607, 78, 120, 18);

        stopwatch.setFont(new Font("Arial", Font.BOLD, 25));
		stopwatch.setBounds(1000, 10, 120, 50);

        aircraft.setBounds(7, 450, 120, 35);
		aircraft.addActionListener(this);
        aircraft.setIcon(new ImageIcon(SetBoard.class.getResource("/images/aircraft.png")));
        
        submarine.setBounds(127, 450, 120, 35);
		submarine.addActionListener(this);
        submarine.setIcon(new ImageIcon(SetBoard.class.getResource("/images/submarine.png")));

        escortShip.setBounds(247, 450, 120, 35);
		escortShip.addActionListener(this);
        escortShip.setIcon(new ImageIcon(SetBoard.class.getResource("/images/escortShip.png")));

        aircraftCarrier.setBounds(367, 450, 120, 35);
		aircraftCarrier.addActionListener(this);
        aircraftCarrier.setIcon(new ImageIcon(SetBoard.class.getResource("/images/aircraftCarrier.png")));

        play.setBounds(487, 450, 120, 35);
        play.addActionListener(this);

        container.add(gridPlayer);
		container.add(gridPc);
        container.add(exit);
        container.add(newGame);
        container.add(reset);
        container.add(hint);
        container.add(singleShot);
        container.add(commonShot);
        container.add(cascade);
        container.add(star);
        container.add(playerLabel);
        container.add(pc);
        container.add(stopwatch);
        container.add(aircraft);
        container.add(submarine);
        container.add(escortShip);
        container.add(aircraftCarrier);
        container.add(play);

        //distribui os vehicles aleatoriamente no board do computador
        distVehPc.distribui(arrayPc, tablePc);

        //copia os valuees do arrayPc para outro array, ele sera usado caso o jogo seja reiniciado
        for(int line = 0; line < 10; line++){
            for(int column = 0; column < 10; column++){
                arrayPcAux[line][column] = arrayPc[line][column];
            }
        }

        //desabilita a maioria dos botoes, eles serao reativados quando o player montar seu campo e clicar em "play"
        hint.setEnabled(false);
        singleShot.setEnabled(false);
        commonShot.setEnabled(false);
        cascade.setEnabled(false);
        star.setEnabled(false);
        play.setEnabled(false);
        newGame.setEnabled(false);
        reset.setEnabled(false);
    }

    //verifica onde o player clicou
    public void actionPerformed(ActionEvent event){    

        //se o player clicou em "play" os botoes desativados serao reativados
        if(event.getSource() == play){
			hint.setEnabled(true);
            singleShot.setEnabled(true);
            commonShot.setEnabled(true);
            cascade.setEnabled(true);
            star.setEnabled(true);
            newGame.setEnabled(true);
            reset.setEnabled(true);

            //inicia o stopwatch
            player.setInitialTime(System.currentTimeMillis());

            //desabilita o botao de "play", visto que a partida ja iniciou
            play.setEnabled(false);

            //copia os valuees do arrayPlayer para outro array, ele sera usado caso o jogo seja reiniciado
            for(int line = 0; line < 10; line++){
                for(int column = 0; column < 10; column++){
                    arrayPlayerAux[line][column] = arrayPlayer[line][column];
                }
            }
		}
        
        //atualiza o stopwatch a cada acao do player, somente se um dos botoes que inicialmente estavam desabilitados, estiver habilitado
        if(newGame.isEnabled()){
            player.setFinalTime(System.currentTimeMillis());
            stopwatch.setText(""+player.getSetTime());
        }

        //se o player "clicou" em exit, a janela sera fechada
		if(event.getSource() == exit){
			this.dispose();
		}

        //verifica qual opcao o player clicou e marca ela como selecionada
        if(event.getSource() == singleShot){
			selected = "single";
		}
        if(event.getSource() == commonShot){
			selected = "common";
		}
        if(event.getSource() == cascade){
			selected = "cascade";
		}
        if(event.getSource() == star){
			selected = "star";
		}
        if(event.getSource() == hint){
			selected = "hint";
		}
        if(event.getSource() == aircraft){
			selected = "aircraft";
		}
        if(event.getSource() == submarine){
			selected = "submarine";
		}
        if(event.getSource() == escortShip){
			selected = "escortShip";
		}
        if(event.getSource() == aircraftCarrier){
            selected = "aircraftCarrier";
        }

        //verifica se foi clicado dentro da tabela do computador, e qual opcao estava selecionada quando isso ocorreu
		for(int line = 0; line < 10; line++){
			for(int column = 0; column < 10; column++){
                if(event.getSource() == tablePc[line][column] && selected != ""){

                    //se foi selecionada a hint
                    if(selected == "hint"){
                        if(countHint < 3){
                            auxVer = "There are no vertical vehicles. ";
                            for(int i = 0; i < 10; i++){
                                if(arrayPc[i][column] > 0){
                                    auxVer = "There are vertical vehicles. ";
                                }
                            }
                            auxHor = "There are no horizontal vehicles.";
                            for(int i = 0; i < 10; i++){
                                if(arrayPc[line][i] > 0){
                                    auxHor = "There are horizontal vehicles.";
                                }
                            }
                            msg2 = auxVer + auxHor;
                            msg = "Line: "+line+" Column: "+column+"\n"+msg2;
                            JOptionPane.showMessageDialog(null, msg);
                            countHint++;

                            //reseta a opcao selecionada
                            selected = "";

                        //desabilita o botao de hint quando for usado 3 vezes
                        }else if(countHint >= 3){
                            hint.setEnabled(false);
                        }
                    //verifica quando foi clicado no board do computador com uma opcao de disparo selecionada
                    }else if(selected != ""){ 

                        //realiza o disparo do player
                        distVehPlayer.tiroPlayer(selected, arrayPc, tablePc, line, column, arrayPlayer, singleShot);

                        //realiza o disparo do computador
                        distVehPc.tiroPc(arrayPlayer, tablePlayer, arrayPc, singleShot, commonShot, cascade, star, player);

                        //reseta a opcao selecionada
                        selected = "";
                    }
                }
            }
        }

        //verifica se foi clicado dentro da tabela do player
        for(int line = 0; line < 10; line++){
			for(int column = 0; column < 10; column++){
                if(event.getSource() == tablePlayer[line][column] && selected != ""){
                    if(selected == "aircraft" || selected == "submarine" || selected == "escortShip" || selected == "aircraftCarrier"){

                        //se foi clicado dentro do board do player com uma opcao de veiculo selecionada, sera ativado o metodo para posicionar o veiculo no campo
                        distVehPlayer.define(selected, arrayPlayer, tablePlayer, aircraft, submarine, escortShip, aircraftCarrier, line, column);

                        //reseta a opcao selecionada
                        selected = "";
                    }
                }
            }
        }

        //gera um new set board se a opcao for clicada
        if(event.getSource() == newGame){
			this.dispose();
			SetBoard board = new SetBoard(name);
			board.setVisible(true);
			JOptionPane.showMessageDialog(this, "New Game!");
		}

        //reinicia o set board se a opcao for clicada
        if(event.getSource() == reset){

            //atributos para auxiliar no posicionamento das images dos vehicles
            auxC = 0;
            auxS = 0;
            auxN = 0;
            auxP = 0;

            //reseta o atributo que contrhello o disparo single do porta aviao
            distVehPc.setAuxSingle(3);
            distVehPlayer.setAuxSingle(3);

            //reseta as hints
            countHint = 0;

            //habilita os botoes de disparo
            singleShot.setEnabled(true);
            commonShot.setEnabled(true);
            cascade.setEnabled(true);
            star.setEnabled(true);

            //repeticao para reposicionar todas as images de ocean e dos vehicles de volta ao campo
            for(int line = 0; line < 10; line++){
                for(int column = 0; column < 10; column++){

                    //passa os valuees originais de volta aos arrayes e reabilita todo o board
                    arrayPlayer[line][column] = arrayPlayerAux[line][column];
                    tablePlayer[line][column].setEnabled(true);
                    arrayPc[line][column] = arrayPcAux[line][column];
                    tablePc[line][column].setEnabled(true);

                    //coloca a imagem de ocean em todo board do computador
                    tablePc[line][column].setIcon(new ImageIcon(RandomBoard.class.getResource("/images/ocean.png")));
                    tablePc[line][column].setText(line+"-"+column);
                    tablePc[line][column].setHorizontalTextPosition(SwingConstants.CENTER);
                    tablePc[line][column].setMargin( new Insets(10, 10, 10, 10) );
                    tablePc[line][column].addActionListener(this);
                    tablePc[line][column].setForeground(Color.WHITE);

                    //reconstitui o aircraft do player
                    if(arrayPlayer[line][column] == 1 && auxC == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraft2.png")));
                        auxC = 1;
                    }

                    //reconstitui o submarine do player
                    if(arrayPlayer[line][column] == 2 && auxS == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/submarine2.png")));
                        auxS = 1;
                    }

                    //reconstitui o navio de escolta do player
                    if(arrayPlayer[line][column] == 3 && auxN == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip2.png")));
                        tablePlayer[line][column+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/escortShip3.png")));
                        auxN = 1;
                    }

                    //reconstitui o porta aviao do player
                    if(arrayPlayer[line][column] == 4 && auxP == 0){
                        tablePlayer[line][column].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier1.png")));
                        tablePlayer[line][column+1].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier2.png")));
                        tablePlayer[line][column+2].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier3.png")));
                        tablePlayer[line][column+3].setIcon(new ImageIcon(DistributeVehiclesPlayer.class.getResource("/images/aircraftCarrier4.png")));
                        auxP = 1;
                    }
                }
            }

            //reseta o stopwatch e exibe mensagem de jogo reiniciado
            player.setInitialTime(System.currentTimeMillis());
			JOptionPane.showMessageDialog(this, "Restarted!");
		}

        //se o board estiver pronto, habilita o botao de "play"
        if(!aircraft.isEnabled() && !submarine.isEnabled() && !escortShip.isEnabled() && !aircraftCarrier.isEnabled() && auxPlay == 0){
            play.setEnabled(true);
            auxPlay = 1;
        }
    }
}