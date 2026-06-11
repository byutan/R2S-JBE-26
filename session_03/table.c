#include <stdio.h>

int main() {
    int num;
    
    printf("Enter a number from 2 to 9: ");
    scanf("%d", &num);

    if (num < 2 || num > 9) {
        printf("Entered number must be from 2 to 9");
        return -1;
    }

    for(int i = 1; i <= 9; i++) {
        int mul = num * i;
        printf("%d * %d = %d\n", num, i, mul);
    }
    
    return 0;
}