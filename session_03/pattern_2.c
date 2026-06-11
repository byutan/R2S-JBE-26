#include <stdio.h>
#include <string.h>

int main() {
    int num;
    
    printf("Enter number of loops: ");
    scanf("%d", &num);

    if (num < 0) {
        printf("Number of loops must be positive.\n");
        return -1;
    } else if (num == 0) {
        return 0;
    } 
    char output[256] = "";
    char sym[] = "*";
    
    for(int i = 0; i < num; i++) {
        strcat(output, sym);
    } 
    
    for(int i = num; i >= 1; i--) {
        printf("%s\n", output);
        int current_len = strlen(output);
        output[current_len - 1]='\0';
    }
    return 0;
}