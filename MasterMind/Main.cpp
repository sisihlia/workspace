#include <iostream>
#include <sstream>
#include <vector>
#include <random>
#include <algorithm>
#include <string>
#include <iterator>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

vector<string> availableColors = {"blue","green","red","yellow","black","white"};
vector<string> colorCode; // store the color code
vector<string> colorGuess;// store the color guess
vector<string> hints;//red means correct color correct position, white means correct color wrong position

string input; // as dummy for user input
int codeLength = 4;
int counter=0;// turn counter
int dummy;

void intro(){
	cout << "Welcome to MasterMind"<<endl;
	cout << "Available  6 Colors : Blue, Green, Red, Yellow, Black, White" <<endl;
	cout << "You have 10 times to guess the color code"<<endl;
	cout << "Please enter to continue..." <<endl;
	cin.ignore();
}

vector<string> getColorGuess(){
	colorGuess.clear();// make sure no previous colors stored
	for (int i=0; i< codeLength; i++){
		cout << "Color "<< i<< "=";
		cin >> input;
		colorGuess.push_back(input);// store into vector
	}
	return colorGuess;
}

vector<string> getColorCode(){
	   std::random_device seed ;
	   // generator
	   std::mt19937 engine( seed( ) ) ;
	   // number distribution
	   for (int x=0; x<codeLength;x++){
		   std::uniform_int_distribution<int> choose( 0 , availableColors.size( ) - 1 ) ;
		   string y = availableColors[ choose( engine )];
		   colorCode.push_back(y);
	   }
	   return colorCode;
}

int verifyGuess(vector<string>vCode,vector<string> vGuess){
	int redPins=0;// increase when red hint comes out
	int whitePins=0;//increase when white hint comes out

	for (int i=0; i<vCode.size();i++){
		for (int y=0; y<vGuess.size();y++){
			if(vCode[i]==vGuess[i]){
				redPins++;
				break;}
			else if(vCode[i]==vGuess[y]){
				whitePins++;
				break;
			}
		}
	}
	cout<< "RedPins="<<redPins<<" "<<"WhitePins="<<whitePins<<"\n";
	dummy=redPins;// store the redPins in order to be accessed from outside;
	return dummy;
}

int main(){
	int times= 10;// max number user gives input
	vector<string>guess;
	intro();
	int i=0;
	vector<string>code = getColorCode(); //vector<string>vCode = {"br", "bl","g", "o"};

	while(i<times){
		cout<<"\nCounter:"<< i<<endl;//counter checked
		guess=getColorGuess();
		int d=verifyGuess(code,guess);//get redPins
		if(d==4){cout<<"Congratulation. You win"<<"\n";break;}// verify the number of redPins
		i++;
	}
	if(i==times){ // when times is reached out
	cout<<"\n"<<"You lost. The color code is"<<"\n";
	for (auto i = code.begin(); i != code.end(); ++i)
		   	          std::cout << *i << ' ';
	}
	return 0;
}

