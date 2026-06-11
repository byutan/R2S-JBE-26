#include <stdio.h>
#include <string.h>

#define MAX_SIZE 100
#define MIN_SIZE 0

void input_arr(int size, int arr[size]) {
    for(int i = 0; i < size; i++) {
        printf("Enter array element %d: ", i);

        int ele = 0;

        scanf("%d", &ele);
        arr[i] = ele;
    }
    printf("\n");
}

void output_arr(int size, int arr[size]) {
    if (size == 0) {
        printf("\nNothing to display\n\n");
        return;
    }
    printf("\n[");
    for(int i = 0; i < size; i++) {
        if(i == size - 1) {
            printf("%d]\n", arr[i]);
        } else {
            printf("%d, ", arr[i]);
        }
    }
    printf("\n");
}

void output_desc_arr(int size, int arr[size]) {
    if (size == 0) {
        printf("\nNothing to display\n\n");
        return;
    }
    int desc_arr[size];
    int t_size = size;

    memcpy(desc_arr, arr, size * sizeof(int));
    
    while(t_size > 1) {
        int i = 0;
        int j = i + 1;
    
        while(j != t_size) {
            if(desc_arr[i] < desc_arr[j]) {
                int temp = desc_arr[i];
                desc_arr[i] = desc_arr[j];
                desc_arr[j] = temp;
            }
            i = j;
            j++;
        }
    
        t_size--;
    }
    output_arr(size, desc_arr);
}

void check_odd(int size, int arr[size]) {
    if (size == 0) {
        printf("\nNothing to display\n\n");
        return;
    }
    for(int i = 0; i < size; i++) {
        if(arr[i] % 2 == 0) {
            printf("\nNot odd\n\n");
            return;
        }
    }
    printf("\nOdd\n\n");
}

void find_value(int size, int arr[size]) {
    if (size == 0) {
        printf("\nNothing to display\n\n");
        return;
    }
    int value;
    printf("\nPlease enter a value: ");
    scanf("%d", &value);
    for(int i = 0; i < size; i++) {
        if(arr[i] == value) {
            printf("Exists value %d in the array.\n\n", arr[i]);
            return;
        }
    }
    printf("Does not exist value %d in the array.\n\n", value);
}

void display_prime(int size, int arr[size]) {
    if (size == 0) {
        printf("\nNothing to display\n\n");
        return;
    }
    printf("\n");
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
            printf("%d ", n);
        }
    }
    printf("\n\n");
}

int main() {
    printf("\n---PROGRAM---\n");

    int choice = 0;

    int size = MIN_SIZE;
    int arr[MAX_SIZE];

    while(choice != 1) {
        printf("1. Exit application.\n");
        printf("2. Input the array.\n");
        printf("3. Output the array.\n");
        printf("4. Output in descending order.\n");
        printf("5. Check if all elements are odd.\n");
        printf("6. Search a value.\n");
        printf("7. Display prime numbers element\n");
        printf("Enter number with following options: ");
        scanf("%d", &choice);
        

        switch (choice) {
        case 1:
            break;
        
        case 2:
            printf("\n1. Enter the array size (1-100): ");
            scanf("%d", &size);
            while(size < MIN_SIZE || size > MAX_SIZE) {
                printf("Out of bound (1-100). Please try again: ");
                size = MIN_SIZE;
                scanf("%d", &size);
            }
            input_arr(size, arr);
            break;
        
        case 3:
            output_arr(size, arr);
            break;
        
        case 4:
            output_desc_arr(size, arr);
            break;
        
        case 5:
            check_odd(size, arr);
            break;
        
        case 6:
            find_value(size, arr);
            break;
        
        case 7:
            display_prime(size, arr);
            break;

        default:
            printf("\nInvalid option. Try again.\n\n");
            break;
        }
    }
    return 0;
}