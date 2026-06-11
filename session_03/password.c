#include <stdio.h>

int main() {
    const int valid_password = 12345;
    int input_password;
    
    for(int i = 0; i < 3; i++) {
        printf("Enter your password: ");
        scanf("%d", &input_password);
        if(input_password != valid_password) {
            printf("Invalid password\n");
        } else {
            break;
        }
    }
    return 0;
}