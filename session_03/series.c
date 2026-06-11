#include <stdio.h>

int main() {
    int input = 100;

    while (input >= 5) {
        if (input == 5) {
            printf("%d", input);
        } else {
            printf("%d, ", input);
        }
        input -= 5;    
    }

    return 0;
}