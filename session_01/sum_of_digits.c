#include <stdio.h>
/*
Declare variables num, digit1, digit2, and digit3. Prompt the user to enter a three-digit number, extract each digit using division and modulo operations, calculate the sum of digits, and then display the result.
1. declare variable num, digit1, digit2, digit3(int), sum
2. input 3 digit number: num
3. digit is the modulo of num and 10, each turn divide by 10
4. calculate sum of 3 digits
5. print sum
*/
int main() {
    int num, digit1, digit2, digit3;
    
    printf("Enter 3-digit number: ");
    scanf("%d", &num); 
    
    digit3 = num % 10;
    num /= 10;
    digit2 = num % 10;
    num /= 10;
    digit1 = num;
    
    int sum = digit1 + digit2 + digit3;
    printf("Sum of digits: %d", sum);
    return 0;
}