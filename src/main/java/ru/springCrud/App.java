package ru.springCrud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.springCrud.model.Item;
import ru.springCrud.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //Получение Человека и его товары
//            Person person = session.get(Person.class, 1);
            // Получение данных о person
//            System.out.println(person);
            // Получение товаров для person
//            List<Item> items = person.getItems();
//            for(Item item : items) System.out.println(item);

            //Получение Человека через товары
//            Item item = session.get(Item.class, 6);
//            System.out.println(item);
//            Person person = item.getOwner();
//            System.out.println(person);

            //Добавление товара с СУЩЕСТВЕЮЩИМ Person
//            Person person = session.get(Person.class, 3);
//            Item newItem = new Item("SuperBook", person);
            //Добавление товара в уже существующий список Товаров, данные хранятся в КЭШЕ в Hibernate
//            person.getItems().add(newItem);
//            session.save(newItem);

            //Создание нового человека и добавление нового товара
//            Person newPerson = new Person("Vasilii",19);
//            Item newItem = new Item("BOOK", newPerson);
            //Добавление товара в список человека
//            newPerson.setItems(new ArrayList<>(Collections.singletonList(newItem)));
//            session.save(newPerson);
//            session.save(newItem);

            //Удаление товар у человека
//            Person person = session.get(Person.class, 2);
//            List<Item> items = person.getItems();
            //SQL
//            for (Item item : items){
//                session.remove(item);
//            }
            //Удаление из КЭША данные из Hibernate
//            person.getItems().clear();

            //Удаление человека из БД
//            Person person = session.get(Person.class, 5);
//            session.remove(person);
            //Назначение товарам знаечния NULL, т.к. человека больше не существует в БД
//            person.getItems().forEach(elem ->elem.setItem(null));

            //Назначить нового владельце на старого владельца сущ. товара
            Person  person = session.get(Person.class, 3);
            Item item = session.get(Item.class, 1);
            //Удаление у старого владельца товар из списка
            item.getOwner().getItems().remove(item);
            //Назначение нового владельца на товар
            item.setOwner(person);
            person.getItems().add(item);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
