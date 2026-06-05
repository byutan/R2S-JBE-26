#include <stdio.h>
/* evaluate the net salary of an employee given the 
following constraints
1. declare variable: basic_salary, DA, HRA, TA, others, PF, IT, net_salary(int)
2. declare const: DA,PF,IT_percentage(float)
3. calculte net_salary following formula
*/
int main() {
    int basic_salary = 12000; 
    int HRA = 150;
    int TA = 450;
    int others = 450;
    const float DA_percentage = 0.12;
    const float PF_percentage = 0.14;
    const float IT_percentage = 0.15;
    float DA = DA_percentage * basic_salary;
    float PF = PF_percentage * basic_salary;
    float IT = IT_percentage * basic_salary;
    float net_salary = basic_salary + DA + HRA + TA + others - (PF + IT);
    printf("Net salary: %f", net_salary);
    return 0;   
}