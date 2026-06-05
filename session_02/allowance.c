#include <stdio.h>
/*
1. declare salary (float), grade, total_salary
2. input value salary, grade
3. if grade A -> salary + 300
4. elif grade B -> salary + 200
5. else salary + 100
6. print total_salary
*/
int main() {
    float salary, total_salary;
    char grade[1];
    
    printf("Enter salary: ");
    scanf("%f", &salary);
    
    printf("Enter grade: ");
    scanf("%c", &grade);
    
    const ALLOWANCE_A = 300;
    const ALLOWANCE_B = 200;
    const ALLOWANCE_OTHER = 100;

    if (grade == 'A') {
        total_salary = salary + ALLOWANCE_A;
    } else if (grade == 'B') {
        total_salary = salary + ALLOWANCE_B;
    } else {
        total_salary = salary + ALLOWANCE_OTHER;
    }

    return 0;
}
