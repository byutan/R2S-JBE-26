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
        if(arr[i] < 0) {
            count++;
        }
    }
    printf("%d values is less than 0 in the array.", count);
    return 0; 
}