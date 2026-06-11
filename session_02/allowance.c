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
    char grade;
    
    printf("Enter salary: ");
    scanf("%f", &salary);
    
    printf("Enter grade: ");
    scanf(" %c", &grade);
    
    const int ALLOWANCE_A = 300;
    const int ALLOWANCE_B = 200;
    const int ALLOWANCE_OTHER = 100;

    switch (grade) {
    case 'A':
        total_salary = salary + ALLOWANCE_A;
        break;
    case 'B':
        total_salary = salary + ALLOWANCE_B;
        break;
    default:
        total_salary = salary + ALLOWANCE_OTHER;
        break;
    }
    
    printf("Total salary: %f", total_salary);
    return 0;
}
