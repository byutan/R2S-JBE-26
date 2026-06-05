#include <stdio.h>
#include <math.h>
/*
Prompt the user to enter the coordinates (x1, y1) and (x2, y2) of the two points, 
calculate the distance using the distance formula distance = sqrt((x2 - x1)^2 + (y2 - 
y1)^2), and then display the result.
1. declare variable x1, x2, y1, y2, distance (float)
2. input value: x1, x2, y1, y2
3. calculate distance 
4. print distance
*/
int main() {
    float x1,x2,y1,y2;
    printf("Enter first coordinate (x1,y1): ");
    scanf("%f%f", &x1, &y1);
    printf("Enter second coordinate (x2,y2): ");
    scanf("%f%f", &x2, &y2);
    float distance= sqrt(pow((x2-x1),2)+pow((y2-y1),2));
    printf("Distance between 2 coordinates: %f", distance);
    return 0;

}