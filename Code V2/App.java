import javax.swing.*;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;

public class App{
    //Declares objects as attributes, this helps to simply initiate them instead of declaring them every time we want one of these
    public static JPanel panel, newPanel;
    public static JFrame frame, popupFrame;
    public static JButton addH, addT, addB, back, add,
    daily, weekly, monthly,
    easy, medium, hard;
    public static JTextField name, deadline;
    public static JLabel label, time, diff;
    public static FileWriter writer;
    public static ItemList itemList= new ItemList();
    public static JList<String> habits= new JList<>(itemList.habits.stream().map(Habit::toString).toArray(String[]::new));
    public static JList<String> tasks= new JList<>(itemList.habits.stream().map(Habit::toString).toArray(String[]::new));
    public static Integer timeSelected, diffSelected;
    public static String HF= "Archivos/habitos.txt";
    public static String TF= "Archivos/tareas.txt";
    public static String BHF= "Archivos/malos.txt";

    public static void main(String[] args) {
        //Try to create a directory
        try{
            String userDirect= System.getProperty("user.dir");
            File directory= new File(userDirect+File.separator+"Archivos");
            if(directory.mkdir()){
                System.out.println("Directory created");
            }
            else{
                System.out.println("Directory already exists");
            }
        }catch(Exception x){
            System.out.println("Cant create directory: "+x);
        }

        //Try to create a text file
        try{
            File habitsFile= new File(HF);
            if(habitsFile.createNewFile())
                System.out.println("Habits file created");
            else
                System.out.println("Habits file already exists");
        }
        catch(IOException e){
            System.out.println("Can't create habits file: "+e);
        }
        try{
            File tasksFile= new File(TF);
            if(tasksFile.createNewFile())
                System.out.println("Tasks file created");
            else
                System.out.println("Tasks File Already exists");
        }
        catch(IOException x){
            System.out.println("Can´t create tasks file: "+x);
        }
        try{
            File tasksFile= new File(BHF);
            if(tasksFile.createNewFile())
                System.out.println("Bad habits file created");
            else
                System.out.println("Bad habits File Already exists");
        }
        catch(IOException b){
            System.out.println("Can´t create bad habits file: "+b);
        }

        startFrame();
        startButtons();
        //Set main panel
        panel.setLayout(null);
        panel.add(addH);
        panel.add(addT);
        panel.add(addB);

        panel.setBackground(Color.decode("#472287"));

    }
    /**Redirects to the corresponfing panel */
    private static void event(int id){
        panel.setVisible(false);
        newPanel = new JPanel();
        newPanel.setBounds(0, 0, 1920, 1080);
        switch (id) {
            case 1:
                newPanel.setBackground(Color.decode("#911bcc"));
                AddHabit();
                break;
            case 2:
                newPanel.setBackground(Color.decode("#8c1176"));
                AddTask();
                break;
            case 3:
                newPanel.setBackground(Color.decode("#2c118c"));
                AddBadHabit();
                break;
            default:
                newPanel.setBackground(Color.RED);
                break;
        }
        
        newPanel.setVisible(true);
        newPanel.setLayout(null);
        frame.add(newPanel);
        //Creates a button that goes back to the main panel
        back= new JButton("Back");
        back.setBounds(1400, 20, 100, 60);
        back.addActionListener( e-> {
            newPanel.setVisible(false);
            panel.setVisible(true);
        });

        newPanel.add(back);

        frame.revalidate();
        frame.repaint();
    }

    private static void AddHabit(){
        label = new JLabel("ADD HABIT");
        label.setBounds(725, 20, 100, 20);
        label.setVisible(true);

        name = new JTextField();
        name.setText("Name : ");
        name.setBounds(525, 100, 500, 100);
        name.setVisible(true);
        textDissapear(1);

        time= new JLabel("Time: ");
        time.setBounds(525, 300, 100, 20);
        time.setVisible(true);

        diff= new JLabel();
        diff.setText("Difficulty: ");
        diff.setBounds(525, 500, 100, 20);
        diff.setVisible(true);

        addButtonHabit();

        startHabitButtons();
        newPanel.add(label);
        newPanel.add(name);
        newPanel.add(time);
        newPanel.add(diff);

    }

