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
    
    switch (type) {
    case 'B': case 'b':
        printf("%s", BASIC);
        break;
    case 'C': case 'c':
        printf("%s", COBOL);
        break;
    case 'F': case 'f':
        printf("%s", FORTRAN);
        break;
    case 'P': case 'p':
        printf("%s", PASCAL);
        break;
    case 'V': case 'v':
        printf("%s", VSC);
        break;
    default:
        printf("Other character is not valid.");
        break;
    }

    return 0;
}