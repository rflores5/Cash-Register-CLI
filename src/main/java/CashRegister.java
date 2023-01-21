public class CashRegister {

    private int totalAmount = 0;
    private int twenty;
    private int ten;
    private int five;
    private int two;
    private int one;

    public CashRegister(){
        this.twenty = 0;
        this.ten = 0;
        this.five = 0;
        this.two = 0;
        this.one = 0;
    }

    //evaluate which command to run based on user input
    public boolean parseInput(String line){
        String inputError = "Invalid input";
        try{
            boolean keepRunning = true;
            String[] values = line.split(" ");
            String command = values[0];
            switch(command){
                case "get":
                    print(getCurrentAmount());
                    break;
                case "put":
                    if (values.length == 6) {
                        print(addCash(values[1], values[2], values[3], values[4], values[5]));
                    }else{
                        print(inputError);
                    }
                    break;
                case "take":
                    if (values.length == 6) {
                        print(subtractCash(values[1], values[2], values[3], values[4], values[5]));
                    }else{
                        print(inputError);
                    }
                    break;
                case "change":
                    if (values.length == 2){
                        print(getChange(values[1]));
                    }else {
                        print(inputError);
                    }
                    break;
                case "quit":
                    keepRunning = false;
                    break;
            }
            return keepRunning;
        }catch (Exception e){
            print(inputError);
            return true;
        }
    }

    public void setTotalAmount(){
        this.totalAmount = 20*twenty + 10*ten + 5*five + 2*two + 1*one;
    }

    public String getCurrentAmount(){
        return "$" + totalAmount + " " + twenty + " " + ten + " " + five + " " + two + " " + one;
    }

    public void print(String str){
        System.out.println(str);
    }

    public String addCash(String twenty, String ten, String five, String two, String one) throws NumberFormatException{
        String result;
        try{
            this.twenty += Integer.parseInt(twenty);
            this.ten += Integer.parseInt(ten);
            this.five += Integer.parseInt(five);
            this.two += Integer.parseInt(two);
            this.one += Integer.parseInt(one);
            setTotalAmount();
            result = getCurrentAmount();
        } catch (Exception e){
            result = "Unable to parse input";
        }
        return result;
    }


    public boolean enoughCash(int twenty, int ten, int five, int two, int one){
        return (this.twenty >= twenty) && (this.ten >= ten) && (this.five >= five) && (this.two >= two) && (this.one >= one);
    }


    public String subtractCash(int twenty, int ten, int five, int two, int one){
        String result;
        if(enoughCash(twenty, ten, five, two, one)){
            this.twenty -= twenty;
            this.ten -= ten;
            this.five -= five;
            this.two -= two;
            this.one -= one;
            setTotalAmount();
            result = getCurrentAmount();
        }else{
            result = "Not enough cash available";
        }
        return result;
    }

    public String subtractCash(String twenty, String ten, String five, String two, String one) throws NumberFormatException{
        String output;
        try {
            output = subtractCash(Integer.parseInt(twenty), Integer.parseInt(ten), Integer.parseInt(five), Integer.parseInt(two), Integer.parseInt(one));
        }catch (Exception e){
            output = "Unable to parse input";
        }
        return output;
    }

    //Uses ChangeCalculator class to determine if exact change can be given and returns it
    public String getChange(String amount){
        int change = Integer.parseInt(amount);
        String error = "Unable to provide exact change";
        String output;
        if (change > totalAmount){
            output = error;
        } else if( change == totalAmount){
            output = this.twenty + " " + this.ten + " " + this.five + " " + this.two + " " + this.one;
            subtractCash(this.twenty,this.ten, this.five, this.two, this.one);
        } else{
            ChangeCalculator calculator = new ChangeCalculator(this.twenty, this.ten, this.five, this.two, this.one);
            int[] den = calculator.findChange(change);
            if (den == null){
                output = error;
            }else{
                output = den[0] + " " + den[1] + " " + den[2] + " " + den[3] + " " + den[4];
                subtractCash(den[0], den[1], den[2], den[3], den[4]);
            }
        }
        return output;
    }
}