    private static void AddTask(){
        label = new JLabel("ADD TASK");
        label.setBounds(725, 20, 100, 20);
        label.setVisible(true);

        name = new JTextField();
        name.setText("Name : ");
        name.setBounds(525, 100, 500, 100);
        name.setVisible(true);
        textDissapear(1);

        deadline= new JTextField("Deadline : ");
        deadline.setBounds(525, 300, 500, 100);
        deadline.setVisible(true);
        textDissapear(2);

        diff= new JLabel("Difficulty: ");
        diff.setBounds(525, 500, 100, 20);
        diff.setVisible(true);

        addButtonTask();

        startDiffButtons();
        newPanel.add(label);
        newPanel.add(name);
        newPanel.add(deadline);
        newPanel.add(diff);
    }

    private static void AddBadHabit(){
        label = new JLabel("ADD BAD HABIT");
        label.setForeground(Color.decode("#f0e9e9"));
        label.setBounds(725, 20, 100, 20);
        label.setVisible(true);

        name = new JTextField("Name : ");
        name.setBounds(525, 100, 500, 100);
        name.setVisible(true);
        textDissapear(1);

        time= new JLabel("Time: ");
        time.setForeground(Color.decode("#f0e9e9"));
        time.setBounds(525, 300, 500, 20);
        time.setVisible(true);

        diff= new JLabel("Difficulty: ");  
        diff.setForeground(Color.decode("#f0e9e9"));
        diff.setBounds(525, 500, 500, 20);
        diff.setVisible(true);

        addButtonBadHabit();

        startHabitButtons();
        newPanel.add(label);
        newPanel.add(name);
        newPanel.add(time);
        newPanel.add(diff);
    }

    /**Starts the frame with a panel */
    private static void startFrame(){
        frame = new JFrame("Habitra");
        panel = new JPanel();
        frame.setSize(1920,1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    }
    /**Starts the buttons */
    private static void startButtons(){
        addH= new JButton("AddHabit");
        addH.setBounds(30, 500, 100, 60);
        addH.addActionListener(e -> {
            event(1);
            System.out.println("Add Habit button clicked");
        });
        
        addT= new JButton("AddTask");
        addT.setBounds(30, 600, 100, 60);
        addT.addActionListener(e -> {
            event(2);
            System.out.println("Add Task button clicked");
        });

        addB= new JButton("AddBadHabit");
        addB.setBounds(30, 700, 130, 60);
        addB.addActionListener(e -> {
            event(3);
            System.out.println("Add Bad Habit button clicked");
        });
    }
    /**Creates an "add"*/
    private static void addButton(){
        add= new JButton("ADD!");
        add.setBounds(720, 700, 100, 60);
        add.setVisible(true);
    }

    /**Creates an "add" button for habits */
    private static void addButtonHabit(){
        addButton();
        add.addActionListener(e->{
        popupFrame= new JFrame("Habit Added");
        String newName= name.getText();
        Boolean success;
            try{
                Habit newHabit= new Habit(newName, timeSelected, diffSelected, false, false);
                itemList.habits.add(newHabit);
                habits.setListData(itemList.habits.stream().map(Habit::toString).toArray(String[]::new));      
                JOptionPane.showMessageDialog(popupFrame, "Habit added!");
                success= true;
                System.out.println(newHabit.toString());
            }
            catch(Exception h){
                JOptionPane.showMessageDialog(popupFrame, "Couldn´t add habit");
                success= false;
            }
            if(success){
                try{
                    writer= new FileWriter(HF,true);
                    writer.write(newName+" ");
                    writer.write(timeSelected+" ");
                    writer.write(diffSelected+" ");
                    writer.write(System.lineSeparator());
                    writer.close();
                }
                catch(IOException x){
                    System.out.println("Impossible to write on file: "+x);
                }     
            }
            newPanel.setVisible(false);
            panel.setVisible(true);
        });
        
        newPanel.add(add);
    }

    /**Creates an "add" button for bad habits */
    private static void addButtonBadHabit(){
        addButton();
        add.addActionListener(e->{
        popupFrame= new JFrame("Bad Habit Added");
        String newName= name.getText();
        Boolean success;
            try{
                Habit newHabit= new Habit(newName, timeSelected, diffSelected, false, true);
                itemList.habits.add(newHabit);
                habits.setListData(itemList.habits.stream().map(Habit::toString).toArray(String[]::new));      
                JOptionPane.showMessageDialog(popupFrame, "Bad Habit added");
                success= true;
                System.out.println(newHabit.toString());
            }
            catch(Exception h){
                JOptionPane.showMessageDialog(popupFrame, "Couldn´t add habit");
                success= false;
            }
            if(success){
                try{
                    writer= new FileWriter(BHF,true);
                    writer.write(newName+" ");
                    writer.write(timeSelected+" ");
                    writer.write(diffSelected+" ");
                    writer.write(System.lineSeparator());
                    writer.close();
                }
                catch(IOException x){
                    System.out.println("Impossible to write on file: "+x);
                }     
            }
            newPanel.setVisible(false);
            panel.setVisible(true);
        });
        
        newPanel.add(add);
    }

    /**Creates an "add" button for tasks*/
    private static void addButtonTask(){
        addButton();
        add.addActionListener(e->{
        popupFrame= new JFrame("Task Added");
        String newName= name.getText();
        String newDead= deadline.getText();
        Boolean success;
            try{
                Task newTask= new Task(newName, newDead, diffSelected, false);
                itemList.tasks.add(newTask);
                tasks.setListData(itemList.tasks.stream().map(Task::toString).toArray(String[]::new));      
                JOptionPane.showMessageDialog(popupFrame, "Task added!");
                success= true;
                System.out.println(newTask.toString());
            }
            catch(Exception h){
                JOptionPane.showMessageDialog(popupFrame, "Couldn´t add task");
                success= false;
            }
            if(success){
                try{
                writer= new FileWriter(TF,true);
                writer.write(newName+" ");
                writer.write(newDead+" ");
                writer.write(diffSelected+" ");
                writer.write(System.lineSeparator());
                writer.close();
                }
                catch(IOException x){
                System.out.println("Impossible to write on file: "+x);
                }     
            }
            newPanel.setVisible(false);
            panel.setVisible(true);
        });
        
        newPanel.add(add);
    }

    private static void textDissapear(int id){
        switch (id) {
            case 1:
                name.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e){
                if(name.getText().equals("Name : "))
                    name.setText("");
                }
                @Override
                public void focusLost(FocusEvent e){
                if(name.getText().isBlank())
                    name.setText("Name : ");
                }
                });
            break;

