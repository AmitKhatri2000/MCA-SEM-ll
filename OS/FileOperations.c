#include <stdio.h>
#include <stdlib.h>

void create_file();
void read_file();
void write_file();
void delete_file();

int main() {
    int choice;

    do {
        printf("\n\t\t\tFILE OPERATIONS MENU\n\t\t-------------------------\n");
        printf("1. Create a File\n2. Read a File\n3. Write to a File\n4. Delete a File\n5. Exit\nEnter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                create_file();
                break;
            case 2:
                read_file();
                break;
            case 3:
                write_file();
                break;
            case 4:
                delete_file();
                break;
            case 5:
                printf("Exiting...\n");
                exit(0);
            default:
                printf("Invalid choice! Please try again.\n");
        }
    } while (choice <= 5);

    return 0;
}

void create_file() {
    char filename[100];
    printf("Enter the filename to create: ");
    scanf("%s", filename);

    FILE *file = fopen(filename, "w");
    if (file) {
        printf("File '%s' created successfully.\n", filename);
        fclose(file);
    } else {
        printf("Error creating file '%s'.\n", filename);
    }
}

void read_file() {
    char filename[100];
    printf("Enter the filename to read: ");
    scanf("%s", filename);

    FILE *file = fopen(filename, "r");
    if (file) {
        char buffer[255];
        printf("Contents of '%s':\n", filename);
        while (fgets(buffer, sizeof(buffer), file)) {
            printf("%s", buffer);
        }
        fclose(file);
    } else {
        printf("Error reading file '%s'. It may not exist.\n", filename);
    }
}

void write_file() {
    char filename[100];
    printf("Enter the filename to write to: ");
    scanf("%s", filename);

    FILE *file = fopen(filename, "a"); 
    if (file) {
        char data[255];
        printf("Enter text to write (max 255 characters): ");
        getchar(); 
        fgets(data, sizeof(data), stdin);
        fputs(data, file);
        fclose(file);
        printf("Data written to '%s' successfully.\n", filename);
    } else {
        printf("Error opening file '%s' for writing.\n", filename);
    }
}

void delete_file() {
    char filename[100];
    printf("Enter the filename to delete: ");
    scanf("%s", filename);

    if (remove(filename) == 0) {
        printf("File '%s' deleted successfully.\n", filename);
    } else {
        printf("Error deleting file '%s'. It may not exist.\n", filename);
    }
}