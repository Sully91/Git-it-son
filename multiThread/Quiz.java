package com.company;

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


public class Quiz
{
    public static void main(String []args) throws ParserConfigurationException, IOException, SAXException {
        File inputFile = new File("QuizQuestions.xml");  //Declare the input file
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();  //Build Database
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  //Create new document based upon the database
        Document doc = dBuilder.parse(inputFile);  //Parse the input file
        doc.getDocumentElement().normalize();      //Read the root record
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());  //get the first element
        NodeList nList = doc.getElementsByTagName("Question");

         Node questionOne = nList.item(0);

            Element eElement = (Element) questionOne;

            System.out.println("Question: "
                    + eElement.getElementsByTagName("text")
                    .item(0)
                    .getTextContent());


            System.out.println("Answers: "
                    + eElement
                    .getElementsByTagName("answers")
                    .item(0)
                    .getTextContent());



            int c = 0;

            Scanner scan = new Scanner ( System.in );

            String in;
            in = scan.nextLine();


            if(in.equals("A") || in.equals("a"))
            {
                System.out.println("Correct\n");
                c++;
            }

            else
            {
                System.out.println("Wrong\n");
            }


        Node questionTwo = nList.item(1);

        Element eElementTwo = (Element) questionTwo;

        System.out.println("Question: "
                + eElementTwo.getElementsByTagName("text")
                .item(0)
                .getTextContent());


        System.out.println("Answers: "
                + eElementTwo
                .getElementsByTagName("answers")
                .item(0)
                .getTextContent());

        in=scan.nextLine();

                if(in.equals("B") || in.equals("b"))
        {
            System.out.println("Correct\n");
            c++;
        }

        else
        {
            System.out.println("Wrong\n");
        }

        Node questionThree = nList.item(2);

        Element eElementThree = (Element) questionThree;

        System.out.println("Question: "
                + eElementThree.getElementsByTagName("text")
                .item(0)
                .getTextContent());


        System.out.println("Answers: "
                + eElementThree
                .getElementsByTagName("answers")
                .item(0)
                .getTextContent());

        in=scan.nextLine();

        if(in.equals("D") || in.equals("d"))
        {
            System.out.println("Correct\n");
            c++;
        }

        else
        {
            System.out.println("Wrong\n");
        }

        Node questionFour = nList.item(3);

        Element eElementFour = (Element) questionFour;

        System.out.println("Question: "
                + eElementFour.getElementsByTagName("text")
                .item(0)
                .getTextContent());


        System.out.println("Answers: "
                + eElementFour
                .getElementsByTagName("answers")
                .item(0)
                .getTextContent());

        in=scan.nextLine();

        if(in.equals("c") || in.equals("c"))
        {

            if(c == 3){
                System.out.println("You have completed the Quiz with full marks!");
            }
        }

        else
        {
            int finalScore = c;
            System.out.println("You have failed the quiz with a score of " + finalScore + " out of 4.");
        }

    }
}