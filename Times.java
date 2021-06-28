import java.io.*;

public class Times{
	
    //metodo que salva o tempo e name do player passado por parametro
	public void saveList(Player player){
        try{

            //contacate o tempo e name do player no final do arquivo, ou cria um new arquivo caso nao exta
            Writer file = new BufferedWriter(new FileWriter("ranking.txt" ,true));
            file.append(player.getSetTime()+" seconds   -   "+player.getName()+"\n");
            file.close();
        }catch(Exception ex){

            //mensagem caso tenha dado algum erro
            System.out.println("Error.");
        }
	}
}