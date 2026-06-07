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
    
    for(int i = 0; i < size; i++) {
        if(arr[i] % 3 == 0 || arr[i] % 5 == 0) {
            printf("%d ", arr[i]);
        }
    }
    return 0;
}