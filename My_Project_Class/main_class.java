import java.util.Scanner;

class main_class
{
    Scanner ob=new Scanner(System.in);
    accessory in=new accessory();
    Game_arcade admin=new Game_arcade();
    String create_username(Game_arcade members[])
    {
        int i;
        String username="";
        for(;;)
        {
            System.out.println("\fEnter 0 to exit\n");
            System.out.print("Please enter your username - ");
            boolean check=false;
            username=ob.next();
            for(i=0;i<10;i++)
            {
                if(username.equals(members[i].username))
                {
                    check=true;
                    break;
                }
            }
            if(check&&!(username.equals("0")))
                System.out.println("This username is already in use");
            else
                return(username);
        }
    }

    String create_password(Game_arcade members[])
    {
        String password="";
        int i;
        for(;;)
        {
            System.out.print("Please enter your password - ");
            boolean check=false;
            password=ob.next();
            for(i=0;i<10;i++)
            {
                if(password.equals(members[i].password))
                {
                    check=true;
                    break;
                }
            }
            if(password.equals("0"))
                return("0");
            else if(password.length()<8)
                System.out.println("Please enter an 8 character pasword");
            else if(check)
                System.out.println("This password is already in use");
            else
                return(password);
            in.delay(2);
        }
    }

    void delete_username(Game_arcade members[])
    {
        System.out.print("\fEnter username - ");
        String username=ob.next();
        int i;
        boolean check=false;
        for(i=0;i<10;i++)
        {
            if(members[i].username.equals(username))
            {
                check=true;
                break;
            }
        }
        if(check)
            members[i]=new Game_arcade();
        else
            System.out.println("Name not found");
        in.delay(2);
    }

    int login_username(Game_arcade members[])
    {
        String username="";
        int i;
        for(;;)
        {
            System.out.println("\fEnter 0 to exit\n");
            System.out.print("Please enter your username - ");
            boolean check=false;
            username=ob.next();
            if(username.equals("0"))
                return(-1);
            if(username.equals(admin.username))
                return(10);
            for(i=0;i<10;i++)
            {
                if(username.equals(members[i].username))
                {
                    check=true;
                    break;
                }
            }
            if(check)
                return(i);
            else
                System.out.println("Username not found");
            in.delay(2);
        }
    }

    int login_password(Game_arcade members[],int i)
    {
        String password="";
        for(;;)
        {
            System.out.print("Please enter your password - ");
            password=ob.next();
            if(password.equals("0"))
                return(-1);
            in.delay(2);
            if(i==10)
            {
                if(password.equals(admin.password))
                {
                    System.out.println("Login Successful");
                    return(10);
                }
                else
                    System.out.println("Incorrect Password");
            }
            else
            {
                if(password.equals(members[i].password))
                {
                    System.out.println("Login Successful");
                    return(i);
                }
                else
                    System.out.println("Incorrect password");
            }
            in.delay(2);
        }
    }

    void admin(Game_arcade members[])
    {
        int choice;
        do
        {
            System.out.println("\fWELCOME ROHAN");
            System.out.println("Enter 1 to delete username");
            System.out.println("Enter 2 to add tickets to a user");
            System.out.println("Enter 3 to play games");
            System.out.println("Enter 0 to logout");
            choice=in.input_int();
            switch(choice)
            {
                case 1:
                delete_username(members);
                break;
                case 2:
                add_tickets(members);
                break;
                case 3:
                admin.main();
            }
        }
        while(choice!=0);
    }

    void add_tickets(Game_arcade members[])
    {
        System.out.print("\fEnter username - ");
        String username=ob.next();
        boolean check=false;
        int i;
        for(i=0;i<10;i++)
        {
            if(members[i].username.equals(username))
            {
                check=true;
                break;
            }
        }
        if(check)
        {
            System.out.print("Enter the number of tickets to be added - ");
            members[i].tickets+=in.input_int();
            System.out.println("Adding Tickets");
        }
        else
            System.out.println("Username not found");
        in.delay(2);
    }

    void main()
    {
        int choice;
        Game_arcade members[]=new Game_arcade[10];
        for(int i=0;i<10;i++)
            members[i]=new Game_arcade();
        admin.username="Rohan_Games";
        admin.password="P@$$W0RD";
        admin.tickets=1000000000;
        do
        {
            System.out.println("\f\t\t\t\t\t\tWELCOME\n");
            System.out.println("Enter 1 to create username");
            System.out.println("Enter 2 to login");
            System.out.println("Enter 0 to exit");
            choice=in.input_int();
            switch(choice)
            {
                case 1:
                int i;
                for(i=0;i<10;i++)
                {
                    if(members[i].username.equals(""))
                        break;
                }
                if(i==10)
                {
                    System.out.println("Sorry the gamesite is full");
                    in.delay(3);
                    break;
                }
                String username=create_username(members);
                if(username.equals("0"))
                    break;
                String password=create_password(members);
                if(password.equals("0"))
                    break;
                for(i=0;i<10;i++)
                {
                    if(members[i].username.equals(""))
                        break;
                }
                members[i].username=username;
                members[i].password=password;
                members[i].tickets=1000;
                break;
                case 2:
                int user=login_username(members);
                if(user==-1)
                    break;
                int p_user=login_password(members,user);
                if(p_user==-1)
                    break;
                in.delay(2);
                if(user==10)
                    admin(members);
                else
                    members[user].main();
                break;
            }
        }
        while(choice!=0);
    }
}

class accessory
{
    public int input_int()
    {
        int input;
        for(;;)
        {
            Scanner ob=new Scanner(System.in);
            if(!ob.hasNextInt())
            {
                System.out.println("Please enter an integer");
                continue;
            }
            else
            {
                input=ob.nextInt();
                if(input>=0)
                    break;
                else
                    System.out.println("Please enter a positive number");
            }
        }
        return(input);
    }

    public char input_letter_or_digit()
    {
        char input;
        for(;;)
        {
            Scanner ob=new Scanner(System.in);
            String input_string=ob.next();
            if(input_string.length()>1)
            {
                System.out.println("Please enter only one character");
                continue;
            }
            input=input_string.charAt(0);
            if(!Character.isLetterOrDigit(input))
                System.out.println("Please enter a letter or digit");
            else
                break;
        }
        return(input);
    }

