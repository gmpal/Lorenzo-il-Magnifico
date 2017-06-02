package it.polimi.ingsw.GC_24.network.multi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;



//There's a ServerSocketView for each Client --> SERVER SIDE
//It's this class that controls the communication from SERVER to CLIENT 
public class ServerSocketView extends MyObservable implements MyObserver, Runnable{

	private Socket socket;
	private ObjectOutputStream objToClient;
	private ObjectInputStream objFromClient;
	private boolean end;
	
	
	//constructor --> Receive a socket and creates Scanner and PrintWriter
	public ServerSocketView(Socket socket) throws IOException{
		
		this.socket=socket;
		
		objToClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		objFromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		end = false;
	}
	
	
/**This methods keeps receiving Objects from the socket Input Stream 
 * and handling them, giving a response. If the request contains "EXIT" 
 * the connection is closed*/
	@Override
	public void run() {
		
		       
        while (!end){
            try{
                Map<String,Object> request = (Map<String, Object>) objFromClient.readObject();
                
                Map<String, Object> response = this.handleRequestFromClient(request);
                objToClient.writeObject(response);
                objToClient.flush();
                end = request.containsKey("EXIT");
                
            }catch (IOException | ClassNotFoundException ioe){
                end = true;
            }
            
            System.out.println("SERVER: Socket connection closed");
            
            try {
				objToClient.close();
	            objFromClient.close();
	            socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

         	}
		
	}

	

	/**This method analyzes the incoming HashMap. If it finds specific keywords
	 * in the keySet, it does different things with different objects*/
	private Map<String, Object> handleRequestFromClient(Map<String, Object> request) {
		Set<String> command = request.keySet();
		
		/**If commands contains "player" than the corresponding object is a string with a player
		 * name and a player colour --> used to create the Player*/
		if (command.contains("TEST")){
			SetOfValues setofvalues = (SetOfValues) request.get("TEST");
			setofvalues.getCoins().addQuantity(5);;
			request.put("TEST", setofvalues);
			
			return request;}
		/*if (command.contains("player")){
           Player player = tokenizeFromPLayer((String) request.get("player"));
		}else if (command.contains("leave")){
            User user = (User) request.get("leave");
            try {
                this.leave(user);
                return user.getRepr() + " left the chat";
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (command.contains("send")){
            Message msg = (Message) request.get("send");
            try {
                this.send(msg);
                return msg.toString();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (command.contains("BYE")){
            return "User left the chat";
        }
       return "bad command";*/
		else return null;
	}
	


   /**Creates a new Player from a string containing his name and his colour*/
	public Player tokenizeFromPLayer(String string) {
		StringTokenizer tokenizer = new StringTokenizer(string);
		Player player = new Player(
						(String) tokenizer.nextToken(),
						PlayerColour.valueOf(tokenizer.nextToken()));
		return player;		
	}



	@Override
	public void update() {
		System.out.println("ServerView here, i have been notified by the Model --> it's changed!");
		
	}



	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		// TODO Auto-generated method stub
		
	}

}

