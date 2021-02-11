#include <iostream>
using namespace std;
#include <cstdlib>
#include <ctime>
#include <tgmath.h>

int main()
{
//1
// int k;
// int n;

// cin >> n >> k;

// int sum = 1;

// int i = 0;
// while (i <= n){
//     sum += pow(i-2,2) + k*i;
//     i++;
//     cout << sum << "\n"; 
// }

//2
int arr[8] = {};

int i = 0;
bool notInRange = true;
while(i < 8){
    notInRange = true;
    int buffer;
    cin >> buffer;
    while(notInRange){
        if(buffer < 22 || buffer > 50){
        cout << "Vuvedete novo chislo mejdu 22 i 50" << "\n";
        cin >> buffer;
        }
        else {
            notInRange = false;
        }
    }  
    arr[i] = buffer;
    i++;
}

// cout << "Vuvedete broi na elementi da budat otpechatani" << "\n";
// int n;
// cin >> n;
// i = 0;
// while(i < n){
//     cout << arr[i] << "\n";
//     i++;
// }

    // int j = 2;
    // int closestElement = arr[1];
    // int sumHolder = arr[0] - arr[1];
    // while(j < 8){
    //     int sumCheck = arr[0] - arr[j];
    //     if(abs(sumCheck) < abs(sumHolder)){
    //         sumHolder = sumCheck;
    //         closestElement = arr[j];
    //     }

    //     j++;
    // }
    // cout << "Closest element is: " << closestElement << "\n";

    int j = 0;
    while(j<8){
        if(arr[j] % 2 == 0){
            arr[j] = j + arr[j] + 8;
            cout << arr[j] << endl;
        }
        else{
            arr[j] = arr[j]*2 - 3;
            cout << arr[j] << endl;
        }
        j++;
    }



//3

// cout << "Vuvedete 4 cifreno chislo:";
// int num;
// cin >> num;

// bool notRightFormat = true;
// while (notRightFormat){
//     if(num/1000 <= 0){
//         cout << "Vuvedete 4 cifreno chislo:";
//         int buffer;
//         num = 0;
//         cin >> buffer;
//         num += buffer;

//     }
//     else {
//         notRightFormat = false;
//     }
 
// }

// int i = 2;
// bool isItPrime = true;
// while ( i < 10){
//     if(num%i == 0){
//         isItPrime = false;       
//         break;       
//     } 

//     i++;
// }

// if(isItPrime){
//     cout << "Chisloto e prosto" << "\n";
// }
// else {
//     cout<< "Chisloto ne e prosto" << "\n";
// }

// int j = 2;

// while (j < 10){
//     int sum = 0;    
//     int buffer = num;

//     while (buffer > 0){
    
//      if(buffer%j == 0){
//         buffer=buffer/j;
//            sum++;
           
//      }
//      else {
         
//          break;
//      }
     
//     }
//     cout << j << ": " << sum << "\n";

//     j++;
// }

// return 0;



}
    

