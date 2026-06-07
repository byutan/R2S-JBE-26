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
    
    int x, count = 0;

    printf("Enter number x: ");
    scanf("%d", &x);
    
    for(int i = 0; i < size; i++) {
        if(arr[i] == x) {
            count++;
        }
    }
    
    printf("X appeares %d times in the array.", count);
    return 0;
}