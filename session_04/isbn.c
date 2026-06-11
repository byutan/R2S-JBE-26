#include <stdio.h>

int main() {
    const int size = 10; 
    int arr[size];

    for(int i = 0; i < size; i++) {
        printf("Enter element %d: ", i);
        scanf("%d", &arr[i]);
    }

    int sub_unit = size;
    int sum = 0;

    for(int i = 0; i < size; i++) {
        sum += arr[i] * (sub_unit - i);    
    }

    if(sum % 11 == 0) {
        printf("Valid ISBN code.");
    } else {
        printf("Invalid ISBN code.");
    }

    return 0;
}