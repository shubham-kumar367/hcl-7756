
package HCL_Assessment_2_CoreJava_2300320120246_ShubhamKumar;

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

abstract class LibraryItem
{
    int id;
    String title;
    boolean issued;

    LibraryItem(int id, String title)
    {
        this.id = id;
        this.title = title;
        this.issued = false;
    }

    abstract double getLateFee(int days);

    public String toString()
    {
        return id + " - " + title + " - " + (issued ? "Issued" : "Available");
    }
}

class Book extends LibraryItem
{
    Book(int id, String title)
    {
        super(id, title);
    }

    double getLateFee(int days)
    {
        return Math.max(0, days) * 5.0;
    }
}

class Magazine extends LibraryItem
{
    Magazine(int id, String title)
    {
        super(id, title);
    }

    double getLateFee(int days)
    {
        return Math.max(0, days) * 3.0;
    }
}

class DVD extends LibraryItem
{
    DVD(int id, String title)
    {
        super(id, title);
    }

    double getLateFee(int days)
    {
        return Math.max(0, days) * 10.0;
    }
}

class Member
{
    int id;
    String name;

    Member(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String toString()
    {
        return id + " - " + name;
    }
}

class LibraryException extends Exception
{
    LibraryException(String message)
    {
        super(message);
    }
}

class Library<T extends LibraryItem>
{
    Map<Integer, LibraryItem> items = new LinkedHashMap<>();
    Map<Integer, Member> members = new LinkedHashMap<>();
    Map<Integer, Queue<Integer>> waitlists = new HashMap<>();
    Map<Integer, Integer> borrowers = new HashMap<>();
    Map<Integer, LocalDate> dueDates = new HashMap<>();

    void addItem(T item) throws LibraryException
    {
        if (items.containsKey(item.id))
            throw new LibraryException("Item ID already exists");

        items.put(item.id, item);
        System.out.println("Item added successfully");
    }

    void registerMember(Member member) throws LibraryException
    {
        if (members.containsKey(member.id))
            throw new LibraryException("Member ID already exists");

        members.put(member.id, member);
        System.out.println("Member registered successfully");
    }

    void issueItem(int itemId, int memberId) throws LibraryException
    {
        LibraryItem item = items.get(itemId);

        if (item == null)
            throw new LibraryException("Item not found");

        if (!members.containsKey(memberId))
            throw new LibraryException("Member not found");

        if (item.issued)
        {
            waitlists.computeIfAbsent(itemId, k -> new LinkedList<>()).offer(memberId);
            System.out.println("Item unavailable. Member added to waitlist");
            return;
        }

        item.issued = true;
        borrowers.put(itemId, memberId);
        dueDates.put(itemId, LocalDate.now().plusDays(14));
        System.out.println("Item issued. Due date: " + dueDates.get(itemId));
    }

    void returnItem(int itemId) throws LibraryException
    {
        LibraryItem item = items.get(itemId);

        if (item == null)
            throw new LibraryException("Item not found");

        if (!item.issued)
            throw new LibraryException("Item is not issued");

        LocalDate dueDate = dueDates.get(itemId);
        long lateDays = Math.max(0, ChronoUnit.DAYS.between(dueDate, LocalDate.now()));

        System.out.println("Late fee: Rs. " + item.getLateFee((int) lateDays));

        item.issued = false;
        borrowers.remove(itemId);
        dueDates.remove(itemId);

        Queue<Integer> queue = waitlists.get(itemId);

        if (queue != null && !queue.isEmpty())
        {
            int memberId = queue.poll();
            item.issued = true;
            borrowers.put(itemId, memberId);
            dueDates.put(itemId, LocalDate.now().plusDays(14));
            System.out.println("Item automatically issued to member " + memberId);
        }
    }

    void displayItems()
    {
        for (LibraryItem item : items.values())
            System.out.println(item);
    }

    void displayMembers()
    {
        for (Member member : members.values())
            System.out.println(member);
    }

    void exportReport() throws IOException
    {
        try (PrintWriter writer = new PrintWriter("library_report.txt"))
        {
            writer.println("LIBRARY MANAGEMENT REPORT");
            writer.println("Items:");

            for (LibraryItem item : items.values())
                writer.println(item);

            writer.println("\nMembers:");

            for (Member member : members.values())
                writer.println(member);

            writer.println("\nWaitlists:");

            for (Map.Entry<Integer, Queue<Integer>> entry : waitlists.entrySet())
                writer.println("Item " + entry.getKey() + ": " + entry.getValue());

            writer.println("\nCurrent Borrowers:");

            for (Map.Entry<Integer, Integer> entry : borrowers.entrySet())
                writer.println("Item " + entry.getKey() + ", Member " + entry.getValue()
                        + ", Due date " + dueDates.get(entry.getKey()));
        }

        System.out.println("Report exported to library_report.txt");
    }
}

public class LibraryManagementSystem
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Library<LibraryItem> library = new Library<>();
        int choice;

        do
        {
            System.out.println("\n1. Add Book");
            System.out.println("2. Add Magazine");
            System.out.println("3. Add DVD");
            System.out.println("4. Register Member");
            System.out.println("5. Issue Item");
            System.out.println("6. Return Item");
            System.out.println("7. Display Items");
            System.out.println("8. Display Members");
            System.out.println("9. Export Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try
            {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice)
                {
                    case 1:
                    case 2:
                    case 3:
                        System.out.print("Enter item ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter title: ");
                        String title = sc.nextLine();

                        LibraryItem item;

                        if (choice == 1)
                            item = new Book(id, title);
                        else if (choice == 2)
                            item = new Magazine(id, title);
                        else
                            item = new DVD(id, title);

                        library.addItem(item);
                        break;

                    case 4:
                        System.out.print("Enter member ID: ");
                        int memberId = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter member name: ");
                        String name = sc.nextLine();

                        library.registerMember(new Member(memberId, name));
                        break;

                    case 5:
                        System.out.print("Enter item ID: ");
                        int issueId = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter member ID: ");
                        int issueMember = Integer.parseInt(sc.nextLine());

                        library.issueItem(issueId, issueMember);
                        break;

                    case 6:
                        System.out.print("Enter item ID: ");
                        int returnId = Integer.parseInt(sc.nextLine());
                        library.returnItem(returnId);
                        break;

                    case 7:
                        library.displayItems();
                        break;

                    case 8:
                        library.displayMembers();
                        break;

                    case 9:
                        library.exportReport();
                        break;

                    case 0:
                        System.out.println("Exiting Library System");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a valid number");
                choice = -1;
            }
            catch (LibraryException e)
            {
                System.out.println(e.getMessage());
                choice = -1;
            }
            catch (IOException e)
            {
                System.out.println("Error exporting report: " + e.getMessage());
                choice = -1;
            }
        }
        while (choice != 0);

        sc.close();
    }
}
