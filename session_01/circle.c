#include <stdio.h>
#include <math.h>
/*
find the area and perimeter of a circle;
1. declare variable radius, permiter, area(float)
2. input value radius
3. calculated: perimeter, area
4. print result: permeter, area
*/
int main() {    
    float radius; // data type: choose the minimum type for memory effiency
    const float PI = 3.14; //  const type: maintainable, solve magic number problem (unknown meaning number)
    printf("Enter radius: ");
    scanf("%f", &radius);
    float perimeter = 2 * PI * radius;
    printf("Perimeter: %f\n", perimeter);
    float area = PI * radius * radius;
    printf("Area: %f\n", area);
    return 0;
}