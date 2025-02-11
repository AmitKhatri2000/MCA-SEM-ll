#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

void clear_screen() {
    system("cls");
}

int main() {
    char name[100];
    int n;

    clear_screen();
    printf("\t\tMAIN MENU OF PERMISSION\n\t--------------------------------\n");
    printf("1. Only Read\n2. Only Write\n3. Exit\nEnter your choice:\n");
    scanf("%d", &n);
    getchar(); 

    switch (n) {
        case 1:
            printf("\nEnter the File Name:\n");
            fgets(name, sizeof(name), stdin);
            name[strcspn(name, "\n")] = 0;
            
            if (SetFileAttributes(name, FILE_ATTRIBUTE_READONLY)) {
                printf("%s file read permission accepted\n", name);
            } else {
                perror("Unable to set read permission");
            }
            break;

        case 2:
            printf("\nEnter the File Name:\n");
            fgets(name, sizeof(name), stdin);
            name[strcspn(name, "\n")] = 0; 
            
            SetFileAttributes(name, FILE_ATTRIBUTE_NORMAL);
            if (SetFileAttributes(name, FILE_ATTRIBUTE_NORMAL)) {
                printf("%s write permission accepted\n", name);
            } else {
                perror("Unable to set write permission");
            }
            break;

        case 3:
            exit(0);
            break;

        default:
            printf("Invalid choice.\n");
            return 1;
    }

    printf("Operation completed successfully.\n");
    return 0;
}
