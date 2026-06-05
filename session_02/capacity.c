#include <stdio.h>
/*
1. declare type(char)
3. if else...
4. print type
5. throw error if number character
*/

int main() {
    char type;
    printf("Enter type: ");
    scanf("%c", &type);

    const char BASIC[] = "Basic";
    const char COBOL[] = "Cobol";
    const char FORTRAN[] = "Cobol";
    const char PASCAL[] = "Pascal";
    const char VSC[] = "Visual C++";

    if (type == 'B' || type == 'b') {
        printf("%s", BASIC);
    } else  if (type == 'C' || type == 'c') {
        printf("%s", COBOL);
    } else  if (type == 'F' || type == 'f') {
        printf("%s", FORTRAN);
    } else  if (type == 'P' || type == 'p') {
        printf("%s", PASCAL);
    } else  if (type == 'V' || type == 'v') {
        printf("%s", VSC);
    } else {
        printf("Other character is not valid.");
        return -1;
    }

    return 0;
}