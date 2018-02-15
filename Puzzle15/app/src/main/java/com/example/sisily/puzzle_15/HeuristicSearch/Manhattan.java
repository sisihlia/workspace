package com.example.sisily.puzzle_15.HeuristicSearch;

/**
 * Created by sisily on 04/02/18.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Manhattan {
        /*
         Breadth first search algorithm with manhattan heuristic
         * declare priorityQueue
         * add root node to our priorityQueue
         * while priorityQueue not empty do following loops:
         * a. retrieve then remove first node of our openlist
         * b. check status of retrieved node
         *      if it is the goal node then break loop and print solution
         *      if it is not goal node then:
         *      - expand retrieved node
         *      - evaluate using manhattan heuristik
         *      - add to priorityQueue
         *      - continue loop
         */

         /* Board positions index (because we use string)
         * 0 1 2
         * 3 4 5
         * 6 7 8
         *
         * if str = "135782460" then it correspondent to
         * 1 3 5
         * 7 8 2
         * 4 6 0
         *
         * with 0 as blank space
         *
         */

    String str = ""; // initial state
    String goal = ""; //goal state


    PriorityQueue <StateOrder> queue;


    Map<String,Integer> levelDepth;


    Map<String,String> stateHistory;

    int nodes = 0; //counter for node generation
    int limit = 150; //counter for limit
    int unique = -1;//counter for uniq state
    int newValue; //counter depth limit
    int a; //position of blank
    int h; //heuristic

    String currState;
    boolean solution = false;
    List<String> movements = new ArrayList<>();


  public Manhattan(String str, String goal){
        queue = new PriorityQueue <StateOrder> ();
        levelDepth = new HashMap<String, Integer>();
        stateHistory = new HashMap<String,String>();
        this.str = str;
        this.goal = goal;
        addToQueue(str,null);
    }

    public String findSolution (){
        String move="hyuj";
        while (!queue.isEmpty()){


            currState = queue.poll().toString();//RETRIEVE then remove first node

            if (currState.equals(goal)){ // check if current state is goal state
                solution = true;
                printSolution(currState);// print solutions
                break;
            }


            if (levelDepth.get(currState) == limit){//check if under limit
                solution = false;
                printSolution(currState);// print solutions
                break;
            }


            else {
                //expand currentstate then add expanded node to the of openlist

                a = currState.indexOf("0");// get index position of 0 (blank)


                //left
                while (a != 0 && a != 3 && a != 6){// if blank not in the left most column then it able move left
                    String nextState = currState.substring(0,a-1)+"0"+currState.charAt(a-1)+currState.substring(a+1);//swap blank with destination
                    addToQueue(nextState, currState);//add expanded node to openlist
                    nodes++;

                    move = "L";

                    //System.out.println ("move to " +move + " at " + levelDepth.get(currState));
                    movements.add("0 to left");
                    break;
                }

                //up
                while (a!=0 && a!=1 && a!=2){//if blank not in the very top of row then it able to move up
                    String nextState = currState.substring(0,a-3)+"0"+currState.substring(a-2,a)+currState.charAt(a-3)+currState.substring(a+1);//swap blank with destination
                    addToQueue(nextState, currState);//add expanded node to openlist
                    nodes++; //nodes = nodes + 1; a node is being genereted add it to counter
                   move = "U";
                   // System.out.println ("move to " +move + " at " + levelDepth.get(currState));
                     movements.add("0 to up");
                    break;
                }

                //right
                while(a != 2 && a != 5 && a != 8){// if blank not in the right most column then it able to move right
                    String nextState = currState.substring(0,a)+currState.charAt(a+1)+"0"+currState.substring(a+2);//swap blank with destination
                    addToQueue(nextState, currState);//add expanded node to openlist
                    nodes++;
                    move = "R";
                    //System.out.println ("move to " +move + " at " + levelDepth.get(currState));
                    movements.add("0 to right");
                    break;
                }

                //down
                while (a != 6 && a != 7 && a != 8) {// if blank not in the very bottom row then it able to move down
                    String nextState = currState.substring(0,a)+currState.substring(a+3,a+4)+currState.substring(a+1,a+3)+"0"+currState.substring(a+4);//swap blank with destination
                    addToQueue(nextState, currState);//add expanded node to openlist
                    nodes++;
                    move = "D";
                   // System.out.println ("move to " +move + " at " + levelDepth.get(currState));
                     movements.add("0 to down");
                    break;
                }

            }
            synchronized (this) {
                String traceState = currState;
                System.out.println("move to " + move + " at " + levelDepth.get(traceState));
            }
        }

        if (solution){
            System.out.println("Solution Exist");
            System.out.println("Solution found is " +currState + " in " + levelDepth.get(currState)+" step(s)");
        }

        else {
            System.out.println("State unsolved  " +currState+ " in " + levelDepth.get(currState)+" step(s)");
            System.out.println("Solution not yet found! My suggestion are:");
            System.out.println("1. Try to increse level depth limit ");
            System.out.println("2. Use other heuristc ");
            System.out.println("3. Maybe it is physically impossible");
        }

        return currState;
    }

    public void addToQueue (String newState, String oldState){
        if(!levelDepth.containsKey(newState)){// check repeated state
            newValue = oldState == null ? 0 : levelDepth.get(oldState) + 1;
            unique ++;
            levelDepth.put(newState, newValue);
            h = calcManhattan(newState,goal); // calculate heuristic from newstate
            //h= 0;
            queue.add(new StateOrder(h,newState));//add to priority queue
            stateHistory.put(newState, oldState);
        }

    }

    public List<String> getMovements(){
        return movements;
    }

    public int calcManhattan(String currState, String goalState){
        //lookup table for manhattan distance
        int [][] manValue = {
                {0,1,2,1,2,3,2,3,4},
                {1,0,1,2,1,2,3,2,3},
                {2,1,0,3,2,1,4,3,2},
                {1,2,3,0,1,2,1,2,3},
                {2,1,2,1,0,1,2,1,2},
                {3,2,1,2,1,0,3,2,1},
                {2,3,4,1,2,3,0,1,2},
                {3,2,3,2,1,2,1,0,1},
                {4,3,2,3,2,1,2,1,0},
        };
        //calculate manhattan distance
        int heu = 0 ;
        int result = 0;
        //String a = null;
        for (int i=1; i<9;i++){
            heu = manValue[currState.indexOf(String.valueOf(i))][goalState.indexOf(String.valueOf(i))];
            result = result + heu;

        }
        return result;
    }

    public void printSolution (String currState){
        if (solution){
            System.out.println("Solution found in " +levelDepth.get(currState)+" step(s)");
            System.out.println("Node generated: "+ nodes);
            System.out.println("Unique Node generated: "+ unique);
        }
        else {
            System.out.println("Solution not found!");
            System.out.println("Depth Limit Reached!");
            System.out.println("Node generated: "+ nodes);
            System.out.println("Unique Node generated: "+ unique);
        }

        String traceState = currState;

        while (traceState != null) {
            System.out.println(traceState + " at " + levelDepth.get(traceState));
            try{
                for(int z=0;z<9;z++){
                    System.out.print(" " + String.valueOf(traceState.charAt(z)) + " ");
                    if ((z+1) % 3 == 0){System.out.println();}
                }
            }
            catch (NullPointerException e) {}
            traceState = stateHistory.get(traceState);
        }
        //System.exit(0); //break
    }

    public static void main(String[] args) {




    }

}
