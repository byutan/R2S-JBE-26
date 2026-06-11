#include <stdio.h>
/*accept 2 numbers. Calculate the difference between the two values.
1. declare 2 numbers num_1, num_2, diff
2. input value num_1, num_2
3. calculate diff = num_1 - num_2
4. if diff = num_1 (num_2) print equal to num_1 (num_2) 
5. else print different
*/

int main () {
    int num_1, num_2, diff;
    
    printf("Enter two numbers: ");
    scanf("%d%d", &num_1, &num_2);

    diff = num_1 - num_2;
    
    if (diff == num_1) {
        printf("Difference is equal to value: %d", num_1);
    } else if (diff == num_2) {
        printf("Difference is equal to value: %d", num_2);
    } else {
        printf("Difference is not equal to any of the values entered.");
    }
    
    return 0;
}