    public char input_letter()
    {
        char input;
        for(;;)
        {
            Scanner ob=new Scanner(System.in);
            String input_string=ob.next();
            if(input_string.length()>1)
            {
                System.out.println("Please enter only one character");
                continue;
            }
            input=input_string.charAt(0);
            if(!Character.isLetter(input))
                System.out.println("Please enter a letter");
            else
                break;
        }
        return(input);
    }

    public void delay(int seconds)
    {
        try
        {
            Thread.sleep(seconds*1000);
        }
        catch(Exception e)
        {
        }
    }
}

class stone_paper_scissors
{
    accessory in=new accessory();
    int your_score=0;
    int computer_score=0;
    int win_score;
    void input_win_score()
    {
        System.out.print("\fEnter the win score - ");
        win_score=in.input_int();
        System.out.print("\f");
    }

    void scoring(int your_choice,int computer_choice)
    {
        in.delay(1);
        if(your_choice==1&&computer_choice==2)
        {
            System.out.println("Computer wins this round");
            computer_score++;
        }
        if(your_choice==1&&computer_choice==3)
        {
            System.out.println("You win this round");
            your_score++;
        }
        if(your_choice==2&&computer_choice==1)
        {
            System.out.println("You win this round");
            your_score++;
        }
        if(your_choice==2&&computer_choice==3)
        {
            System.out.println("Computer wins this round");
            computer_score++;
        }
        if(your_choice==3&&computer_choice==1)
        {
            System.out.println("Computer wins this round");
            computer_score++;
        }
        if(your_choice==3&&computer_choice==2)
        {
            System.out.println("You win this round");
            your_score++;
        }
        if(your_choice==computer_choice)
            System.out.println("This round is a tie");
    }

    void score_display()
    {
        System.out.println("-----------------------------------------");
        System.out.println("| Your Score\t| Computer's Score\t|");
        System.out.println("-----------------------------------------");
        System.out.println("| "+your_score+"\t\t| "+computer_score+"\t\t\t|");
        System.out.println("-----------------------------------------");
    }

    int input()
    {
        int your_choice;
        for(;;)
        {
            System.out.println("Enter 1 for stone");
            System.out.println("Enter 2 for paper");
            System.out.println("Enter 3 for scissors");
            System.out.println("Enter your choice");
            your_choice=in.input_int();
            if(your_choice>3&&your_choice<0)
                System.out.println("Please enter 0,1,2 or 3 as input");
            else
                break;
        }
        return(your_choice);
    }

    int play()
    {
        input_win_score();
        int your_choice=0;
        int computer_choice=0;
        for(;;)
        {
            if(your_score==win_score||computer_score==win_score)
                break;
            your_choice=input();
            System.out.print("\f");
            if(your_choice==1)
                System.out.println("You chose stone");
            if(your_choice==2)
                System.out.println("You chose paper");
            if(your_choice==3)
                System.out.println("You chose scissors");
            computer_choice=(int)(Math.random()*3)+1;
            if(computer_choice==1)
                System.out.println("Computer chose stone");
            if(computer_choice==2)
                System.out.println("Computer chose paper");
            if(computer_choice==3)
                System.out.println("Computer chose scissors");
            scoring(your_choice,computer_choice);
            score_display();
        }
        scoring(your_choice,computer_choice);
        if(computer_score>your_score)
        {
            System.out.println("Computer Wins");
            return(0);
        }
        else
        {
            System.out.println("You Win 100 tickets");
            return(100);
        }
    }
}

