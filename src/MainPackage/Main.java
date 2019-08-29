package MainPackage;

import FileWorks.CollectionManager;
import FileWorks.CollectionWriter;
import FileWorks.ComandFactory;
import FileWorks.Comandable;
import Humans.*;

import java.util.*;


public class Main {
    public static void start(FileWorks.collection.CollectionManager manager)   {
           class Local{
            void method()  {
                ArrayList<Card> cards = new ArrayList<>(manager.getCollection());
                Human neznaika = new Human("Незнайка", Human.Status.Suspect, 72.5, 30.5, 2.5, Eyes.Blue, Hair.Blond, 2000);
                Policeman miguel = new Policeman("Мигль", Human.Status.ChiefPoliceOfficer, 2000);
                Policeman gegl = new Policeman("Гегль", Human.Status.OfficerAssistant);
                Human krasavchik = new Human("Красавчик", Human.Status.Jailbird, 72.5, 30.5, 2.5, Eyes.Amber, Hair.Red, 20000000);
                Card cardKrasavchik = null;
                for (Card card: cards) {
                    if(card.getName().equals(krasavchik.getName()))
                        cardKrasavchik = card;
                }
                if(cardKrasavchik == null){
                    System.err.println("В коллекции отсутствует карточка Красавчика!");
                    return;
                }
                 cardKrasavchik = new Card(50,100,krasavchik);
                neznaika.sayPhrase("За что меня повязали?");
                miguel.sayPhrase("Давайка составим карточку и сделаем фотографию, а потом решим за что вас посадить.");
                Card cardNeznaika = miguel.setCard(neznaika);
                if (miguel.Compare(cardKrasavchik, cardNeznaika)) {
                    miguel.sayPhrase("Так ты известный преступник под именем Красавчик. ");
                    neznaika.sayPhrase("Вы меня перепутали, я Незнайка.");
                    miguel.sayPhrase("На, посмотри на карточки.");
                    System.out.println();
                    System.out.println(cardNeznaika.toString());
                    System.out.println(cardKrasavchik.toString());
                    if (neznaika.Compare(cardKrasavchik, cardNeznaika)) {
                        neznaika.sayPhrase("Мы действительно похожи, это какая-то ошибка.");
                    } else {
                        neznaika.sayDifference(cardKrasavchik, cardNeznaika);
                        neznaika.sayPhrase("Это не я!");
                        miguel.sayPhrase("В наше время за деньги можно изменить все что угодно, даже твою судьбу.");
                    }

                        try {
                        miguel.askBribe(miguel.getBribe(),neznaika.getMoney());
                        neznaika.sayPhrase("Ну у меня столько нет, я не Красавчик, я же вам говорю.");
                        miguel.sayPhrase("Всё! Мне это надоело. Гегль! Гегль! Уводи его.");
                        gegl.sayPhrase("Фить! Фить! Бегом в камеру!");
                    }catch (MoneyException ex){
                            ex.printStackTrace();
                          System.out.println(ex.getMessage());
                          System.out.println("Количество денег Незнайки =  "+ex.getMoney()); }
                        catch ( BribePlusException ex){
                            ex.printStackTrace();
                            System.out.println(ex.getMessage());
                            System.out.println("Размер взятки, которую требует Мигль =  "+ex.getBribe()); }
                        catch (BribeException ex){
                            ex.printStackTrace();
                            System.out.println(ex.getMessage());
                            System.out.println("Количество денег Незнайки =  "+ex.getMoney());
                            System.out.println("Размер взятки, которую требует Мигль =  "+ex.getBribe());
                            neznaika.sayPhrase("На держи, не в чём себе не отказывай.");
                            miguel.sayPhrase("Извените за беспокойство. Гегль! Гегль! Проводи господина Незнайку на улицу, он доказал, что он не виновен.");
                            gegl.sayPhrase("Да, конечно. Пройдёмтесо мной, пожалуйста.");}

                } else {
                    miguel.sayPhrase("Хм, вы свободны, извините");
                }
                IHuman localresident = new Human(){
                        @Override
                    public void sayPhrase(String str){
                            System.out.println("Местный житель: "+str);
                    }
                };
                localresident.sayPhrase("Мужики, ну отпустите бога ради, меня жена убьёт. Я уже у вас 2 дня в обезьяннике сижу. ");
            }
           }
                Local local = new Local();
                local.method();
            }
    public static void main(String [] args) throws Exception{
        String env = System.getenv("FILE");
        if(env == null || env.length() == 0){
            System.out.println("Укажите путь к файлу в переменной окружения!");
            System.exit(0);
        }

        CollectionManager manager = new CollectionManager(env);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите help для получения помощи");
        if (manager.read()){
            while(true){
                String line = "";
                try{
                    line = scanner.nextLine();
                }
                catch (NoSuchElementException e){
                    System.out.println("Завершение!");
                    manager.close();
                    System.exit(0);
                }

                Pair<Comandable,String> cmdPair = ComandFactory.createComand(line);

                if(cmdPair.getKey() != null)
                    cmdPair.getKey().run(cmdPair.getValue(), manager);
                else{
                    System.out.println("Неверная команда!");
                }
            }
        }
        manager.close();
    }
}