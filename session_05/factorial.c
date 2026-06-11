#include <stdio.h>

int cal_factorial(int num) {
    int res = 1;
    while (num > 0) {
        res *= num;
        num--;
    }
    return res;
}

int main() {
    int num, factorial;

    printf("Enter a positive integer number: ");
    scanf("%d", &num);

    if(num <= 0) {
        printf("Input must be greater than 0");
        return -1;
    } else if (num == 1) {
        printf("Factorial of %d: %d\n", num, 1);
        return 0;
    }

    factorial = cal_factorial(num);

    printf("Factorial of %d: %d\n", num, factorial);
    return 0;
}