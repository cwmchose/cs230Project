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
    public ListModel questionPoolD = new DefaultListModel();
    public ListModel questionPoolC = new DefaultListModel();
    public ListModel pSelects = new DefaultListModel();  
    public ListModel dSelects = new DefaultListModel();
    public ListModel iSelects = new DefaultListModel();
   
    Server(){
        
    }

    public ListModel getList(String s){
        if(s.equals("dean"))
            return dSelects;
        else if(s.equals("provost"))
            return pSelects;
        else if  (s.equals("instructor"))
            return iSelects;
        else if(s.equals("cpool"))
            return questionPoolC;
        else if(s.equals("pool"))
            return questionPoolD;
        else 
            return null;
    }
      
    public void setList(ListModel m, String s){
        if(s.equals("dean"))
            dSelects = m;
        else if(s.equals("provost"))
            pSelects = m;
        else if  (s.equals("instructor"))
            iSelects = m;
        else if(s.equals("cpool"))
            questionPoolC = m;
        else if(s.equals("pool"))
            questionPoolD = m;
        else 
            return;
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
