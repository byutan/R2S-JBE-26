#include <stdio.h>

int main() {
    int num_1, num_2;

    printf("Enter two numbers: ");
    scanf("%d %d", &num_1, &num_2);

    // edge case: 
    if (num_1 == num_2) { // 0-0
        printf("Sum of odd: %d", num_1);
        return 0;
    } 

    if (num_1 > num_2) { // 1-0
        int temp = num_1;
        num_1 = num_2;
        num_2 = temp;
    }

    // regular case: even to even
    int sum = num_1;
    int it = num_1 + 2;
    int end = num_2;
    
    if (num_1 % 2 == 0) { 
        sum++;
        it++;
    } 
    if (num_2 % 2 == 0) {
        end--;
    }

    while (it <= end) {
        sum += it;
        it += 2;
    }

    printf("Sum of odds: %d", sum);
    return 0;
}