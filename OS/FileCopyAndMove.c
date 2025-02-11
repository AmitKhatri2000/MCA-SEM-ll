#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void clear_screen() {
#ifdef _WIN32
    system("cls");
#else
    system("clear");
#endif
}

void copy_file();
void move_file();
void print_file();

char fn2[100];

int main() {
    int c;
    clear_screen();
    do {
        printf("\n\t\tMain Menu\n-------------------------------\n");
        printf("1. Copy a File\n2. Move a File\n3. Exit\n");
        scanf("%d", &c);
        getchar(); 
        switch (c) {
            case 1:
                copy_file();
                break;

            case 2:
                move_file();
                break;

            case 3:
                exit(0);
        }
    } while (c <= 3);

    return 0;
}

void copy_file() {
    FILE *f1, *f2;
    char ch, fn1[100];
    int a;

    printf("\nDo you want to see the previous files (1/0)? ");
    scanf("%d", &a);
    if (a == 1)
        print_file();

    printf("Enter the source file name: ");
    scanf("%s", fn1);
    printf("Enter the destination file name: ");
    scanf("%s", fn2);

    f1 = fopen(fn1, "r");
    if (f1 == NULL) {
        printf("Can't open the source file\n");
    } else {
        f2 = fopen(fn2, "w");
        if (f2 == NULL) {
            printf("Can't open the destination file\n");
            fclose(f1);
            return;
        }

        while ((ch = getc(f1)) != EOF)
            putc(ch, f2);
        
        printf("File copied successfully.\n");
        fclose(f2);
    }
    fclose(f1);
}

void move_file() {
    FILE *f1, *f2;
    char ch, fn1[100];
    int a;

    printf("\nDo you want to see the previous files (1/0)? ");
    scanf("%d", &a);
    if (a == 1)
        print_file();

    printf("Enter the source file name: ");
    scanf("%s", fn1);
    printf("Enter the destination file name: ");
    scanf("%s", fn2);

    f1 = fopen(fn1, "r");
    if (f1 == NULL) {
        printf("Can't open the source file\n");
    } else {
        f2 = fopen(fn2, "w");
        if (f2 == NULL) {
            printf("Can't open the destination file\n");
            fclose(f1);
            return;
        }

        while ((ch = getc(f1)) != EOF)
            putc(ch, f2);
        
        printf("File moved successfully.\n");
        fclose(f2);
        remove(fn1);
    }
    fclose(f1);
}

void print_file() {
    system("dir");
}
