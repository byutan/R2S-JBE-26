#include <stdio.h>
#include <string.h>
/*
1. grade for 4 course: float a,b,c...
2. validate grade (0-10)
3. calculate gpa on 10 point scale
4. convert to 4 point-scale
5. classify rank
6. display gpa, rank
*/

void classify_rank(float grade, char rank[]) {
    if(grade >= 3.6 && grade <= 4.0) {
        strcpy(rank,"Execellent");
    } else if(grade >= 3.2 && grade <= 3.59) {
        strcpy(rank,"Good");
    } else if(grade >= 2.5 && grade <= 3.19) {
        strcpy(rank,"Fair");
    } else if(grade >= 2.00 && grade <= 2.49) {
        strcpy(rank,"Average");
    } else if(grade < 2.00) {
        strcpy(rank,"Weak");
    }
}

int validate_grade(float grade) {
    if(grade < 0.0 || grade > 10.0) {
        return -1;
    }
    return 1;
}

float input_grade() {
    float grade = -1.0;
    scanf("%f", &grade);
    return grade;
}



void input_gpa(float grade[4]) {
    printf("---Input marks---");
    int choice = -1;
    while (choice != 0) {
        printf("\nChoose option: \n1. Database\n2. C\n3. OOP\n4. Java\n0. Quit\n");
        printf("\nOption: ");
        scanf("%d", &choice);
        switch (choice) {
            float entered_grade;
            int is_valid;
            case 1:
                is_valid = -1;
                while(is_valid == -1) {
                    printf("Enter grade for course Database: ");
                    entered_grade = input_grade();
                    is_valid = validate_grade(entered_grade);
                    if(is_valid == 1) {
                        grade[0] = entered_grade;
                        break;
                    } else {
                        printf("\nGrade must be from 0.0 to 10.0. Please try again.\n");
                    }
                }
                break;
            
            case 2:
                is_valid = -1;
                while(is_valid == -1) {
                    printf("Enter grade for course C: ");
                    entered_grade = input_grade();
                    is_valid = validate_grade(entered_grade);
                    if(is_valid == 1) {
                        grade[1] = entered_grade;
                        break;
                    } else {
                        printf("\nGrade must be from 0.0 to 10.0. Please try again.\n");
                    }
                }
                break;
            
            case 3:
                is_valid = -1;
                while(is_valid == -1) {
                    printf("Enter grade for course OOP: ");
                    entered_grade = input_grade();
                    is_valid = validate_grade(entered_grade);
                    if(is_valid == 1) {
                        grade[2] = entered_grade;
                        break;
                    } else {
                        printf("\nGrade must be from 0.0 to 10.0. Please try again.\n");
                    }
                }
                break;
            
            case 4:
                is_valid = -1;
                while(is_valid == -1) {
                printf("Enter grade for course Java: ");
                    entered_grade = input_grade();
                    is_valid = validate_grade(entered_grade);
                    if(is_valid == 1) {
                        grade[3] = entered_grade;
                        break;
                    } else {
                        printf("\nGrade must be from 0.0 to 10.0. Please try again.\n");
                    }
                }
                break;
            
            case 0: 
                break;

            default:
                printf("\nOptions must be from 0 to 5. Please try again.\n");
                break;
        }
    }
}

void display_gpa(float grade[4]) {
    printf("\n---GPA table---\n");
    float total_gpa = 0.0;
    for(int i = 0; i < 4; i++) {
        char course[50] = "";
        if (i == 0) {
            strcpy(course, "Database");
        } else if (i == 1) {
            strcpy(course, "C");
        } else if (i == 2) {
            strcpy(course, "OOP");
        } else if (i == 3) {
            strcpy(course, "Java");
        }
        total_gpa += grade[i];
        printf("%s: %.2f\n", course, grade[i]);
    }
    float average_gpa = ((total_gpa / 4) / 10) * 4;
    char rank[50] = "";
    
    classify_rank(average_gpa, rank);

    printf("\n---Average GPA & Rank---\n");
    printf("Average GPA: %.2f\n", average_gpa);
    printf("Rank: %s\n", rank);
}

int main() {
    float grade[4] = {0.0, 0.0, 0.0, 0.0};

    input_gpa(grade);
    display_gpa(grade);
    return 0;
}