#include <stdio.h>

int main() {
    int size;

    printf("Enter array size: ");
    scanf("%d", &size);

    if (size < 1) {
        printf("Invalid array size");
        return -1;
    }
    
    int arr[size];

    for(int i = 0; i < size; i++) {
        printf("Enter element %d: ", i);
        scanf("%d", &arr[i]);
    }

    int count = 0;
    for(int i = 0; i < size; i++) {
        int n = arr[i];
        int is_prime = 1;
        if (n <= 1) {
            is_prime = 0;
        } else if (n == 2 || n == 3) {
            is_prime = 1;
        } else if (n % 2 == 0 || n % 3 == 0) {
            is_prime = 0;
        } else {
            for (int j = 5; j * j <= n; j += 6) {
                if(n % j == 0 || n % (j + 2) == 0) {
                    is_prime = 0;
                    break;
                }
            }
        }
        if(is_prime == 1) {
            count++;
        }
    }
    printf("%d values are prime numbers in the array.", count);
    return 0; 
}