#include <stdio.h>
#include <string.h> 

int main() {
    int choice = 1;
    int num;
    
    printf("Enter follow options: \n 1. Ascending - Default\n 2. Descending\n");
    scanf("%d", &choice);

    printf("Enter number of loops: \n");
    scanf("%d", &num);

    if (num < 0) {
        printf("Number of loops must be positive.\n");
        return -1;
    } else if (num == 0) {
        return 0;
    } 

    char output[256] = "";
    char temp[15];
    
    switch (choice) {
    case 1:
        for (int i = 1; i <= num; i++) {
            snprintf(temp, sizeof(temp), "%d", i);

            strcat(output, temp);
            
            printf("%s\n", output);
        }
        break;
    case 2:
        for (int i = 1; i <= num; i++) {
            
            snprintf(temp, sizeof(temp), "%d", i);
            
            strcat(output, temp);
        }
        for (int i = num; i >= 1; i--) {
            printf("%s\n", output);

            snprintf(temp, sizeof(temp), "%d", i);

            int len_to_cut = strlen(temp);
            int current_len = strlen(output);

            output[current_len - len_to_cut]='\0';
        }
    break;
    default:
        break;
    }
}