            case 2:
                deadline.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e){
                if(deadline.getText().equals("Deadline : "))
                    deadline.setText("");
                }
                @Override
                public void focusLost(FocusEvent e){
                if(deadline.getText().isBlank())
                    deadline.setText("Deadline : ");
                }
                });
            break;
        
            default:
                System.out.println("Impossible to dissapear placeholder");
                break;
        }    
    }

    public static void startHabitButtons(){
        startTimeButtons();
        startDiffButtons();
    }

    /**Initializes time buttons */
    public static void startTimeButtons(){
        daily= new JButton("Daily");
        daily.setBounds(625, 300, 100, 40);
        daily.setVisible(true);
        daily.addActionListener(e->{
            selectTimeButton(daily);
            timeSelected=3;
        });

        weekly= new JButton("Weekly");
        weekly.setBounds(725, 300, 100, 40);
        weekly.setVisible(true);
        weekly.addActionListener(e->{
            selectTimeButton(weekly);
            timeSelected=2;
        });

        monthly= new JButton("Monthly");
        monthly.setBounds(825, 300, 100, 40);
        monthly.setVisible(true);
        monthly.addActionListener(e->{
            selectTimeButton(monthly);
            timeSelected=1;
        });

        newPanel.add(daily);
        newPanel.add(weekly);  
        newPanel.add(monthly);
    }

    /**Initializes difficulty buttons */
    public static void startDiffButtons(){
        easy= new JButton("Easy");
        easy.setBounds(625, 500, 100, 40);
        easy.setVisible(true);
        easy.addActionListener(e->{
            selectDiffButton(easy);
            diffSelected=1;
        });

        medium= new JButton("Medium");
        medium.setBounds(725, 500, 100, 40);
        medium.setVisible(true);
        medium.addActionListener(e->{
            selectDiffButton(medium);
            diffSelected=2;
        });

        hard= new JButton("Hard");
        hard.setBounds(825, 500, 100, 40);
        hard.setVisible(true);
        hard.addActionListener(e->{
            selectDiffButton(hard);
            diffSelected=3;
        });

        newPanel.add(easy);
        newPanel.add(medium);
        newPanel.add(hard);
    }

    /**Logic for when a time button is selected */
    public static void selectTimeButton(JButton sButton){
        JButton[] buttons = {daily, weekly, monthly};
        for (JButton button : buttons) {
            if (button == sButton) {
                button.setBackground(Color.decode("#101b82"));
            } else {
                button.setBackground(Color.decode("#ffffff"));
            }
        }
    }

    /**Logic for when a difficulty button is selected */
    public static void selectDiffButton(JButton sButton){
        JButton[] buttons = {easy, medium, hard};
        for (JButton button : buttons) {
            if (button == sButton) {
                button.setBackground(Color.decode("#590814"));
            } else {
                button.setBackground(Color.decode("#ffffff"));
            }
        }
    }
}