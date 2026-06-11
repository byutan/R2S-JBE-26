#include <stdio.h>
#include <math.h>
/*Prompt the user to enter the lengths of the three sides, calculate the semi-perimeter (s) 
using the formula s = (a + b + c) / 2, and then calculate the area using Heron's formula 
area = sqrt(s * (s - a) * (s - b) * (s - c)), where a, b, and c are the lengths of the sides. 
1. declare variable side1, side2, side3, semi_peri, area
2. input value into side1, side2, side3
3. calculate semi_peri
4. calculate area
5. print area
*/
int main() {
    float side1, side2, side3;
    
    printf("Enter length of triangle sides,\n");
    
    printf("Side 1: ");
    scanf("%f", &side1);
    
    printf("Side 2: ");
    scanf("%f", &side2);
    
    printf("Side 3: ");
    scanf("%f", &side3);
    
    float semi_peri = (side1 + side2 + side3)/2;
    float area = sqrt(semi_peri*(semi_peri-side1)*(semi_peri-side2)*(semi_peri-side3));
    printf("Area: %f", area);
    
    return 0;
}