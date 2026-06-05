#include <stdio.h>
/*accept 2 numbers and tell whether the product of the two numbers is equal to or greater than 1000
1. declare two numbers num_1, num_2(int)
2. input value into num_1, num_2
3. declare product as product of num_1 & num_2
4. declare constant compare_num as 1000
5. if product >= const print greater or equal
6. else print less than const
*/
int main() {
    int num_1, num_2;

    printf("Enter two number: ");
    scanf("%d%d", &num_1, &num_2);

    int product = num_1 * num_2;

    const int COMPARE_NUM = 1000;

    if (product >= COMPARE_NUM) {
        printf("Product of two numbers is greater or equal to %d.", COMPARE_NUM);
    } else {
        printf("Product of two numbers is less than %d.", COMPARE_NUM);
    }

    return 0;
}