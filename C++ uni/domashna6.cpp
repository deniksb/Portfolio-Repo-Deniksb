#include <iostream>
using namespace std;
#include <cstdlib>
#include <ctime>

int main()
{
    //1
    // int n;
    // int m;
    // cin >> n >> m;
    // srand((unsigned int)time(NULL));

    // int **darr = new int *[n];

    // int i = 0;

    // while (i < n)
    // {
    //     darr[i] = new int[m];
    //     i++;
    // }

    // for (int j = 0; j < n; j++)
    // {
    //     for (int y = 0; y < m; y++)
    //     {

    //         *(darr[j] + y) = -12 + (rand() % static_cast<int>(122 - (-12) + 1));
    //     }
    // }

    // for (int j = 0; j < n; j++)
    // {
    //     for (int y = 0; y < m; y++)
    //     {

    //         cout << *(darr[j] + y) << "\n";
    //     }
    // }

    // return 0;

    //2
    //   int n;
    // int m;
    // cin >> n >> m;
    // srand((unsigned int)time(NULL));

    // int **darr = new int *[n];

    // int i = 0;

    // while (i < n)
    // {
    //     darr[i] = new int[m];
    //     i++;
    // }

    // for (int j = 0; j < n; j++)
    // {
    //     for (int y = 0; y < m; y++)
    //     {

    //         *(darr[j] + y) = -12 + (rand() % static_cast<int>(122 - (-12) + 1));
    //     }
    // }

    // int sub = 1; //promenliva koqto se premahva ot m za da se izchislqt broq na nulite
    // for (int j = 0; j < n; j++)
    // {
    //     for (int y = 0; y < m; y++)
    //     {
    //         if(y < m-sub && j < n-1){
    //             cout << 0 << "\t";
    //         }
    //         else {
    //          cout << *(darr[j] + y) << "\t";   
    //         }
            
    //     }
    //     sub++;
    //     cout << "\n";
    // }

    // return 0;

    //3
    int n;
    int m;
    cin >> n >> m;
    srand((unsigned int)time(NULL));

    int **darr = new int *[n];

    int i = 0;

    while (i < n)
    {
        darr[i] = new int[m];
        i++;
    }

    for (int j = 0; j < n; j++)
    {
        for (int y = 0; y < m; y++)
        {

            *(darr[j] + y) = -12 + (rand() % static_cast<int>(122 - (-12) + 1));
        }
    }

    int sub = 1; //promenliva koqto se premahva ot m za da se izchislqt broq na nulite
    for (int j = 0; j < n; j++)
    {
        for (int y = 0; y < m; y++)
        {            
            if(j==sub-1 && y <= sub-1 ){
                cout << 0 << "\t"; // DA PRINTNA SBORA OT INDEXITE POVDIGNAT NA STEPEN NE ZNAMA SI KVO SI 
                          
            }
            else {
             cout << *(darr[j] + y) << "\t";   
            }
            
        }
        sub++;
        cout << "\n";
    }

    return 0;


}