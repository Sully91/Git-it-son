package multiThreadPkg;

        /*
         * Multi-Threaded Client-Server Example
         *
         * Date: 22 Nov 2019
         *
         * This is the Server side
         *
         */
//Java implementation of Server side
//It contains two classes : Server and ClientHandler
//Save file as Server.java

        import java.io.*;
        import java.text.*;
        import java.util.*;
        import java.net.*;
//
//Server class
//
//


        import java.io.IOException;
        import java.util.Scanner;
        import java.io.File;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.ParserConfigurationException;

        import org.w3c.dom.Document;
        import org.w3c.dom.NodeList;
        import org.w3c.dom.Node;
        import org.w3c.dom.Element;
        import org.xml.sax.SAXException;

        import java.util.Timer;
        import java.util.TimerTask;



public class Server
{   // There are no class variables declared
    //
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056 as previous example port changed from 3142

        ServerSocket ss = new ServerSocket(5056);

        // running infinite loop to wait for client request
        // Request will be first and last name
        //
        while (true) //infinite while loop
        {
            Socket s = null; //Declare a variable s of type socket and set it to null

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object

                Thread t = new ClientHandler(s, dis, dos); //declare a new thread t of type ClientHandler

                // Invoking the start() method
                t.start(); //Start the client handler

            } // End try part
            catch (Exception e){
                s.close();
                e.printStackTrace();
            } // End catch
        } // End while
    } // End Main
} // End Server Class

//ClientHandler class
class ClientHandler extends Thread
{

    final DataInputStream dis; //Declare dis as DataInputStream
    final DataOutputStream dos; //Declare dos as DataOutputStream
    final Socket s; //Declare s as a Socket

    boolean shouldKeepRunning = true;



    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) throws ParserConfigurationException, IOException, SAXException {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }



    @Override
    public void run()
    {

        if (shouldKeepRunning)
        {
            try {

                // Declare variable inputs for questions
                String received;
                String receivedTwo;
                String receivedThree;
                String receivedFour;
                String firstName;
                String lastName;

                String toreturn;

                // Parse through the XML File
                File inputFile = new File("QuizQuestions.xml");  //Declare the input file
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();  //Build Database
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  //Create new document based upon the database
                Document doc = dBuilder.parse(inputFile);  //Parse the input file
                doc.getDocumentElement().normalize();      //Read the root record
                NodeList nList = doc.getElementsByTagName("Question");

                // Grab the right question from the file. Question one
                Node questionOne = nList.item(0);
                Element eElement = (Element) questionOne;
                // Create counter for score
                int c = 0;

                // Client enters information
                dos.writeUTF("Type in your first name. \n");

                firstName = dis.readUTF();

                if(firstName.length() > 0){
                    dos.writeUTF("\nThanks \n");

                }

                dos.writeUTF("Type in your last name. \n");

                lastName = dis.readUTF();

                if(lastName.length() > 0){
                    dos.writeUTF("\nThanks \n");
                }




                // Greets user + gives the user the question
                dos.writeUTF("\n \nHey! Welcome to the quiz " + firstName + "! \n \n \nQuestion: "
                        + eElement.getElementsByTagName("text")
                        .item(0)
                        .getTextContent() + "\nAnswers " + eElement
                        .getElementsByTagName("answers")
                        .item(0)
                        .getTextContent());


                // receive the answer from client
                received = dis.readUTF();


                // write on output stream based on the
                // answer from the client
                if(received.equals("A") || received.equals("a"))
                {
                    dos.writeUTF("Correct\n");
                    c++;
                }

                else
                {
                    dos.writeUTF("Incorrect\n");
                }


                // Grab the right question from the file. Question one
                Node questionTwo = nList.item(1);

                Element eElementTwo = (Element) questionTwo;

                // give the user the question
                dos.writeUTF("Question: "
                        + eElementTwo.getElementsByTagName("text")
                        .item(0)
                        .getTextContent() + "\nAnswers " + eElementTwo
                        .getElementsByTagName("answers")
                        .item(0)
                        .getTextContent());



                 receivedTwo = dis.readUTF();

                if(receivedTwo.equals("B") || receivedTwo.equals("b"))
                {
                    dos.writeUTF("Correct\n");
                    c++;
                }

                else
                {
                    dos.writeUTF("Incorrect\n");
                }

                // Grab the right question from the file. Question one
                Node questionThree = nList.item(2);

                Element eElementThree = (Element) questionThree;

                // give the user the question
                dos.writeUTF("Question: "
                        + eElementThree.getElementsByTagName("text")
                        .item(0)
                        .getTextContent() + "\nAnswers " + eElementThree
                        .getElementsByTagName("answers")
                        .item(0)
                        .getTextContent());



                 receivedThree = dis.readUTF();

                if(receivedThree.equals("D") || receivedThree.equals("d"))
                {
                    dos.writeUTF("Correct\n");
                    c++;
                }

                else
                {
                    dos.writeUTF("Incorrect\n");
                }


                // Grab the right question from the file. Question one
                Node questionFour = nList.item(1);

                Element eElementFour = (Element) questionFour;

                // give the user the question
                dos.writeUTF("Question: "
                        + eElementFour.getElementsByTagName("text")
                        .item(0)
                        .getTextContent() + "\nAnswers " + eElementFour
                        .getElementsByTagName("answers")
                        .item(0)
                        .getTextContent());



                 receivedFour = dis.readUTF();

                if(receivedFour.equals("C") || receivedFour.equals("c")) {
                    if(c == 3){
                        dos.writeUTF("You have completed the Quiz with full marks! \n");
                        shouldKeepRunning = false;
                    } else  {
                        int finalScore = c;
                        dos.writeUTF("You have failed the quiz with a score of " + finalScore + " out of 4. \n");
                        shouldKeepRunning = false;

                    }

                }
                else
                {
                    int finalScore = c;
                    dos.writeUTF("You have failed the quiz with a score of " + finalScore + " out of 4. \n");
                    shouldKeepRunning = false;


                }


            } catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        }

        if (!shouldKeepRunning)
        {

            // give the user the question
            try {

                dos.writeUTF("Thanks for taking the test! Test will now close.");

                dos.writeUTF("Client " + this.s + " sends exit...");
                dos.writeUTF("Closing this connection.");
                this.s.close();
                System.out.println("Connection Close");


            } catch (IOException e) {
                e.printStackTrace();
            }



        }


        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

