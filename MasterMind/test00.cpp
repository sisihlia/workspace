#include <iostream>
#include <random>
#include <vector>
#include <algorithm>
#include <iterator>
#include<stdio.h>
#include <stdlib.h>
using namespace std;

vector<int> list;
int *fooo(){
    int a = 0;
    a=a+100;
    return &a;
}
void boo(){
    int a = 7;

}/*
int main( ) {

	  int *p = foo();
	    //boo();
	    cout<<*p<<endl;
}

   std::vector<int> numbers { 11 , 88 , -5 , 13 , 4 , 13 , 77 , 2 } ;
   std::random_device seed ;
   // generator
   std::mt19937 engine( seed( ) ) ;
   // number distribution
   std::vector<int>::iterator it;

    it = find (numbers.begin(), numbers.end(), 13);
    if (it != numbers.end()){
    	int nPosition = distance (numbers.begin (), it);
      std::cout << "Element found in myvector: " << *it << " in position "<< nPosition<<'\n';}

    else{
      std::cout << "Element not found in myvector\n";}
  for (int x=0; x<4;x++){
	   std::uniform_int_distribution<int> choose( 0 , numbers.size( ) - 1 ) ;
	   int y = numbers[ choose( engine )];
	   list.push_back(y);
	   //std::cout << "random element picked : " << numbers[ choose( engine ) ]
   }           //  << " !\n" ;
   for (auto i = list.begin(); i != list.end(); ++i)
       std::cout << *i << ' ';

	std::vector<int> List;
	    List.push_back(100);
	    List.push_back(200);
	    List.push_back(300);
	    List.push_back(100);
	    int findValue = 100;

	    std::copy_if(List.begin(), List.end(), std::ostream_iterator<int>(std::cout, "\n"), [&](int v)
	    		{ return v == findValue;
	    });
	    std::vector<int> matches;
	    std::copy_if(List.begin(), List.end(), std::back_inserter(matches), [&](int v) {
	        if( v == findValue);
	        	return v;
	    });
	    for (auto i = matches.begin(); i != matches.end(); ++i)
	          std::cout << *i << ' ';

	    /*std::vector<std::list<int>::const_iterator> matches;
	    auto i = List.begin(), end = List.end();
	    while (i != end)
	    {
	      i = std::find(i, end, findValue);
	      if (i != end)
	        matches.push_back(i++);
	    }
	    //array<int, 10> arr =  { 3, 2, 5, 7, 3, 5, 6, 7 };

	    std::vector<int> results;
	    for (int i = 0; i < List.size(); ++i)
	    {
	        if (List[i] == 100)
	        {
	            results.push_back(i);
	        }
	    }
	    for (auto i = results.begin(); i != results.end(); ++i)
	   	          std::cout << *i << ' ';

	    return 0;


	    	char colors[4];

	    	srand(time(0));
	    	int randomint = (rand()%5)+1;

	    	for(int i=0;i<4;i++){
	    	randomint = (rand()%5)+1;

	    	 switch(randomint){
	    	 case 1:
	    		 colors[i] = 'R';
	    		 break;
	    	 case 2:
	    		 colors[i] = 'B';
	    		 break;
	    	 case 3:
	    		 colors[i] = 'Y';
	    		 break;
	    	 case 4:
	    		 colors[i] = 'P';
	    		 break;
	    	 case 5:
	    		 colors[i] = 'G';
	    		 break;

	    	 }
	    	}

	string colors[4]={"brown","black","green","orange"};
	string usercolors[4];

		cout << "We have our colors!" << endl;
		cout << endl << endl;
		int turncounter = 0;
		while(turncounter != 12){
			turncounter++;

			cout << "Current try: " << turncounter << endl;

			for(int i=0;i<4;i++){
				cout << "Color " << i << ": ";
				cin >> usercolors[i];
				cout << endl;
			}

			for(int i=0;i<4;i++){
				if(usercolors[i] == colors[i])
					cout << "R" << " ";
			}

			if(usercolors[0] == colors[1] ||
			   usercolors[0] == colors[2] ||
			   usercolors[0] == colors[3] ){
				   cout << "W" << " ";
			}
			   if(usercolors[1] == colors[0] ||
				  usercolors[1] == colors[2] ||
			       usercolors[1] == colors[3]){
					   cout << "W" << " ";
			   }
			   if(usercolors[2] == colors[0] ||
			   usercolors[2] == colors[1] ||
			   usercolors[2] == colors[3]){
				   cout << "W" << " ";
			   }
			   if(usercolors[3] == colors[0] ||
			   usercolors[3] == colors[1] ||
			   usercolors[3] == colors[2])
				{
					cout << "W" << " ";
				}

			cout << endl << endl;

			if(usercolors[0] == colors[0] &&
			   usercolors[1] == colors[1] &&
			   usercolors[2] == colors[2] &&
			   usercolors[3] == colors[3])
			{
				cout << "You win! Number of tries: " << turncounter << endl;
				turncounter = 12;
			}else{
				cout << "Nope." << endl << endl;
			}

		}
		if(turncounter == 12){
			cout << "You lost." << endl;
		}

		cin.get();
		cin.get();
		return 0;


	    	/*for (int k=0; k<position.size();k++){
			if(position[k]==i){
				std::cout << "Red"<< " k="<<k<<" i="<<i<<'\n';
				break;}
			else{
		      std::cout << "White"<< " k="<<k<<" i="<<i<<'\n';
				break;}*/
		/* std::vector<int> matches;
		 std::copy_if(position.begin(), position.end(), std::back_inserter(matches), [&](int v) {
			 return v == i;
			 	    });
		 if (matches.size()>0){cout <<"Red"<<"\n";
		 }else{ cout <<"White"<<"\n";*/



    //for (auto i = position.begin(); i != position.end(); ++i)
	  // 	          std::cout << *i << ' ';

	/*for (int i = 0; i<vCode.size();i++){
		//cout<<"Guess "<< vGuess[i]<< " position "<< i <<endl;
		for (int y=0; y<vGuess.size();y++){
			//cout<<"Code "<< vCode[y]<< "position "<< y <<endl;
			if (vCode[i]==vGuess[y]){
				if (i==y){
					cout << "Red"<< " ";
					break;}
				else if (i!=y){
					cout <<"White"<< " ";
					break;}
			}
		}*/
		//cout << "value " <<value <<" index "<<index<<endl;
	/*if (value == 1 && index == 1){
			hint.push_back("red");
	}else if(value == 1 && index == 0){
			hint.push_back("white");
	}else if(value == 0 && index == 1){
			hint.push_back("white");
	}else{
			hint.push_back("blank");
	}

	//cout << hint.size()<<endl;
	//for (auto i = hint.begin(); i != hint.end(); ++i)
		//		       std::cout << *i << ' ';
	//cout<< value <<endl;
	//return hint;
	typedef enum {RED,GREEN,BLUE,YELLOW,ORANGE,BLACK} color;
	std::cout << "Welcome to ... M A S T E R M I N D.\n";

		// there are 6 colors
		std::vector<color> all_colors(6);
		for (int i = 0; i < 6; ++i)
			all_colors[i] = color(i);

		// initialize random seed and shuffle all_colors
		srand(time(NULL));
		std::random_shuffle(all_colors.begin(), all_colors.end());

		// fill real_code with the four first colors
		//std::vector<color> real_code(all_colors.begin(), all_colors.begin()+4);

		vector<string> real_code = {"brown", "black","green", "orange"};
		for (int i = 0; i < 5; ++i) {
			std::vector<int> current_try(4);
			int black_hits = 0, white_hits = 0;

			for (int j = 0; j < 4; ++j)
				std::cin >> current_try[j];

			for (int j = 0; j < 4; ++j) {
				if (color(current_try[j]) == real_code[j]) {
					++black_hits;
					continue;
				}

				for (int k = 0; k < 4; ++k)
					if (color(current_try[j]) == real_code[k])
						++white_hits;
			}

			std::cout << black_hits << " black hits & " << white_hits
					  << " white hits.\n";

			if (black_hits == 4) {
				std::cout << "you won!\n";
				return 0;
			}
		}

		std::cout << "The real code was ";

		for (int i = 0; i < 4; ++i)
			std::cout << real_code[i] << ' ';

		std::cout << std::endl;

}*/


