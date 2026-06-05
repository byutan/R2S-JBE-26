#include <stdio.h>
/*
1. declare mark(float), grade(char)
2. input value mark 
3. declare const MAX, MIN(int)
4. if cond... -> grade... else if ... -> grade ...
5. print final grade or throw error
*/

int main() {
    float mark;
    char grade;
    
    printf("Enter mark: ");
    scanf("%f", &mark);
    
    const int MAX_GRADE = 100;
    const int MIN_GRADE = 0;

    if (mark > 75 && mark <= MAX_GRADE) {
        grade = 'A';
    } else if (mark <= 75 && mark > 60) {
        grade = 'B';
    } else if (mark <= 60 && mark > 45) {
        grade = 'C';
    } else if (mark <= 45 && mark > 35) {
        grade = 'D';
    } else if (mark <= 35 && mark >= MIN_GRADE) {
        grade = 'E';
    } else {
        printf("Invalid mark");
        return -1;
    }
    printf("grade: %c", grade);
    return 0;
}