class hangman
{
    accessory in=new accessory();
    String database[]={"","toothpaste","steam","describe","ugliest","angry","spare","decide",
            "protective","horses","deceive","black","curly","puncture","excuse","beef","horse",
            "juvenile","baseball","delirious","damage","escape","meaty","detect","wacky","fly",
            "concerned","snore","appliance","doubt","new","futuristic","present","dress","dance",
            "motion","poor","screeching","expect","guarantee","insidious","fold","hope","craven",
            "eight","sip","overrated","slim","marked","bathe","thin","muddled","hallowed","blow",
            "instrument","scarf","violet","fallacious","copy","needless","record","crazy","oven",
            "vein","accept","show","capable","flimsy","noisy","locket","public","farflung","striped",
            "safe","towering","probable","mixed","tan","woozy","sleep","fretful","obsolate","start",
            "return","puffy","guiltless","amused","draconian","panoramic","comfortable","learn","slow",
            "increase","quarrelsome","doubtful","little","bashful","berry","roll","oatmeal","plasma",
            "clove","fairway","inmate","itch","resurrection"};
    char track[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    String ChosenWord="";
    int length;
    void ChooseWord()
    {
        ChosenWord=database[(int)(Math.random()*database.length)+1];
        ChosenWord=ChosenWord.toUpperCase();
        length=ChosenWord.length();
    }
    int WrongLetter=0;
    void hangmanImage(int count)
    {
        if(count==0)
        {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
        if(count==1)
        {
            System.out.println("   ____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
        }
        if(count==2)
        {
            System.out.println("   ____________");
            System.out.println("   |         _|_");
            System.out.println("   |        /. .\\");
            System.out.println("   |       |     |");
            System.out.println("   |        \\___/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if(count==3) 
        {
            System.out.println("   ____________");
            System.out.println("   |         _|_");
            System.out.println("   |        /. .\\");
            System.out.println("   |       |     |");
            System.out.println("   |        \\___/");
            System.out.println("   |          |");
            System.out.println("   |          |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if(count==4)
        {
            System.out.println("   ____________");
            System.out.println("   |         _|_");
            System.out.println("   |        /. .\\");
            System.out.println("   |       |     |");
            System.out.println("   |        \\___/");
            System.out.println("   |         _|");
            System.out.println("   |        / |");
            System.out.println("   |        ");
            System.out.println("___|___     ");
        }
        if(count==5) 
        {
            System.out.println("   ____________");
            System.out.println("   |         _|_");
            System.out.println("   |        /. .\\");
            System.out.println("   |       |     |");
            System.out.println("   |        \\___/");
            System.out.println("   |         _|_");
            System.out.println("   |        / | \\");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if(count==6)
        {
            System.out.println("   ____________");
            System.out.println("   |         _|_");
            System.out.println("   |        /. .\\");
            System.out.println("   |       |     |");
            System.out.println("   |        \\___/");
            System.out.println("   |         _|_");
            System.out.println("   |        / | \\");
            System.out.println("   |         / ");
            System.out.println("___|___     /   ");
        }
        if(count==7) 
        {
            System.out.println("   ____________");
            System.out.println("   |         _|_");
            System.out.println("   |        /. .\\");
            System.out.println("   |       |     |");
            System.out.println("   |        \\___/");
            System.out.println("   |         _|_");
            System.out.println("   |        / | \\");
            System.out.println("   |         / \\ ");
            System.out.println("___|___     /   \\");
            System.out.println("GAME OVER!");
        }
    }

    char input()
    {
        char input;
        for(;;)
        {
            input=Character.toUpperCase(in.input_letter_or_digit());
            boolean repeat=false;
            for(int i=0;i<track.length;i++)
            {
                if(track[i]==input)
                    repeat=true;
            }
            if(input=='0')
                break;
            else if(!repeat)
                System.out.println("You have already entered this letter");
            else if(Character.isLetter(input))
                break;
            else
                System.out.println("Invalid character");
        }
        return(input);
    }

    int play()
    {
        ChooseWord();
        char ScreenWord[]=new char[length];
        boolean win=false;
        System.out.println();
        for(int i=0;i<length;i++)
            ScreenWord[i]='_';
        for(;;)
        {
            hangmanImage(WrongLetter);
            String Word="";
            if(WrongLetter==7)
                break;
            for(int i=0;i<length;i++)
                Word=Word+ScreenWord[i];
            System.out.println();
            if(Word.equals(ChosenWord))
            {
                System.out.println("You win.");
                win=true;
                break;
            }
            for(int i=0;i<Word.length();i++)
                System.out.print(Word.charAt(i)+" ");
            System.out.println("\nTrack - ");
            for(int i=0;i<track.length;i++)
                System.out.print(track[i]+" ");
            System.out.println("\n\nEnter letter");
            char input=input();
            track[input-65]=' ';
            boolean CorrectLetter=false;
            for(int i=0;i<length;i++)
            {
                char ch=ChosenWord.charAt(i);
                if(ch==input)
                {
                    ScreenWord[i]=ch;
                    CorrectLetter=true;
                }
            }
            in.delay(1);
            if(CorrectLetter)
                System.out.println("\fCorrect Letter");
            if(!CorrectLetter)
            {
                System.out.println("\fWrong Letter");
                WrongLetter++;
            }
        }
        System.out.println("Word was - "+ChosenWord);
        if(win)
        {
            System.out.println("You win 150 tickets");
            return(150);
        }
        else
        {
            System.out.println("You lose");
            return(0);
        }
    }
}

class hand_cricket
{
    accessory in=new accessory();
    int overs;
    int player_score=0;
    int computer_score=0;
    int display=0;
    int i;
    int choice;
    int player_choice;
    int computer_choice;
    int player_input;
    int computer_input;
    void input_overs()
    {
        for(;;)
        {
            System.out.print("Enter the number of overs - ");
            overs=in.input_int();
            if(overs<0)
                System.out.print("\f");
            else
                break;
        }
    }

    boolean toss()
    {
        for(;;)
        {
            System.out.println("\fEnter - ");
            System.out.println("1 for heads");
            System.out.println("2 for tails");
            choice=in.input_int();
            if(choice==0||choice==1||choice==2)
                break;
        }
        int toss=(int)(Math.random()*2)+1;
        in.delay(2);
        if(toss==1)
            System.out.println("\fIt's Heads");
        else
            System.out.println("\fIt's Tails");
        in.delay(2);
        if(toss==choice)
            return(true);
        else
            return(false);
    }

    void bowling_batting()
    {
        boolean outcome=toss();
        System.out.print("\f");
        if(outcome)
        {
            for(;;)
            {
                System.out.println("Enter -");
                System.out.println("1 to bat");
                System.out.println("2 to bowl");
                player_choice=in.input_int();
                in.delay(1);
                if(player_choice==1)
                {
                    System.out.println("\fYou chose batting");
                    computer_choice=2;
                    break;
                }
                else if(player_choice==2)
                {
                    System.out.println("\fYou chose bowling");
                    computer_choice=1;
                    break;
                }
                else
                    System.out.print("\f");
            }
        }
        else
        {
            int choice=(int)(Math.random()*2)+1;
            if(choice==1)
            {
                System.out.println("Computer chose bowling");
                computer_choice=2;
                player_choice=1;
            }
            else if(choice==2)
            {
                System.out.println("Computer chose batting");
                computer_choice=1;
                player_choice=2;
            }
        }
        in.delay(2);
    }

    int input()
    {
        int input;    
        for(;;)
        {
            System.out.print("Enter a number from 1-10 - ");
            input=in.input_int();
            if(input<=10&&input>0)
                break;
        }
        return(input);
    }

    void inning_1()
    {
        System.out.print("\f");
        for(i=1;i<=overs*6;i++)
        {
            player_input=input();
            computer_input=(int)(Math.random()*10)+1;
            System.out.println("Computer's choice - "+computer_input);
            in.delay(1);
            System.out.print("\f");
            if(player_input==computer_input)
            {
                System.out.println("Out!");
                break;
            }
            else
            {
                if(player_choice==1)
                {
                    player_score=player_score+player_input;
                    System.out.println("Your Score - "+player_score);
                }
                else if(computer_choice==1)
                {
                    computer_score=computer_score+computer_input;
                    System.out.println("Computer's score - "+computer_score);
                }
            }
            if(i<overs*6)
                System.out.println("Overs - "+(i/6)+"."+(i%6));
        }
    }

    void inning_1_score()
    {
        if(i>overs*6)
            System.out.println("Overs - "+(i/6)+"."+((i%6)-1));
        else
            System.out.println("Overs - "+(i/6)+"."+(i%6));
    }

    void inning_2()
    {
        for(i=1;i<=overs*6;i++)
        {
            if(player_choice==1)
                System.out.println("Target - "+(player_score+1));
            else if(computer_choice==1)
                System.out.println("Target - "+(computer_score+1));
            player_input=input();
            computer_input=(int)(Math.random()*10)+1;
            System.out.println("\fComputer's choice - "+computer_input);
            in.delay(1);
            System.out.print("\f");
            if(player_input==computer_input)
            {
                System.out.println("Out!");
                break;
            }
            else
            {
                if(player_choice==2)
                {
                    player_score=player_score+player_input;
                    System.out.println("Your Score - "+player_score);
                    if(player_score>computer_score)
                    {
                        System.out.println("You win");
                        display++;
                        break;
                    }
                }
                else if(computer_choice==2)
                {
                    computer_score=computer_score+computer_input;
                    System.out.println("Computer's score - "+computer_score);
                    if(computer_score>player_score)
                    {
                        System.out.println("Computer wins");
                        display++;
                        break;
                    }
                }
            }
            in.delay(1);
            if(i<overs*6)
                System.out.println("Overs - "+(i/6)+"."+(i%6));
        }
    }

    int inning_2_score()
    {
        in.delay(1);
        if(i>overs*6)
            System.out.println("Overs - "+(i/6)+"."+((i%6)-1));
        else
            System.out.println("Overs - "+(i/6)+"."+(i%6));
        if(player_score>computer_score&&display==0)
        {
            System.out.println("You win by "+(player_score-computer_score)+" runs");
            System.out.println("You win 175 tickets");
            return(175);
        }
        else if(computer_score>player_score&&display==0)
        {
            System.out.println("Computer wins by "+(computer_score-player_score)+" runs");
            return(0);
        }
        else
        {
            System.out.println("Match tied");
            System.out.println("Tickets refunded");
            return(80);
        }
    }

    int play()
    {
        input_overs();
        bowling_batting();
        inning_1();
        inning_1_score();
        inning_2();
        int tickets=inning_2_score();
        return(tickets);
    }
}

class cows_and_bulls
{
    accessory in=new accessory();
    int digits;
    int player_value;
    int cows;
    int bulls;
    int value;
    int tries;
    int possibilities=1;
    void initialise_digits()
    {
        for(;;)
        {
            System.out.println("Enter the number of digits from 2 to 8");
            digits=in.input_int();
            if(digits>=2&&digits<=8)
                break;
            else
                System.out.println("Please enter a number of 2-8 digits only");
        }
        in.delay(1);
        System.out.println("\nTry and guess in "+(digits*3-3)+" tries");
        System.out.println("To win "+digits*100+" tickets");
    }

    void possibilities()
    {
        int t=9;
        for(int i=1;i<=digits;i++)
        {
            possibilities=possibilities*t;
            t--;
        }
    }

    void initialise_number(int number[])
    {
        int c=0;
        boolean t=true;
        while(c!=digits)
        {
            c=0;
            for(int i=0;i<digits;i++)
                number[i]=(int)(Math.random()*8+1);
            for(int i=0;i<digits;i++)
            {
                for(int j=i+1;j<digits;j++)
                {
                    if(number[i]==number[j])
                    {
                        t=false;
                        break;
                    }
                }
                if(t)
                    c++;
                t=true;
            }
        }
    }

    void initialise_value(int number[])
    {
        for(int i=0;i<digits;i++)
            value=value*10+number[i];
    }

    void input(int input[],int track[][])
    {
        for(;;)
        {
            System.out.print("enter your number - ");
            player_value=in.input_int();
            boolean valid=true;
            boolean repeat=false;
            int t=player_value;
            for(int i=0;i<tries;i++)
            {
                if(player_value==track[0][i])
                    repeat=true;
            }
            for(int i=digits-1;i>=0;i--)
            {
                input[i]=t%10;
                t=t/10;
            }
            for(int i=0;i<digits;i++)
            {
                for(int j=i+1;j<digits;j++)
                {
                    if(input[i]==input[j])
                        valid=false;
                }
                if(input[i]==0)
                    valid=false;
            }
            if(player_value==0)
                break;
            else if(player_value>=(int)(Math.pow(10,digits))||player_value<(int)(Math.pow(10,digits-1))||!valid)
                System.out.println("Illegal number.");
            else if(repeat)
                System.out.println("This number has already been input by you");
            else
                break;
        }
    }

    void track(int track[][])
    {
        track[0][tries-1]=player_value;
        track[1][tries-1]=cows;
        track[2][tries-1]=bulls;
        System.out.print("\f------------------------------------");
        System.out.print("\n| Try\t| Number   | Cows | Bulls |");
        System.out.print("\n------------------------------------");
        for(int i=0;i<tries;i++)
        {
            System.out.print("\n| "+(i+1)+"\t| "+track[0][i]+" ");
            for(int j=digits;j<8;j++)
                System.out.print(" ");
            System.out.print("| "+track[1][i]+"    | "+track[2][i]+"     |");
        }
        System.out.print("\n------------------------------------");
        System.out.println("\n");
    }

    int play()
    {
        initialise_digits();
        possibilities();
        in.delay(4);
        System.out.print("\f");
        int number[]=new int[digits];
        int input[]=new int[digits];
        int track[][]=new int[3][possibilities];
        initialise_number(number);
        initialise_value(number);
        for(tries=1;tries<=digits*5;tries++)
        {
            input(input,track);
            in.delay(1);
            if(player_value==0)
                break;
            cows=0;
            bulls=0;
            for(int i=0;i<digits;i++)
            {
                if(number[i]==input[i])
                    bulls++;
                else
                {
                    for(int j=0;j<digits;j++)
                    {
                        if(input[j]==number[i])
                            cows++;
                    }
                }
            }
            track(track);
            if(value==player_value)
            {
                System.out.println("You win!");
                break;
            }
        }
        System.out.println("The number was - "+value);
        if(tries<=digits*3-3)
        {
            System.out.println("You win - "+digits*100+" tickets");
            return(digits*100);
        }
        else
        {
            System.out.println("You did not complete it in "+(digits*3-3)+" tries");
            return(0);
        }
    }
}

class tic_tac_toe
{
    accessory in=new accessory();
    char board[][]=new char[3][3];
    void input_row_column_player_X()
    {
        boolean check=true;
        int row_X;
        int column_X;
        do
        {
            System.out.print("Player X enter your row number - ");
            row_X=in.input_int()-1;
            System.out.print("Player X enter your column number - ");
            column_X=in.input_int()-1;
            check=check_input(row_X,column_X);
        }
        while(!check);
        board[row_X][column_X]='X';
    }

    void input_row_column_player_O()
    {
        boolean check=true;
        int row_O;
        int column_O;
        do
        {
            System.out.print("Player O enter your row number - ");
            row_O=in.input_int()-1;
            System.out.print("Player O enter your column number - ");
            column_O=in.input_int()-1;
            check=check_input(row_O,column_O);
        }
        while(!check);
        board[row_O][column_O]='O';
    }

    boolean check_input(int row,int column)
    {
        boolean check=true;
        if(row<0||row>2||column<0||column>2)
        {
            System.out.println("This space is invalid");
            check=false;
        }
        else if(board[row][column]=='X'||board[row][column]=='O')
        {
            System.out.println("This space has been occupied");
            check=false;
        }
        return(check);
    }

    void initialise_board()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                board[i][j]=' ';
        }
    }

    void display_board()
    {
        System.out.println("\f    1   2   3 ");
        System.out.println("  -------------");
        System.out.println("1 | "+board[0][0]+" | "+board[0][1]+" | "+board[0][2]+" |");
        System.out.println("  -------------");
        System.out.println("2 | "+board[1][0]+" | "+board[1][1]+" | "+board[1][2]+" |");
        System.out.println("  -------------");
        System.out.println("3 | "+board[2][0]+" | "+board[2][1]+" | "+board[2][2]+" |");
        System.out.println("  -------------");
    }

    boolean check_player_wins(char player)
    {
        boolean check=false;
        if(board[0][0]==player&&board[0][1]==player&&board[0][2]==player
        ||board[1][0]==player&&board[1][1]==player&&board[1][2]==player
        ||board[2][0]==player&&board[2][1]==player&&board[2][2]==player
        ||board[0][0]==player&&board[1][0]==player&&board[2][0]==player
        ||board[0][1]==player&&board[1][1]==player&&board[2][1]==player
        ||board[0][2]==player&&board[1][2]==player&&board[2][2]==player
        ||board[0][0]==player&&board[1][1]==player&&board[2][2]==player
        ||board[2][0]==player&&board[1][1]==player&&board[0][2]==player)
            check=true;
        return(check);
    }

    void play()
    {
        initialise_board();
        boolean player_X_win=false;
        boolean player_O_win=false;
        display_board();
        for(int i=1;i<=9;i++)
        {
            if(i%2!=0)
                input_row_column_player_X();
            else
                input_row_column_player_O();
            in.delay(1);
            player_X_win=check_player_wins('X');
            player_O_win=check_player_wins('O');
            display_board();
            if(player_X_win||player_O_win)
                break;
        }
        if(player_X_win)
            System.out.println("X wins");
        else if(player_O_win)
            System.out.println("O wins");
        else
            System.out.println("It's a tie");
    }
} 

class guess_the_number
{
    accessory in=new accessory();
    long number;
    long player_number;
    int digits;
    int tries=0;
    void initialise_digits()
    {
        for(;;)
        {
            System.out.println("Enter the number of digits");
            digits=in.input_int();
            if(digits>0&&digits<=10)
                break;
            else
                System.out.println("Please enter a number from 1 to 10");
        }
        System.out.println("\nTry and guess in "+(digits*5-5)+" tries");
        System.out.println("To win "+digits*50+" tickets");
        in.delay(4);
    }

    void initialise_number()
    {
        number=(long)(Math.random()*(Math.pow(10,digits)-Math.pow(10,digits-1))-1)+(int)(Math.pow(10,digits-1));
    }

    long input_player_number(long number_track[])
    {
        boolean repeated_number=false;
        for(;;)
        {
            player_number=in.input_int();
            for(int i=0;i<tries;i++)
            {
                if(player_number==number_track[i])
                    repeated_number=true;
            }
            in.delay(1);
            if(player_number==0)
                break;
            if(player_number<(int)(Math.pow(10,digits-1))||player_number>(int)(Math.pow(10,digits)-1))
                System.out.println("Invalid number");
            else if(repeated_number)
                System.out.println("This number has already be inputted by you");
            else
                break;
        }
        return(player_number);
    }

    void track(String greater_smaller_track[],long number_track[])
    {
        System.out.print("\f------------------------------------");
        for(int j=6;j<digits;j++)
            System.out.print("-");
        System.out.print("\n| Try\t| Number");
        for(int j=6;j<digits;j++)
            System.out.print(" ");
        System.out.print(" | Greater/Smaller |\n");
        System.out.print("------------------------------------");
        for(int j=6;j<digits;j++)
            System.out.print("-");
        for(int i=0;i<tries;i++)
        {
            System.out.print("\n| "+(i+1)+"\t| "+number_track[i]);
            for(int j=digits;j<=5;j++)
                System.out.print(" ");
            System.out.print(" | "+greater_smaller_track[i]+"         |");
        }
        System.out.print("\n------------------------------------");
        for(int j=6;j<digits;j++)
            System.out.print("-");
        System.out.println();
    }

    int play()
    {
        initialise_digits();
        initialise_number();
        long number_track[]=new long[(int)(Math.pow(10,digits)-Math.pow(10,digits-1))];
        String greater_smaller_track[]=new String[(int)(Math.pow(10,digits)-Math.pow(10,digits-1))];
        String greater_smaller=null;
        System.out.print("\f");
        for(tries=1;tries<=(int)(Math.pow(10,digits)-Math.pow(10,digits-1));tries++)
        {
            System.out.print("Enter your number - ");
            player_number=input_player_number(number_track);
            if(player_number==number)
            {
                System.out.println("You Win!");
                break;
            }
            else if(player_number>number)
                greater_smaller="greater";
            else if(player_number<number)
                greater_smaller="smaller";
            number_track[tries-1]=player_number;
            greater_smaller_track[tries-1]=greater_smaller;
            track(greater_smaller_track,number_track);
        }
        System.out.println("The number was - "+number);
        if(tries<=digits*5-5)
        {
            System.out.println("You win "+digits*50+" tickets");
            return(digits*50);
        }
        else
        {
            System.out.println("You did not guess it in "+(digits*5-5)+" tries");
            return(0);
        }
    }
} 

class quiz
{
    accessory in=new accessory();
    String questions[]={
            "Which of the following is not a Ved?",
            "Which was Amitabh Bachchan's first super hit movie?",
            "Which of the following cricketer has scored a century in his first test match?",
            "Which of the following is true for the value of G on Earth and Moon?",
            "What was the name of the third Musketeer in the book - The Three Musketeers?",
            "Who developed the programming language 'Python'?",
            "Who was the first foreign minister of India?",
            "Where is the 'Red Spot' found in out Solar System?",
            "Which is the lightest radioactive element?",
            "In which state did the Bharatiya Janta Party (BJP) form their Government for the first time?",
            "Which of the following years was celebrated as the World Communication Year?",
            "Which city of India was first of all affected by plague?",
            "Which colonial power ended its involvement in India by selling the rights of the Nicobar Islands to the British on October 18, 1868?",
            "Which of these U.S. Presidents appeared on the television series Laugh-In?",
            "What was the name given to the supercluster of galaxies, discovered by a team of Indian astronomers in 2017?",
            "Who, in 1978, became the first person to be born in the continent of Antarctica?",
            "Who is credited with inventing the first mass-produced helicopter?",
            "Who did artist Grant Wood use as the model for the farmer in his classic painting American Gothic?",
            "Who commanded the ‘Hector’, the first British trading ship to land at Surat?",};
    String options[][]={
            {"A. Rugved","B. Atharvaved","C. Yuvaved","D. Yajurved"},
            {"A. Zanjeer","B. Sholay","C. Saat Hindustani","D. Kaalia"},
            {"A. Virat Kohli","B. Sachin Tendulkar","C. Prithvi Shaw","D. Rishabh Pant"},
            {"A. G for moon is six times less as compared to Earth","B. G for Moon and Earth are equal","C. G for Moon is 36 times greater than that on Earth","D. G for Moon is 6 times greater than that on Earth"},
            {"A. Porthos","B. Aramis","C. D'Artagnan","D. Athos"},
            {"A. James Gosling","B. Denis Ritchie","C. John G. Kemeny","D. Guido Van Rossum"},
            {"A. Indira Gandhi","B. Lal Bahadur Shastri","C. Gulzarilal Nanda","D. Jawaharlal Nehru"},
            {"A. Mars","B. Jupiter","C. Uranus","D. Neptune"},
            {"A. Radiocarbon-14","B. Duterium-2","C. Tritium-3","D. Bismuth-208"},
            {"A. Assam","B. Uttar Pradesh","C. Gujarat","D. Madhya Pradesh"},
            {"A. 1981","B. 1982","C. 1983","D. 1984"},
            {"A. Jaipur","B. Bombay","C. Kanpur","D. Surat"},
            {"A. Belgium","B. Denmark","C. Italy","D. France"},
            {"A. Lyndon Johnson","B. Richard Nixon","C. Jimmy Carter","D. Gerald Ford"},
            {"A. Durga","B. Laxmi","C. Parvati","D. Saraswati"},
            {"A. Emilio Palma","B. James Weddell","C. Nathaniel Palmer","D. Chales Wilkes"},
            {"A. Igor Sikorsky","B. Ferdinand Von Zeppelin","C. Elmer Sperry","D. Gottlieb Daimler"},
            {"A. Travelling salesman","B. Local Sherrif","C. His Dentist","D. His butcher"},
            {"A. Paul Canning","B. William Hawkins","C. Thomas Roe","D. James Lancaster"},};
    char answer[]={'C','A','C','B','A','D','D','B','C','A','C','D','B','B','D','A','A','C','B'};
    String price[]={
            "10 tickets   ",
            "20 tickets   ",
            "40 tickets   ",
            "50 tickets   ",
            "75 tickets   ",
            "80 tickets   ",
            "100 tickets  ",
            "200 tickets  ",
            "400 tickets  ",
            "500 tickets  ",
            "750 tickets  ",
            "800 tickets  ",
            "1000 tickets ",
            "2000 tickets ",
            "4000 tickets ",
            "5000 tickets ",
            "7500 tickets ",
            "8000 tickets ",
            "10000 tickets",};
    String lifelines[]={"1. Double Dip","2. 50:50","3. Expert Advise"};
    char input()
    {
        char input;
        for(;;)
        {        
            input=Character.toUpperCase(in.input_letter_or_digit());
            if(input=='A'||input=='B'||input=='C'||input=='D'||input=='1'||input=='2'||input=='3'||input=='0')
                break;
            else
                System.out.println("Illegal character");
        }
        return(input);
    }

    char input_double_dip()
    {
        char input;
        for(;;)
        {        
            input=Character.toUpperCase(in.input_letter());
            if(input=='A'||input=='B'||input=='C'||input=='D')
                break;
            else
                System.out.println("Illegal character");
        }
        return(input);
    }

    boolean double_dip(int question_no)
    {
        System.out.print("Enter your first choice - ");
        char choice_1=input_double_dip();
        System.out.print("Enter your second choice - ");
        char choice_2=input_double_dip();
        if(choice_1==answer[question_no]||choice_2==answer[question_no])
            return(true);
        else
            return(false);
    }

    void fifty_fifty(int question_no)
    {
        int eliminate_1,eliminate_2;
        for(;;)
        {
            eliminate_1=(int)(Math.random()*4);
            eliminate_2=(int)(Math.random()*4);
            if((char)(eliminate_1+65)!=answer[question_no]&&
            (char)(eliminate_2+65)!=answer[question_no]&&
            eliminate_1!=eliminate_2)
                break;
        }
        options[question_no][eliminate_1]=(char)(eliminate_1+65)+".";
        options[question_no][eliminate_2]=(char)(eliminate_2+65)+".";
    }

    void expert_advise(int question_no)
    {
        int probability=(int)(Math.random()*100);
        if(probability<=95)
            System.out.println("The correct answer might be "+answer[question_no]);
        else
            System.out.println("The correct answer might be "+(char)((int)(Math.random()*4)+65));
    }

    void display_price(int question_no)
    {
        System.out.println("\f-------------------------");
        System.out.println("| Q.no\t| Price         |");
        System.out.println("-------------------------");
        for(int i=18;i>=0;i--)
        {
            if(i==question_no-1)
            {
                System.out.println("-------------------------");
                System.out.println("| "+(i+1)+"\t| "+price[i]+" |");
                System.out.println("-------------------------");
                continue;
            }
            System.out.println("| "+(i+1)+"\t| "+price[i]+" |");
        }
        System.out.println("-------------------------");
    }

    int play()
    {
        boolean use_double_dip=false;
        boolean use_fifty_fifty=false;
        boolean use_expert_advise=false;
        boolean quit=false;
        int i;
        boolean use_1_lifeline=false;
        int tickets=0;
        for(i=1;i<=19;i++)
        {
            boolean correct_answer=false;
            display_price(i);
            System.out.println("Question "+i+"\n");
            System.out.println("Price - "+price[i-1]);
            System.out.println(questions[i-1]);
            for(int j=0;j<4;j++)
                System.out.println(options[i-1][j]);
            System.out.println("\nLifelines - ");
            for(int j=0;j<3;j++)
                System.out.println(lifelines[j]);
            System.out.println("\nEnter 0 to quit");
            char choice=input();
            if(choice==answer[i-1])
                correct_answer=true;
            if(choice=='1'&&!use_double_dip&&!use_1_lifeline)
            {
                in.delay(2);
                correct_answer=double_dip(i-1);
                use_double_dip=true;
                use_1_lifeline=true;
                lifelines[0]="1.";
            }
            else if(choice=='1'&&use_double_dip)
            {
                System.out.println("You have already used double dip");
                in.delay(2);
                i--;
                continue;
            }
            else if(choice=='1'&&use_1_lifeline)
            {
                System.out.println("You have already used 1 lifeline");
                in.delay(2);
                i--;
                continue;
            }
            if(choice=='2'&&!use_fifty_fifty&&!use_1_lifeline)
            {
                fifty_fifty(i-1);
                i--;
                use_fifty_fifty=true;
                use_1_lifeline=true;
                lifelines[1]="2.";
                continue;
            }
            else if(choice=='2'&&use_fifty_fifty)
            {
                System.out.println("You have already used 50:50");
                in.delay(2);
                i--;
                continue;
            }
            else if(choice=='2'&&use_1_lifeline)
            {
                System.out.println("You have already used 1 lifeline");
                in.delay(2);
                i--;
                continue;
            }
            if(choice=='3'&&!use_expert_advise&&!use_1_lifeline)
            {
                expert_advise(i-1);
                in.delay(2);
                i--;
                use_expert_advise=true;
                use_1_lifeline=true;
                lifelines[2]="3.";
                continue;
            }
            else if(choice=='3'&&use_expert_advise)
            {
                System.out.println("You have already used expert advise");
                in.delay(2);
                i--;
                continue;
            }
            else if(choice=='3'&&use_1_lifeline)
            {
                System.out.println("You have already used 1 lifeline");
                in.delay(2);
                i--;
                continue;
            }
            if(choice=='0')
            {
                quit=true;
                break;
            }
            System.out.println();
            in.delay(i);
            if(correct_answer)
            {
                System.out.println(answer[i-1]+" is the right answer");
                System.out.println("You have won "+price[i-1]);
                in.delay(3);
            }
            else
            {
                System.out.println("Wrong answer");
                System.out.println("The correct answer was "+answer[i-1]);
                break;
            }
            use_1_lifeline=false;
        }
        if(quit)
        {
            System.out.println("Well played!");
            System.out.println("Questions answered correctly - "+(i-1));
            if(i==1)
            {
                System.out.println("You have won 0 tickets");
                tickets=0;
            }
            else
            {
                System.out.println("You have won "+price[i-2]);
                tickets=Integer.parseInt(price[i-2].substring(0,price[i-2].lastIndexOf('0')+1));
            }
        }
        else if(i<19)
        {
            System.out.println("Still well played!");
            System.out.println("Questions answered correctly - "+(i-1));
            System.out.print("But you have dropped down to - ");
            if(i<7)
                System.out.println(" 0 tickets");
            else if(i<13)
            {
                System.out.println(" 100 tickets");
                tickets=100;
            }
            else if(i<19)
            {
                System.out.println(" 1000 tickets");
                tickets=1000;
            }
        }
        else
        {
            System.out.println("CONGRATULATIONS! You won 10000 tickets");
            tickets=10000;
        }
        return(tickets);
    }
} 

class deal_or_no_deal
{
    accessory in=new accessory();
    String tickets[]={"1   ","2   ","3   ","4   ","5   ","6   ","7   ","8   ","10  ","20  ","50  ","75  ","100 ","250 ","500 ","1000"};
    int ticket[]={1,2,3,4,5,6,7,8,10,20,50,75,100,150,250,500};
    String case_numbers[]={
            "1 ","2 ","3 ","4 ",
            "5 ","6 ","7 ","8 ",
            "9 ","10","11","12",
            "13","14","15","16"};
    int deal;
    int your_case_no;
    int your_case;
    int tickets_won;
    void initialise_cases(int cases[])
    {
        String t[]={"1","2","3","4","5","6","7","8","10","20","50","75","100","250","500","1000"};
        for(int i=0;i<16;i++)
        {
            int random=(int)(Math.random()*16);
            if(t[random].equals(""))
            {
                i--;
                continue;
            }
            cases[i]=Integer.parseInt(t[random]);
            t[random]="";
        }
    }

    int accept_case_number()
    {
        int input;
        for(;;)
        {
            input=in.input_int();
            if(input<0||input>16)
            {
                System.out.println("Enter case from 1-16");
                continue;
            }
            if(!(case_numbers[input-1].equals("  ")))
                break;
            else
                System.out.println("This case has already been chosen");
        }
        return(input);
    }

    int deal(int c,int cases[],int a)
    {
        int t=0;
        for(int i=0;i<16;i++)
            t=t+cases[i];
        return((t*2)/(c*a));
    }

    void accept_case(int cases[])
    {
        System.out.println("Choose your personal case");
        your_case_no=accept_case_number();
        your_case=cases[your_case_no-1];
        case_numbers[your_case_no-1]="  ";
    }

    void remove_tickets(int n)
    {
        String s=Integer.toString(n);
        for(int i=0;i<16;i++)
        {
            if(tickets[i].startsWith(s))
            {
                tickets[i]="    ";
                break;
            }
        }
    }

    void display()
    {
        System.out.println("\fCase numbers -");
        int k=0;
        for(int i=3;i>=0;i--)
        {
            for(int j=0;j<=3;j++,k++)
                System.out.print(case_numbers[k]+" ");
            System.out.println();
        }
        System.out.println("\nPrices");
        for(int i=0;i<=7;i++)
            System.out.println(tickets[i]+" tickets\t"+tickets[i+8]+" tickets");
        System.out.println("\nYour case number - "+your_case_no);
        System.out.println();
    }

    int play()
    {
        int cases[]=new int[16];
        initialise_cases(cases);
        accept_case(cases);
        int c=16;
        int ch=0;
        for(int i=5;i>=2;i--)
        {
            for(int j=i;j>=1;j--)
            {
                display();
                System.out.println("Choose "+j+" cases");
                int case_no=accept_case_number();
                int t=case_no-1;
                in.delay(1);
                System.out.println(cases[t]+" tickets");
                in.delay(1);
                remove_tickets(cases[t]);
                cases[t]=0;
                ticket[t]=0;
                case_numbers[t]="  ";
                c--;
            }
            display();
            deal=deal(c,cases,i);
            System.out.println("Deal - "+deal+" tickets");
            System.out.println("Enter 1 for deal and 2 for no deal");
            for(;;)
            {
                ch=in.input_int();
                if(ch==1)
                {
                    System.out.println("Deal!");
                    break;
                }
                else if(ch==2)
                {
                    System.out.println("No deal!");
                    break;
                }
                else
                    System.out.println("Please enter 1 or 2");
            }
            if(ch==1)
                break;
            in.delay(2);
        }
        if(ch==1)
        {
            System.out.println("You win - "+deal+" tickets");
            System.out.println("Your case - "+your_case+" tickets");
            tickets_won=deal;
        }
        else
        {
            System.out.println("You win - "+your_case+" tickets");
            tickets_won=your_case;
        }
        return(tickets_won);
    }
} 

class ticket_vender
{
    accessory in=new accessory();
    void possibilities()
    {
        System.out.println("\f------------------");
        System.out.println("| %    | Tickets |");
        System.out.println("| 0.01 | 10000   |");
        System.out.println("| 0.1  | 1000    |");
        System.out.println("| 1    | 100     |");
        System.out.println("| 40   | 10      |");
        System.out.println("| Rest | 1       |");
        System.out.println("------------------");
    }

    int play()
    {
        possibilities();
        int probability=(int)(Math.random()*10000)+1;
        System.out.print("Tickets won - ");
        in.delay(3);
        if(probability==1)
        {
            System.out.println(10000);
            return(10000);
        }
        else if(probability<=10)
        {
            System.out.println(1000);
            return(1000);
        }
        else if(probability<=100)
        {
            System.out.println(100);
            return(100);
        }
        else if(probability<=4000)
        {
            System.out.println(10);
            return(10);
        }
        else
        {
            System.out.println(1);
            return(1);
        }
    }
} 

class Game_arcade
{
    String username="";
    String password="";
    int tickets=0;
    accessory in=new accessory();
    boolean check_tickets(int cost)
    {
        if(cost>tickets)
        {
            System.out.println("You don't have enough tickets");
            return(false);
        }
        else
            return(true);
    }

    void main()
    {
        int choice;
        for(;;)
        {
            System.out.print("\f");
            System.out.println("                  *******         *******       **       **     ***********");
            System.out.println("                 *********       *********      ***     ***     ***********");
            System.out.println("                **       **     **       **     ****   ****     **         ");
            System.out.println("                **              **       **     ** ** ** **     **         ");
            System.out.println("                **              **       **     **  ***  **     ***********");
            System.out.println("                **   ******     ***********     **   *   **     ***********");
            System.out.println("                **   ******     ***********     **       **     **         ");
            System.out.println("                **       **     **       **     **       **     **         ");
            System.out.println("                **       **     **       **     **       **     **         ");
            System.out.println("                 *********      **       **     **       **     ***********");
            System.out.println("                  *******       **       **     **       **     ***********");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("  *******       *********         *******         *******       *********       ***********");
            System.out.println(" *********      **********       *********       *********      **********      ***********");
            System.out.println("**       **     **       **     **       **     **       **     **       **     **         ");
            System.out.println("**       **     **       **     **              **       **     **       **     **         ");
            System.out.println("**       **     **********      **              **       **     **       **     ***********");
            System.out.println("***********     *********       **              ***********     **       **     ***********");
            System.out.println("***********     ****            **              ***********     **       **     **         ");
            System.out.println("**       **     **  **          **              **       **     **       **     **         ");
            System.out.println("**       **     **    **        **       **     **       **     **       **     **         ");
            System.out.println("**       **     **      **       *********      **       **     **********      ***********");
            System.out.println("**       **     **       **       *******       **       **     *********       ***********");
            System.out.println("\n\n\nTickets - "+tickets);
            System.out.println("---------------------------------------------------------");
            System.out.println("| Enter | To play              | Ticket Cost | Win Upto |");
            System.out.println("---------------------------------------------------------");
            System.out.println("|   1   | Stone Paper Scissors | 50          | 100      |");
            System.out.println("|   2   | Hangman              | 70          | 150      |");
            System.out.println("|   3   | Hand Cricket         | 80          | 175      |");
            System.out.println("|   4   | Cows and Bulls       | 100         | 800      |");
            System.out.println("|   5   | Tic Tac Toe          | 0           | --       |");
            System.out.println("|   6   | Guess the Number     | 50          | 500      |");
            System.out.println("|   7   | Quiz                 | 100         | 10000    |");
            System.out.println("|   8   | Deal or No Deal      | 100         | 1000     |");
            System.out.println("|   9   | Ticket Vender        | 10          | 10000    |");
            System.out.println("|   0   | Logout               | --          | --       |");
            System.out.println("---------------------------------------------------------");
            choice=in.input_int();
            System.out.print("\f");
            switch(choice)
            {
                case 1:
                if(check_tickets(50))
                {
                    tickets-=50;
                    stone_paper_scissors ob1=new stone_paper_scissors();
                    tickets+=ob1.play();
                }
                in.delay(5);
                break;
                case 2:
                if(check_tickets(70))
                {
                    tickets-=70;
                    hangman ob2=new hangman();
                    tickets+=ob2.play();
                }
                in.delay(5);
                break;
                case 3:
                if(check_tickets(80))
                {
                    tickets-=80;
                    hand_cricket ob3=new hand_cricket();
                    tickets+=ob3.play();
                }
                in.delay(5);
                break;
                case 4:
                if(check_tickets(100))
                {
                    tickets-=100;
                    cows_and_bulls ob4=new cows_and_bulls();
                    tickets+=ob4.play();
                }
                in.delay(5);
                break;
                case 5:
                tic_tac_toe ob5=new tic_tac_toe();
                ob5.play();
                in.delay(5);
                break;
                case 6:
                if(check_tickets(50))
                {
                    tickets-=50;
                    guess_the_number ob6=new guess_the_number();
                    tickets+=ob6.play();
                }
                in.delay(5);
                break;
                case 7:
                if(check_tickets(100))
                {
                    tickets-=100;
                    quiz ob7=new quiz();
                    tickets+=ob7.play();
                }
                in.delay(5);
                break;
                case 8:
                if(check_tickets(100))
                {
                    tickets-=100;
                    deal_or_no_deal ob8=new deal_or_no_deal();
                    tickets+=ob8.play();
                }
                in.delay(5);
                break;
                case 9:
                if(check_tickets(10))
                {
                    tickets-=10;
                    ticket_vender ob9=new ticket_vender();
                    tickets+=ob9.play();
                }
                in.delay(5);
                break;
            }
            if(choice==0)
            {
                System.out.println("Logging out");
                in.delay(3);
                break;
            }
        }
    }
}