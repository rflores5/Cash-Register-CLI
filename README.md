# Cash-Register-CLI
This project was created using Java 11 and built using maven 3.8.7. The project is already built, so after downloading it you can run it by going into the target\classes folder and running "java Main" from the terminal. If you need to build it, this can be done with Maven from the "Cash Register" directory that contains the pom.xml file with the command "mvm clean install". The program can then be run with the command 'mvn exec:java -Dexec.mainClass="Main"'. 

Upon running the program it will output "ready" to the console to show that it is ready to receive input commands. It supports five types of commands with the following formats:
1) get
Displays the current amount of each denomination of bills in the cash register

2) put # # # # #
Puts the corresponding number of each denomincation of bills into the register then displays the resulting balance. It must have 5 integer values following 'put' with spaces between them.

3) take # # # # #
Takes out the corresponding number of each denomination of bills from the register then displays the resulting balance. It must have 5 integer values following 'take' with spaces between them. 

4) change #
Gives out change for the given amount using the least amount of total bills given the current register balance. It will display the change and deduct this amount from the balance in the background. If the exact change can not be provided it will notify the user. The input needs to have one integer value following 'change'.

5) quit
Quits the program.
