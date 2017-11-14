/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author asaas
 */
public class Server extends Thread{
    
    Boolean running = true;
    ArrayList<ClientHandler> clientList = new ArrayList();
    public static ArrayList<String> questions = new ArrayList<String>();
    public ArrayList<String> SEIqs = new ArrayList<String>();
    
   
    Server(){
        
    }
    public void addQuestions(){
        questions.add("Question0:");
        questions.add("Question1");
        questions.add("Question2");
        questions.add("Question3");
        questions.add("Question4");
        questions.add("Question5");
        questions.add("Question6");
        questions.add("Question7");
        questions.add("Question8");
        questions.add("Question9");
        questions.add("Question10");
        questions.add("Question11");
        questions.add("Question12");
        
    }
    
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
