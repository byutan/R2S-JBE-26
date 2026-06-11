#include <stdio.h>
#include <math.h>

/*
1. declare var a,b,c,delta(int)
2. calcualte delta
3. if delta pos -> calculate root
4. if delta neg -> notify
*/

int main() {
    int a, b, c, delta;
    
    printf("Enter a, b, c parameters: ");
    scanf("%d%d%d", &a, &b, &c);

    delta = pow(b,2)-(4*a*c);
    
    if (delta > 0) {
        float x1, x2;

        if (a==0) {
            printf("Parameter a is invalid");
            return -1;
        }

        x1 = (float)(-b + sqrt(delta))/(float)(2*a);
        x2 = (float)(-b - sqrt(delta))/(float)(2*a);
        
        printf("Roots x1: %f\n", x1);
        printf("Roots x2: %f\n", x2);
    } else if (delta == 0) {
        if (a==0) {
            printf("Parameter a is invalid");
            return -1;
        }
        
        float x = (float)-b/(float)2*a;

        printf("Root x: %f", x);
    } else {
        printf("The equation has no real root.");
    }
    return 0;
}