#include <iostream>
using namespace std;
#include <cstdlib>
#include <ctime>
#include <tgmath.h>
const int M = 10;
int main()
{ 
//2
// int arr[M] = {};

// //a
// int i = 0;
// srand((unsigned int)time(NULL));
// while(i < M){
// int random =  -635 + ( rand() % ( 921 - (-635) + 1 ) );
// arr[i] = random;
// i++;
// }
// //b
// int j = 0;
// while(j < M){
//     cout << "array/" << j << "/=" << arr[j];
//     if(j != M-1){
//         cout << "~";
//     }
//     j++;
// }
// //c
// //max stoinost
// int max = arr[0];
// for(int i = 1;i < M;i++){
//     if(arr[i]>max){
//         max = arr[i];
//     }
// }

// int y = 0;
// while (y < M){
//     if(arr[y] > 0 && arr[y]%2 == 0){

//          arr[y] = arr[y]*arr[y];  
        
//     }
//     else {
//         arr[y] = max;
//     }
//     y++;
// }

// int g = 0;
// cout << endl << "NOVI STOINOSTI" << endl;
// while(g < M){
    
//     cout << "array/" << g << "/=" << arr[g];
//     if(g != M-1){
//         cout << "~";
//     }
//     g++;
// }

//3
int num;
while(true){
cout << "Vuvedete mnogocifreno cqlo shislo" << endl;
cin >> num;
int buffer = num;
if(buffer/10 ==0){ //proverka dali e mnogocifreno
continue;  
}
else {
    break;
}
}

int digits[10] = {};
int buffer = num;
int remainder;
int i = 0;

while(buffer >= 10){
remainder = buffer % 10;
buffer = buffer / 10;
digits[i] = remainder;
i++;
}
digits[i] = buffer;
i++;

int length = i;
int occurences[10] = {};
for(int y = 0;y < i;y++){
    int result = 0;
    for(int j=0;j<i;j++){
        if(digits[y]==digits[j]){           
                result++;           
        }        
    }
    occurences[y] = result;
}
// for(int j = 0;j < i;j++){
//     cout << occurences[j] << endl;
// }

//comparing occurences
int max = occurences[0];
int indexOfNumber;
int y = i-1;
while (y>=0){
if(max < occurences[y]){
    max = occurences[y];
    indexOfNumber = y;
}
y--;
}

cout << endl << num << endl << digits[indexOfNumber] << " : " << max << " times";


return 0;
}