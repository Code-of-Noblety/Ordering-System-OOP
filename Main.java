package OrderingSystem;

import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    String currentmenu;
    ArrayList<MenuItem> items = new ArrayList<>();

    Menu(String currentmenu) {
        this.currentmenu = currentmenu;
    }


    void displayMenu() {
        System.out.println(this.currentmenu);
         Main.Formatter(2,"=",70,1);
        System.out.printf("%-10s %-25s %-10s%n", "ID", "Item Name", "Price");
         Main.Formatter(2,"=",70,1);
        for (MenuItem item : items) {
          System.out.printf("%-10s %-25s $%-10.2f%n", 
                item.getId(),     // %-10s (String or number formatted as text)
                item.getName(),   // %-25s (String for name)
                item.getPrice()   // $%-10.2f (Decimal number for price with 2 decimal places)
            );
            
        }
         Main.Formatter(2,"=",70,1);
    }

    
}

class MenuItem {
    private String name;
    private double price;

    private static int nextID = 1;
    private int id;
    MenuItem(){}
    MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = nextID++;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}

class ComboMenu extends Menu {

    ComboMenu() {
        super("COMBO MEALS");

        items.add(new MenuItem("Burger + Fries", 50));
        items.add(new MenuItem("NigerianNuggets + Rice", 70));
    }

  
    
}


class DessertMenu extends Menu {

    DessertMenu() {
        super("Deserts");

        items.add(new MenuItem("Cake", 20));
        items.add(new MenuItem("Golden chicken Ass", 30));
    }


 
}

class CartItem{
    
    private MenuItem item;
    private int quantity; 
    private double itemPrices;

    CartItem(){}
    CartItem(MenuItem item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.itemPrices = item.getPrice();
    }

    public String getItem(){
        return this.item.getName();
    }

    public double getItemPrice(){
        return  item.getPrice();
    }

    public int Quantity(){
        return  this.quantity;

    }

    public double subtotal(){
        return item.getPrice() * quantity;
    }

   
}


class Cart{
    ArrayList<CartItem> cart = new ArrayList<CartItem>();

    public void addItem(MenuItem menuItem, int quantity) {
        cart.add(new CartItem(menuItem, quantity));
    }

    

    public double getTotal(){
        double total = 0;
        for (CartItem items: cart){
            total += items.subtotal();
        }
        return total;
    }


     public void displayItems(){
    System.out.printf("%-25s %-15s %-15s %-15s%n", "ItemName", "Price", "Quantity", "Subtotal");
         Main.Formatter(2,"=",70,1);
         for(CartItem items: cart){     
           System.out.printf("%-25s %-15.2f %-15d %-15.2f%n", 
            items.getItem(), 
            items.getItemPrice(), 
            items.Quantity(), 
            items.subtotal()
            );
            }
            System.out.println("");
            System.out.println("Total: " + getTotal());


    }
}




interface Payment{
void getBill(double amount);
void putMoney(double amount);
void ProcessPayment();
}


class Cashpayment implements Payment{
  private  double bill;
  private double amount;
  double receipt;
    @Override
    public void getBill(double bill) {
        this.bill = bill;
    }
    @Override
    public void putMoney(double amount){
        this.amount = amount;

    }

     @Override
    public void ProcessPayment(){
        this.receipt = this.bill - this.amount;

        System.out.println("Left to be Paid: " + this.receipt);

    }
}







class Main{
    static void Formatter(int type, String text, int repeat, int count) {
         for (int i = 0; i < count; i++) {
        if(type == 1){
             System.out.print(text.repeat(repeat)); //print
        } else {
            System.out.println(text.repeat(repeat)); //println
        }
       
    }
}
    static int findItemIndex(ArrayList<MenuItem> items, int ID) {
    for (int i = 0; i < items.size(); i++) {
        if (items.get(i).getId() == ID) {
            return i;

            
        }
        
    }
    return -1;
}
    static boolean validateID(ArrayList<MenuItem> item, int idChosen){

        
             for (int i = 0; i < item.size(); i++) {
             if (item.get(i).getId() == idChosen) {
            return true;    
    }
    

}

return false;
}


static boolean validateInput(Scanner input) {
    if (!input.hasNextInt()) {
        input.next();
        return false;
    }

    return true;
}
    public static void main(String[] args) {

            var cart = new Cart();
            var input = new Scanner(System.in);
            var combomeals = new ComboMenu();
            var desserts = new DessertMenu();
            Payment paymentMethod = new Cashpayment();
        
            boolean run = true;
            boolean runMenu = false;

            while(run) {
            Formatter(2,"=",70,1);
            System.out.println("Welcome to the Ace's Food Panda");
             Formatter(2,"=",70,1);
            System.out.println("Choose the menu: (1. ComboMeals 2. Dessserts 3.View cart 4. Pay 5. Exit)");
           
            Formatter(2,"=",70,1);
             System.out.print("Menu type: ");
             if(!validateInput(input)) {System.out.println("Invalid Input po numbers only!"); continue;}
            int choose = input.nextInt();
            int orderchoice;
            int index;
            int quantity;
            MenuItem orderedtedItem; 
            switch (choose) {
           
                case 1:
                    runMenu = true;
                    while(runMenu) {

                        Formatter(2,"=",70,1);
                        System.out.println("Choose your order mah man (enter the item's ID)");
                        Formatter(2,"=",70,1);

                         combomeals.displayMenu();
                         System.out.println("0: Back");

                         System.out.print("Item ID: ");
                         if(!validateInput(input)) {System.out.println("Invalid Input po numbers only!"); continue;}
                        orderchoice = input.nextInt();
                         if(orderchoice == 0){ runMenu = false;} 
                         else if(!validateID(combomeals.items, orderchoice)) {System.out.println("Invalid ID po"); continue;}
                        else{
                        index = findItemIndex(combomeals.items, orderchoice);
                         orderedtedItem = combomeals.items.get(index);
                          System.out.print("Quantity: ");
                         quantity = input.nextInt();
                        cart.addItem(orderedtedItem, quantity);
                        }  
                       
                    }
                        break;
                case 2:
                    runMenu = true;
                    while(runMenu) {
                        
                        Formatter(2,"=",70,1);
                        System.out.println("Choose your order mah man (enter the item's ID)");
                        Formatter(2,"=",70,1);

             
                         desserts.displayMenu();
                         System.out.println("0: Back");
                         System.out.print("Item ID: ");
                         if(!validateInput(input)) {System.out.println("Invalid Input po numbers only!"); continue;}
                        orderchoice = input.nextInt();
                         if(orderchoice == 0){ runMenu = false;}
                         else if(!validateID(desserts.items, orderchoice)) {System.out.println("Invalid ID po"); continue;}
                         else{
                        index = findItemIndex(desserts.items, orderchoice);
                         orderedtedItem = desserts.items.get(index);
                          System.out.print("Quantity: ");
                         quantity = input.nextInt();
                       cart.addItem(orderedtedItem, quantity);
                        }  
                       
                    }
                     
                    break;
                
                case 3:
                       cart.displayItems();
                    break;
                case 4:
                    System.out.print("put Money: ");
                        double money = input.nextDouble();
                        paymentMethod.getBill(cart.getTotal());
                        paymentMethod.putMoney(money);
                        paymentMethod.ProcessPayment();
                    break;
                case 5:
                        run = false;
                    break;
                
              
                default:
                    System.out.println("Invalid choice bro");
                break;
            
                
            }

        }
        System.out.println("Thanks");
            
    }   

}