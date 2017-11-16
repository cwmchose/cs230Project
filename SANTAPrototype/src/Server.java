/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
/**
 *
 * @author asaas
 */
public class Server extends Thread{
    
    Boolean running = true;
    ArrayList<ClientHandler> clientList = new ArrayList();
    public static ArrayList<String> questions = new ArrayList<String>();
    public ArrayList<String> SEIqs = new ArrayList<String>();
    public ListModel questionPool = new DefaultListModel();
   
    Server(){
        
    }
<<<<<<< HEAD
    public void addQuestions(){
   
    }
=======
 
>>>>>>> 6acc01622d5764828d4bf7caf2779aef271b37bc
    
    public void addToSEI(int i){
        SEIqs.add(questions.get(i));
    }
    
    
    @Override
    public void run(){
        System.out.println("Starting Server");

        try{
            ServerSocket ss = new ServerSocket(32345);
            while(running){
                Socket clientSocket = ss.accept();
                ClientHandler ch = new ClientHandler(this, clientSocket);
                ch.start();
                clientList.add(ch);
            }
        }catch(IOException e){
            System.out.println("Server Error");
            e.printStackTrace();
        }
        
        System.out.println("Server Stopped");
    }
    
    public static void main(String [] args){
        new Server().run();
    }
}
