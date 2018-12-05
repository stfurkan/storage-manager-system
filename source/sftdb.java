import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class sftdb {

    public static void main(String args[]) throws IOException {

        Scanner scan = new Scanner(System.in);
        Scanner scanint = new Scanner(System.in);
        FileWriter fWriter = null;
        BufferedWriter writer = null;

        int sec;
        int close_db = 1;
        int field_cnt = 8;
        int field_name_size = 10;
        int type_name_size = 22;
        int field_size = 4;

        while(close_db==1){
            System.out.println("Welcome to SFTDB");
            System.out.println("(1) Create a type");
            System.out.println("(2) Delete a type");
            System.out.println("(3) List all types");
            System.out.println("(4) Create a record");
            System.out.println("(5) Delete a record");
            System.out.println("(6) Search for a record(by primary key)");
            System.out.println("(7) List all records of a type");
            System.out.println("(8) Exit");
            System.out.println("Please choose what operation you want to do above (1-8).");

            sec = scanint.nextInt();

            switch(sec){
                case 1://create a new type
                    try {
                        fWriter = new FileWriter("cntMan.dat",true);
                        writer = new BufferedWriter(fWriter);

                        String temp_str = "";
                        try
                        {
                            temp_str = new String ( Files.readAllBytes( Paths.get("cntMan.dat") ) );
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        int type_count = temp_str.length()/103;
                        try_type_name:
                        for(int i=1; i<=4; i++){
                            if(i==1){
                                System.out.print("Enter type name (max 22 characters): ");
                                String text = scan.nextLine();
                                int fark1 = type_name_size - text.length();
                                if(fark1 != 0){
                                    for(int k=1; k<=fark1; k++){
                                        text = text + "\0";
                                    }
                                }

                                for(int y=0; y<type_count; y++){
                                    if(text.equals(temp_str.substring((y*103),(y*103)+22))){
                                        System.out.println("Type name " + text + " already exist. Please try another type name!");
                                        break try_type_name;
                                    }
                                }

                                writer.write(text);
                            }
                            else if(i==2){
                                System.out.print("How many field does the type has (max 8): ");
                                String text = scan.nextLine();
                                field_cnt = Integer.parseInt(text);
                                writer.write(text);
                            }
                            else if(i==3){
                                for(int j=1; j<=field_cnt; j++){
                                    System.out.print("Enter field "+ j + " name: (max 10 characters)");
                                    String text = scan.nextLine();
                                    int fark2 = field_name_size - text.length();
                                    if(fark2 != 0){
                                        for(int k=1; k<=fark2; k++){
                                            text = text + "\0";
                                        }
                                    }
                                    writer.write(text);
                                }
                            }
                            else{
                                int field_fark = 8 - field_cnt;
                                for(int m=1; m<=field_fark; m++){
                                    writer.write("\0\0\0\0\0\0\0\0\0\0");
                                }
                            }
                        }
                        System.out.println("Type created!");
                        writer.close();
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                    break;
                case 2://delete a type
                    System.out.print("Enter type name to delete:");
                    String text = scan.nextLine();
                    String type_name_3 = text;
                    int fark3 = type_name_size - text.length();
                    if(fark3 != 0){
                        for(int k=1; k<=fark3; k++){
                            text = text + "\0";
                        }
                    }

                    String temp_str = "";
                    try
                    {
                        temp_str = new String ( Files.readAllBytes( Paths.get("cntMan.dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    int type_count = temp_str.length()/103;
                    String temp_str_2 = "";
                    for(int y=0; y<type_count; y++){
                        if(text.equals(temp_str.substring((y*103),(y*103)+22))){
                            temp_str_2 = temp_str.replace(temp_str.substring((y*103),(y*103)+103),"");
                            System.out.println("Type deleted!");

                            File file = new File(type_name_3+".dat");

                            if(file.delete()){
                                System.out.println(file.getName() + " is deleted!");
                            }else{
                                System.out.println("Delete operation is failed.");
                            }
                        }
                    }

                    try {
                        fWriter = new FileWriter("cntMan.dat");
                        writer = new BufferedWriter(fWriter);
                        writer.write(temp_str_2);
                        writer.close();
                    }
                    catch (Exception e) {
                        System.out.println("Error!");
                    }


                    break;


                case 3://list all types
                    temp_str = "";
                    try
                    {
                        temp_str = new String ( Files.readAllBytes( Paths.get("cntMan.dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    type_count = temp_str.length()/103;

                    for(int y=0; y<type_count; y++){
                        System.out.println(temp_str.substring((y*103),(y*103)+22));
                        /*
                        System.out.println("Type Name: " +temp_str.substring((y*103),(y*103)+22));
                        System.out.println("Field Count: " +temp_str.substring((y*103)+22,(y*103)+23));
                        for(int t=1; t<=Integer.parseInt(temp_str.substring((y*103)+22,(y*103)+23)); t++){
                            System.out.println("Field "+ String.valueOf(t) +": "+ temp_str.substring((y*103)+23+((t-1)*10),(y*103)+23+((t)*10)));
                        }
                        */
                    }
                    break;
                case 4://create a record
                    System.out.print("Enter type name to create a record:");
                    text = scan.nextLine();
                    String temp_type_name = text;

                    try {
                        temp_str = "";
                        try
                        {
                            temp_str = new String ( Files.readAllBytes( Paths.get("cntMan.dat") ) );
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        type_count = temp_str.length()/103;
                        int fark1 = type_name_size - text.length();
                        if(fark1 != 0){
                            for(int k=1; k<=fark1; k++){
                                text = text + "\0";
                            }
                        }
                        try_type_name:
                        for(int y=0; y<type_count; y++) {
                            int tmp_cnt = 0;
                            if (text.equals(temp_str.substring((y * 103), (y * 103) + 22))) {
                                tmp_cnt = tmp_cnt + 1;
                            } else if (y == type_count - 1 && tmp_cnt == 0) {
                                System.out.println("Type name does not exist. Try again!");
                                break try_type_name;
                            }
                        }
                        try_type_name2:
                        for(int y=0; y<type_count; y++){
                            if(text.equals(temp_str.substring((y*103),(y*103)+22))){
                                fWriter = new FileWriter(temp_type_name+".dat",true);
                                writer = new BufferedWriter(fWriter);
                                temp_str_2 = "";

                                try
                                {
                                    temp_str_2 = new String ( Files.readAllBytes( Paths.get(temp_type_name+".dat") ) );
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }

                                int record_count = temp_str_2.length()/32;

                                for(int m=1; m<3; m++){
                                    if(m==1){
                                        System.out.println("Type Name: " +temp_str.substring((y*103),(y*103)+22));
                                        System.out.println("First field is the key and must be unique!");
                                        System.out.println("Field Count: " +temp_str.substring((y*103)+22,(y*103)+23));

                                        for(int t=1; t<=Integer.parseInt(temp_str.substring((y*103)+22,(y*103)+23)); t++){
                                            System.out.println("Enter integer data (max 9999) for field "+ String.valueOf(t) +": "+ temp_str.substring((y*103)+23+((t-1)*10),(y*103)+23+((t)*10)));
                                            text = scan.nextLine();
                                            fark1 = field_size - text.length();
                                            if(fark1 != 0){
                                                for(int k=1; k<=fark1; k++){
                                                    text = text + "\0";
                                                }
                                            }

                                            for(int z=0; z<record_count; z++){
                                                if(text.equals(temp_str_2.substring((z*32),(z*32)+4))){
                                                    System.out.println("Type primary key " + text + " already exist. Please try another primary key field for the record!");
                                                    break try_type_name2;
                                                }
                                            }

                                            writer.write(text);
                                        }
                                    }
                                    else if(m==2){
                                        int field_fark = 8 - Integer.parseInt(temp_str.substring((y*103)+22,(y*103)+23));
                                        for(int n=1; n<=field_fark; n++){
                                            writer.write("\0\0\0\0");
                                        }
                                        System.out.println("Record created!");
                                    }

                                }

                            }

                        }

                        writer.close();
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                    break;
                case 5://delete a record by primary key
                    System.out.print("Enter type name to delete a record:");
                    text = scan.nextLine();
                    temp_type_name = text;

                    temp_str = "";
                    try
                    {
                        temp_str = new String ( Files.readAllBytes( Paths.get("cntMan.dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    type_count = temp_str.length()/103;
                    int fark1 = type_name_size - text.length();
                    if(fark1 != 0){
                        for(int k=1; k<=fark1; k++){
                            text = text + "\0";
                        }
                    }
                    try_type_name:
                    for(int y=0; y<type_count; y++) {
                        int tmp_cnt = 0;
                        if (text.equals(temp_str.substring((y * 103), (y * 103) + 22))) {
                            tmp_cnt = tmp_cnt + 1;
                        } else if (y == type_count - 1 && tmp_cnt == 0) {
                            System.out.println("Type name does not exist. Try again!");
                            break try_type_name;
                        }
                    }



                    System.out.print("Enter record primary key to delete:");
                    text = scan.nextLine();
                    fark3 = field_size - text.length();
                    if(fark3 != 0){
                        for(int k=1; k<=fark3; k++){
                            text = text + "\0";
                        }
                    }

                    String temp_str2 = "";
                    try
                    {
                        temp_str2 = new String ( Files.readAllBytes( Paths.get(temp_type_name+".dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    type_count = temp_str2.length()/32;
                    temp_str_2 = "";
                    int page_count = 0;
                    for(int y=0; y<type_count; y++){
                        if(y%32==0){
                            page_count = page_count + 1;
                            System.out.println("Searching page " + String.valueOf(page_count));
                        }
                        if(text.equals(temp_str2.substring((y*32),(y*32)+4))){
                            temp_str_2 = temp_str2.replace(temp_str2.substring((y*32),(y*32)+32),"");
                            System.out.println("Record deleted");
                        }
                    }

                    try {
                        fWriter = new FileWriter(temp_type_name+".dat");
                        writer = new BufferedWriter(fWriter);
                        writer.write(temp_str_2);
                        writer.close();
                    }
                    catch (Exception e) {
                        System.out.println("Error!");
                    }
                    break;
                case 6://search record by primary key
                    System.out.print("Enter type name to find a record:");
                    text = scan.nextLine();
                    temp_type_name = text;

                    int tmp_field_size = 0;

                    temp_str = "";
                    try
                    {
                        temp_str = new String ( Files.readAllBytes( Paths.get("cntMan.dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    type_count = temp_str.length()/103;
                    fark1 = type_name_size - text.length();
                    if(fark1 != 0){
                        for(int k=1; k<=fark1; k++){
                            text = text + "\0";
                        }
                    }
                    try_type_name:
                    for(int y=0; y<type_count; y++) {
                        int tmp_cnt = 0;
                        if (text.equals(temp_str.substring((y * 103), (y * 103) + 22))) {
                            tmp_cnt = tmp_cnt + 1;
                            tmp_field_size = Integer.parseInt(temp_str.substring((y*103)+22,(y*103)+23));
                        } else if (y == type_count - 1 && tmp_cnt == 0) {
                            System.out.println("Type name does not exist. Try again!");
                            break try_type_name;
                        }
                    }



                    System.out.print("Enter record primary key to find:");
                    text = scan.nextLine();
                    fark3 = field_size - text.length();
                    if(fark3 != 0){
                        for(int k=1; k<=fark3; k++){
                            text = text + "\0";
                        }
                    }

                    temp_str2 = "";
                    try
                    {
                        temp_str2 = new String ( Files.readAllBytes( Paths.get(temp_type_name+".dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    int record_count = temp_str2.length()/32;
                    int page_count2 = 0;
                    for(int y=0; y<record_count; y++){
                        if(text.equals(temp_str2.substring((y*32),(y*32)+4))){
                            if(y%32==0){
                                page_count2 = page_count2 + 1;
                                System.out.println("Searching page " + String.valueOf(page_count2));
                            }
                            for(int t=1; t<=tmp_field_size; t++){
                                System.out.print(temp_str2.substring((y*32)+((t-1)*4),(y*32)+((t)*4))+" ");
                            }
                            System.out.println();
                        }
                    }
                    break;
                case 7://list all records of a type
                    System.out.print("Enter type name to find all record:");
                    text = scan.nextLine();
                    temp_type_name = text;

                    tmp_field_size = 0;

                    temp_str = "";
                    try
                    {
                        temp_str = new String ( Files.readAllBytes( Paths.get("cntMan.dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    type_count = temp_str.length()/103;
                    fark1 = type_name_size - text.length();
                    if(fark1 != 0){
                        for(int k=1; k<=fark1; k++){
                            text = text + "\0";
                        }
                    }
                    try_type_name:
                    for(int y=0; y<type_count; y++) {
                        int tmp_cnt = 0;
                        if (text.equals(temp_str.substring((y * 103), (y * 103) + 22))) {
                            tmp_cnt = tmp_cnt + 1;
                            tmp_field_size = Integer.parseInt(temp_str.substring((y*103)+22,(y*103)+23));
                        } else if (y == type_count - 1 && tmp_cnt == 0) {
                            System.out.println("Type name does not exist. Try again!");
                            break try_type_name;
                        }
                    }

                    temp_str2 = "";
                    try
                    {
                        temp_str2 = new String ( Files.readAllBytes( Paths.get(temp_type_name+".dat") ) );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    record_count = temp_str2.length()/32;
                    int page_count3 = 0;
                    for(int y=0; y<record_count; y++){
                        if(y%32==0){
                            page_count3 = page_count3 + 1;
                            System.out.println("Searching page " + String.valueOf(page_count3));
                        }
                        for(int t=1; t<=tmp_field_size; t++){
                            System.out.print(temp_str2.substring((y*32)+((t-1)*4),(y*32)+((t)*4))+" ");
                        }
                        System.out.println();

                    }
                    break;
                case 8:
                    close_db = 0;
                    break;
                default:
                    System.out.println("You have entered an invalid value, please try again!");
                    break;
            }


        }


    }

}