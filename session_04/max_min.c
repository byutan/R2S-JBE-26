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
    
    int min = arr[0], max = arr[0];

    if (size == 1) {
        printf("Min = %d\n", min);
        printf("Max = %d\n", max);
    } else {
        for(int i = 0; i < size; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }
        printf("Min = %d\n", min);
        printf("Max = %d\n", max);
    }

    return 0;
}