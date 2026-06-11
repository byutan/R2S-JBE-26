#include <stdio.h>

const float PI = 3.14;

float cal_area(float r) {
    float area = PI * r * r;
    return area; 
}

float cal_perimeter(float r) {
    float perimeter = 2 * PI * r;
    return perimeter;
}
int main() {
    float radius, area, perimeter;

    printf("Enter radius: ");
    scanf("%f", &radius);

    area = cal_area(radius);
    perimeter = cal_perimeter(radius);

    printf("Area: %f\nPerimeter: %f\n", area, perimeter);

    return 0;
}