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
    
    int sum = 0;

    if (size == 1 && arr[0] % 2 != 0) {
        sum = arr[0];
        printf("Sum = %d\n", sum);
    } else if (size == 1 && arr[0] % 2 == 0) {
        printf("Sum = %d\n", sum);
    } else {
        for (int i = 0; i < size; i++) {
            if (arr[i] % 2 != 0) {
                sum += arr[i];
            }
        }
        printf("Sum = %d\n", sum);
    }
    return 0;
}