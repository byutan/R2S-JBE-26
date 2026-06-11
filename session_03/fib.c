#include <stdio.h>

int main() {
    int input;

    printf("Enter a number: ");
    scanf("%d", &input);

    if (input == 0 || input == 1) {
        printf("%d", input);
        return 0;
    } else if (input < 0) {
        printf("Input must be a positive number.");
        return -1;
    }

    long long f0 = 0;
    long long f1 = 1;
    long long fn = 0;
    
    printf("%d ", f0);
    printf("%d ", f1);
    
    fn = f1 + f0;
    
    while (fn <= input) {
        printf("%d ", fn);
        f0 = f1;
        f1 = fn;
        fn = f1 + f0;
    }

    return 0;
}