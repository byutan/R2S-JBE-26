#include <stdio.h>
/*accepts the salary and age from the user and
displays the same on the screen as output.
1. declare variable: salary(float), age(age)
2. input value: salary, age
3. print value: salary, age
*/
int main() {
    float salary;
    int age;
    
    printf("Enter salary: ");
    scanf("%f", &salary);
    
    printf("Enter age: ");
    scanf("%d", &age);
    
    printf("Salary: %f, age: %d\n", salary, age);
    
    return 0;
}